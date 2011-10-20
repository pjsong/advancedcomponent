package ruking.dao;

import java.sql.SQLException;
import java.util.Map;

import db.DataSourceFactory;
import db.DbUtil;
import db.MDTMySQLRowMapper;
import db.QueryRunner;
import db.TransRunner;
import ruking.dto.UserSignUpDTO;
import ruking.utils.Util;

public class UserSignUpDAO {
	public static String dbName = "zkm0m1_db";
	public static UserSignUpDTO getUserByLoginName(String loginName) throws SQLException{
		UserSignUpDTO u=new UserSignUpDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(dbName,"ChinacaT"), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM users WHERE LoginName = " + DbUtil.escSql(loginName);
		Map m=runner.queryForMap(sql);
		if(m==null)return null;
		else{
			u.setAnswer((String)m.get("Answer"));
			u.setCompanyaddress((String)m.get("CompanyAddress"));
			u.setCompanyname((String)m.get("CompanyName"));
			u.setCompanywebsite((String)m.get("CompanyWebSite"));
			u.setEmail((String)m.get("Email"));
			u.setPhoneFax((String)m.get("PhoneFax"));
			u.setLoginName((String)m.get("LoginName"));
			u.setMsnNumber((String)m.get("MsnNumber"));
			u.setQqNumber((String)m.get("QQNumber"));
			u.setName((String)m.get("RealName"));
			u.setMobile((String)m.get("Mobile"));
		}
		return u;
	}
	public static boolean loginNameExists(String loginName) throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(dbName,"ChinacaT"), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM users WHERE LoginName = " + DbUtil.escSql(loginName);
		Map m=runner.queryForMap(sql);
		if(m==null)return false;
		return true;
	}
	public static UserSignUpDTO forgetPassword(String email,String question,String answer) throws SQLException{
		UserSignUpDTO u=new UserSignUpDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(dbName,"ChinacaT"), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM users WHERE Email = " + DbUtil.escSql(email)+" and Question="+DbUtil.escSql(question)+" and Answer="+DbUtil.escSql(answer);
		Map m=runner.queryForMap(sql);
		if(m==null)return null;
		return getUserByLoginName((String)m.get("LoginName"));
	}
	public static void insertUser(UserSignUpDTO u) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(dbName,"ChinacaT"), new MDTMySQLRowMapper());
		String sql="insert into users(LoginName,Password,Question,Sex,Answer,Email,CompanyAddress,Mobile,NewsLetter";
		if(!Util.getNoNull(u.getCompanywebsite()).trim().equals(""))sql+=",CompanyWebSite";
		if(!Util.getNoNull(u.getCompanyname()).trim().equals(""))sql+=",CompanyName";
		if(!Util.getNoNull(u.getName()).trim().equals(""))sql+=",RealName";
		if(!Util.getNoNull(u.getQqNumber()).trim().equals(""))sql+=",QQNumber";
		if(!Util.getNoNull(u.getMsnNumber()).trim().equals(""))sql+=",MsnNumber";
		if(!Util.getNoNull(u.getPhoneFax()).trim().equals(""))sql+=",PhoneFax";
		sql = sql+") values ("+u.getLoginName().trim()+","+u.getPassword().trim()+","+u.getQuestion()+","+u.getSex()+","+u.getAnswer()+","+u.getEmail()+","+u.getCompanyaddress()+","+u.getMobile()+","+u.getNewsLetter();
		if(!Util.getNoNull(u.getCompanywebsite()).equals(""))sql+=","+u.getCompanywebsite().trim();
		if(!Util.getNoNull(u.getCompanyname()).equals(""))sql+=","+u.getCompanyname().trim();
		if(!Util.getNoNull(u.getName()).equals(""))sql+=","+u.getName().trim();
		if(!Util.getNoNull(u.getQqNumber()).equals(""))sql+=","+u.getQqNumber().trim();
		if(!Util.getNoNull(u.getMsnNumber()).equals(""))sql+=","+u.getMsnNumber().trim();
		if(!Util.getNoNull(u.getPhoneFax()).equals(""))sql+=","+u.getPhoneFax().trim();
		sql=sql+");";
		runner.update(sql);
	}

}
