package utils;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import db.DataSourceFactory;
import db.DbUtil;
import db.MDTMySQLRowMapper;
import db.QueryRunner;
import db.RowMapper;
import db.TransRunner;
import dto.SessionDTO;

public class SessionUtil
{	
	private static SimpleDateFormat sessionIdPrefixFormatter = new SimpleDateFormat("yyMMddHHmmss");
	private static final int MAX_INACTIVE_INTERVAL = 120;	// 120 minutes
	private static final double CLEAN_PROBABILITY = 1/1000.00;
	private static Random random = new Random(System.currentTimeMillis());
	public static final String SESS_ID = "sessId";
	public static final String SESS_DATA = "sessData";
	public static final String REFERRAL_BASE_URL = "referralBaseUrl";
	public static final String REFERRAL_EXTENDED_URL = "referralExtendedUrl";
	private DataSource ds;
	private RowMapper rowMapper;
	
	public SessionUtil(DataSource ds, RowMapper rowMapper)
	{
		this.ds = ds;
		this.rowMapper = rowMapper;
	}

	// return the sessId if available, otherwise, return null
	public String getSessIdFromCookie(HttpServletRequest request) throws SQLException
	{
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
		{
			return null;
		}
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals(SessionUtil.SESS_ID))	// found
			{
				return cookie.getValue();
			}
		}
		return null;
	}

	public void updateLastUpdatedField(String sessId) throws Exception
	{
		TransRunner runner = new TransRunner(ds, rowMapper, false);
		// update session last_updated field
		String sql = "update sessions set last_updated=now() where id = " + DbUtil.escSql(sessId);
		runner.update(sql);		
	}

	// return the sessId created
	// there is a 1/1000 probability that the clean() method is called such that the session will be cleaned
	public String createSession(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		TransRunner runner = new TransRunner(ds, rowMapper, false);
		int i = 0;
		while (true)
		{
			// generate a random number to insert into the sessions table
			String sessId = sessionIdPrefixFormatter.format(new Date()) + random.nextInt(1000);
			String sqlTmpl = "insert into sessions (id, last_updated, data) values (%s, now(), %s)";
			String sql = String.format(sqlTmpl, DbUtil.escSql(sessId), DbUtil.escSql(""));
			try
			{
				runner.update(sql);
			}
			catch (Exception e)
			{
				// possible collision with existing ids, sleep and then continue trying couple of times
				if (i >= 5)
				{
					throw e;
				}
				else
				{
					Thread.sleep(1000);
					i++;
					continue;
				}
			}
			
			// set the sessId in request and response
			Cookie c = new Cookie(SessionUtil.SESS_ID, sessId);
			c.setPath("/");
			c.setMaxAge(-1);	// deleted after browser closes (in theory ;-))
			response.addCookie(c);
			
			// clean the sessions table once in a while
			if (random.nextFloat() < CLEAN_PROBABILITY)
			{
				clean();
			}
			
			return sessId;
		}
	}

//	public Map<String, Object> read(HttpServletRequest request) throws Exception
//	{
//		String sessId = (String) request.getAttribute(SessionUtil.SESS_ID);
//		SessionDTO sessDTO = readSessDTO(sessId);
//		if (sessDTO == null)
//		{
//			return null;
//		}
//		else
//		{
//			return sessDTO.getSessData();
//		}
//	}

	public void write(HttpServletRequest request, Map<String, Object> sessData) throws Exception
	{
		String sessId = (String) request.getAttribute(SessionUtil.SESS_ID);
		//sessId ="";
        internalWrite(sessId, sessData);
        request.setAttribute(SessionUtil.SESS_DATA, sessData);
	}
	
	public void putAndWrite(HttpServletRequest request, Map<String, Object> sessData,String sessionName, Object sessionValue) throws Exception
	{
		sessData.put(sessionName, sessionValue);
        write(request,sessData);
	}
	
	public SessionDTO readSessDTO(String sessId) throws Exception
	{	
		QueryRunner runner = new QueryRunner(ds, rowMapper, false);
		String sqlTmpl = "select data, last_updated from sessions where id=%s";
		String sql = String.format(sqlTmpl, DbUtil.escSql(sessId));
		Map<String, Object> map = runner.queryForMap(sql);
		if (map == null)
		{
			// something bad happened, return null
			return null;
		}

		String data = (String) map.get("data");
		Map<String, Object> sessData;
		if (data == null || data.equals(""))
		{
			sessData = new HashMap<String, Object>();
		}
		else
		{
			try
			{
				int colIndex = data.indexOf(':');
				String[] names = data.substring(0, colIndex).split(",");
				String[] values = data.substring(colIndex + 1).split(",");
				
				sessData = new HashMap<String, Object>();
				for (int i = 0; i < names.length; i++)
				{
					sessData.put(names[i], base64ToObject(values[i]));
				}
			}
			catch (Exception e)
			{
				// data conversion error, so, session data got changed
				sessData = new HashMap<String, Object>();
			}
		}
		return new SessionDTO(sessId, (Date) map.get("last_updated"), sessData);
	}


	///////////////////////////////// private methods ////////////////////////////////////////

	// the write function keeps the timestamp of the last access int the last_updated column for each record
	// the sessData is written in this format:
	// name1,name2,name3,:value1,value2,value3,
	private void internalWrite(String sessId, Map<String, Object> sessData) throws Exception
	{
		
		if (sessData.size() == 0)
		{
			//Logger.warn("Session Data is Empty: " + sessId);
			return;
		}
		StringBuilder nameBuf = new StringBuilder();
		StringBuilder valueBuf = new StringBuilder();
		for (String name : sessData.keySet())
		{
			String value = objectToBase64(sessData.get(name));
			nameBuf.append(name).append(",");
			valueBuf.append(value).append(",");
		}
		String data = nameBuf + ":" + valueBuf;
		
		TransRunner runner = new TransRunner(ds, rowMapper, false);
		String sqlTmpl = "update sessions set last_updated=now(), data=%s where id=%s";
		String sql = String.format(sqlTmpl, DbUtil.escSql(data), DbUtil.escSql(sessId));
		runner.update(sql);
	}

	private void clean() throws Exception
	{
		TransRunner runner = new TransRunner(ds, rowMapper, false);
		String sqlTmpl = "delete from sessions where date_sub(now(), interval %s minute) > last_updated";
		String sql = String.format(sqlTmpl, MAX_INACTIVE_INTERVAL);
		runner.update(sql);
	}

	public static Object base64ToObject(String data) throws Exception
	{
		char[] base64Value = data.toCharArray();
		byte[] bytes = Base64.decode(base64Value);		
		ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(bai));
		return ois.readObject();		
	}
	
	public static String objectToBase64(Object obj) throws IOException
	{
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(bao));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
		char[] base64Value = Base64.encode(bao.toByteArray());
		return new String(base64Value);		
	}
	///////////////////////////////// main method for testing ////////////////////////////////////////
	
	public static void main(String[] args) throws Exception
	{
		SessionUtil sessUtil = new SessionUtil(DataSourceFactory.getDataSource(""), new MDTMySQLRowMapper());
//		Map<String, Object> map = sessUtil.internalRead("41030627");
//		System.out.println(map);
	}
}