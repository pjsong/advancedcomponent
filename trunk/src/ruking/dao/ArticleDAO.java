package ruking.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ruking.db.DataSourceFactory;
import ruking.db.DbUtil;
import ruking.db.MDTMySQLRowMapper;
import ruking.db.QueryRunner;
import ruking.db.TransRunner;
import ruking.dto.ArticleDTO;
import ruking.utils.Util;

public class ArticleDAO {
	public String hostName;
	public String dbName;// = "zkm0m1_db";
	public String password;// = "pjsong";
	public String dbUser;

	public ArticleDAO(String hostName, String dbName, String dbUser,
			String password) {
		super();
		this.hostName = hostName;
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.password = password;
	}


	public List<Map> getArticlesByType(String Type)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM articles where Type ="+DbUtil.escSql(Type);
		List<Map> ret = runner.query(sql);
		return ret;
	}

	public List<Map> getAllArticles()throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM articles";
		return runner.query(sql);
	}


	public  ArticleDTO getArticlesByID(String id) throws SQLException{
		ArticleDTO u=new ArticleDTO();
		Map m=getMapByID(id);
		if(m==null)return null;
		else{
			u.setId(((Integer)m.get("ID")).toString());
			u.setType((String)m.get("Type"));
			u.setHeader((String)m.get("Header"));
			u.setContent((String)m.get("Content"));
			u.setType((String)m.get("Type"));
		}
		return u;
	}
	public  Map getMapByID(String id) throws SQLException{
		ArticleDTO u=new ArticleDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM articles WHERE ID = " + DbUtil.escSql(id);
		return runner.queryForMap(sql);
	}
	
	
	public  ArticleDTO insertArticle(ArticleDTO p) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="insert into articles(Type,Header,Content,Author,UpdatedTime";
		sql = sql+") values ("+DbUtil.escSql(p.getType().trim())+","+DbUtil.escSql(p.getHeader().trim())+","+DbUtil.escSql(p.getContent())+","+DbUtil.escSql(p.getAuthor())+",now()";
		sql=sql+");";
		runner.update(sql);
		return p;
	}
	public  void updateArticles(ArticleDTO p) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="update articles set Type="+DbUtil.escSql(p.getType())+",Header="+DbUtil.escSql(p.getHeader());
		sql+=",Content="+DbUtil.escSql(p.getContent())+",Author="+DbUtil.escSql(p.getAuthor())+" where ID="+DbUtil.escSql(p.getId());
		runner.update(sql);
	}
	public void deleteArticles(String id) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "delete FROM articles WHERE ID = " + DbUtil.escSql(id);
		runner.update(sql);
	}
}