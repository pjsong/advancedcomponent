package ruking.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ruking.db.DataSourceFactory;
import ruking.db.DbUtil;
import ruking.db.MDTMySQLRowMapper;
import ruking.db.QueryRunner;
import ruking.db.TransRunner;
import ruking.dto.ProductDTO;
import ruking.dto.ProductDTO;
import ruking.utils.Util;

public class ProductDAO {
	public String hostName;
	public String dbName ;//= "zkm0m1_db";
	public String password ;//= "pjsong";
	public String dbUser;

	public ProductDAO(String hostName,String dbName,String dbUser, String password) {
		super();
		this.hostName = hostName;
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.password = password;
	}
	
	public List<Map> getAllProducts()throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product";
		return runner.query(sql);
	}
	public  ProductDTO getProductByTitle(String title) throws SQLException{
		ProductDTO u=new ProductDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product WHERE Title = " + DbUtil.escSql(title);
		Map m=runner.queryForMap(sql);
		if(m==null)return null;
		else{
			u.setId((String)m.get("ID"));
			u.setTitle((String)m.get("Title"));
			u.setDescription((String)m.get("Description"));
			u.setCategory((String)m.get("Category"));
			u.setSubcategory((String)m.get("SubCategory"));
		}
		return u;
	}
	public  boolean productTitleExits(String title) throws SQLException{
		ProductDTO u=new ProductDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product WHERE Title = " + DbUtil.escSql(title);
		Map m=runner.queryForMap(sql);
		if(m==null)return true;
		return false;
	}
	public  ProductDTO getProductByID(String id) throws SQLException{
		ProductDTO u=new ProductDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product WHERE ID = " + DbUtil.escSql(id);
		Map m=runner.queryForMap(sql);
		if(m==null)return null;
		else{
			u.setId(((Integer)m.get("ID")).toString());
			u.setTitle((String)m.get("Title"));
			u.setDescription((String)m.get("Description"));
			u.setCategory((String)m.get("Category"));
			u.setSubcategory((String)m.get("SubCategory"));
		}
		return u;
	}
	

	public  ProductDTO insertProduct(ProductDTO p) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="insert into product(Title,Description,Category,SubCategory";
		sql = sql+") values ("+DbUtil.escSql(p.getTitle().trim())+","+DbUtil.escSql(p.getDescription().trim())+","+DbUtil.escSql(p.getCategory())+","+DbUtil.escSql(p.getSubcategory());
		sql=sql+");";
		runner.update(sql);
		Map m= runner.queryForMap("select ID from product where Title="+DbUtil.escSql(p.getTitle()));
		p.setId((String)m.get("ID"));
		return p;
	}
	public  void updateProduct(ProductDTO p) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="update product set Title="+DbUtil.escSql(p.getTitle())+",Description="+DbUtil.escSql(p.getDescription());
		sql+=",Category="+DbUtil.escSql(p.getCategory())+",SubCategory="+DbUtil.escSql(p.getSubcategory())+";";
		runner.update(sql);
	}
	public void deleteProduct(String id) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "delete FROM product WHERE ID = " + DbUtil.escSql(id);
		runner.update(sql);
	}
}
