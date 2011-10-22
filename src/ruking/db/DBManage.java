package ruking.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBManage {
	private String url;

	private String dbuser;

	private String dbpass;

	private String driver = "com.mysql.jdbc.Driver";

	public DBManage() {
        this.getProperties();
	}

	private void getProperties() {
		InputStream is;
		Properties dbProps = null;
			 
		try {
			is = getClass().getResourceAsStream("/conf/dbconfig.propertie");
			dbProps = new Properties();
			dbProps.load(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  catch(Exception e){
            is = getClass().getResourceAsStream(
                    "dbconfig.propertie");
         }
		Enumeration enu=dbProps.propertyNames();
		while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            
            if (name.equals("dbuser")) 
                dbuser = dbProps.getProperty(name);
            if(name.equals("dbpassword"))
            	dbpass=dbProps.getProperty(name);
            if(name.equals("url"))
            	url=dbProps.getProperty(name);
		}
	}

	public Connection getConnection() {
		Connection con;
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, dbuser, dbpass);
			return con;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int renewData(String sql) {
		int count = 0;
		Connection con = null;
		Statement stat = null;

		try {
			con = getConnection();
			stat = con.createStatement();
			count = stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
				try {
					if(stat!=null)
					stat.close();
					if(con!=null)
						con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return count;
	}

	public List listData(String tablename,String id) {

		List list = new ArrayList();
		String sql = "select * from " + tablename+" order by "+id+" desc", tmp;
		ResultSet rs = null;
		Connection con = null;
		Statement stat = null;
		try {
			con = getConnection();
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Map map = new HashMap();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}
	public static void main(String [] args){
		DBManage dbm=new DBManage();
		dbm.getProperties();
	}
}
