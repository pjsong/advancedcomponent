package ruking.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	public List<Map> getGlobalCatProducts(int id)throws SQLException{
		List<Map> ret = new ArrayList<Map>();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT ProductIDs FROM globalcat where ID="+DbUtil.escSql(id);
		Map map = runner.queryForMap(sql);
		if(map == null)return ret;
		String str = (String)map.get("ProductIDs");
		if(str==null || str.equals(""))return null;
		String[] ids = str.split(",");
		for(String pid:ids){
			Map p = getMapByID(pid);
			ret.add(p);
		}
		return ret;
	}
	public List<Map> getCatProducts(String cat)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product where SubCategory ="+DbUtil.escSql(cat);
		List<Map> ret = runner.query(sql);
		return ret;
	}
	public List<Map> getCatProductsByCatID(String id)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product where CatID ="+DbUtil.escSql(id);
		List<Map> ret = runner.query(sql);
		return ret;
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
		if(m==null)return false;
		return true;
	}
	public boolean productTitleExits_eng(String title) throws SQLException {
		ProductDTO u=new ProductDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product_eng WHERE Title = " + DbUtil.escSql(title);
		Map m=runner.queryForMap(sql);
		if(m==null)return false;
		return true;
	}

	public boolean productTitleExits_big(String title) throws SQLException {
		ProductDTO u=new ProductDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product_big WHERE Title = " + DbUtil.escSql(title);
		Map m=runner.queryForMap(sql);
		if(m==null)return false;
		return true;
	}
	public  ProductDTO getProductByID(String id) throws SQLException{
		ProductDTO u=new ProductDTO();
		Map m=getMapByID(id);
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
	
	public ProductDTO getProductByID_eng(String id) throws SQLException {
		ProductDTO u=new ProductDTO();
		Map m=getMapByID_eng(id);
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

	public ProductDTO getProductByID_big(String id) throws SQLException {
		ProductDTO u=new ProductDTO();
		Map m=getMapByID_big(id);
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
	public  Map getMapByID(String id) throws SQLException{
		ProductDTO u=new ProductDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product WHERE ID = " + DbUtil.escSql(id);
		return runner.queryForMap(sql);
	}
	private Map getMapByID_eng(String id) throws SQLException {
		ProductDTO u=new ProductDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product_eng WHERE ID = " + DbUtil.escSql(id);
		return runner.queryForMap(sql);
	}
	private Map getMapByID_big(String id) throws SQLException {
		ProductDTO u=new ProductDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product_big WHERE ID = " + DbUtil.escSql(id);
		return runner.queryForMap(sql);
	}
	public  ProductDTO insertProduct(ProductDTO p) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="insert into product(Title,Description,Category,SubCategory,CatID";
		sql = sql+") values ("+DbUtil.escSql(p.getTitle().trim())+","+DbUtil.escSql(p.getDescription().trim())+","+DbUtil.escSql(p.getCategory())+","+DbUtil.escSql(p.getSubcategory())+","+DbUtil.escSql(p.getCatID());
		sql=sql+");";
		runner.update(sql);
		Map m= runner.queryForMap("select ID from product where Title="+DbUtil.escSql(p.getTitle()));
		p.setId(((Integer)m.get("ID")).toString());
		return p;
	}
	public  ProductDTO insertProduct_big(ProductDTO p) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="insert into product_big(ID,Title,Description,Category,SubCategory,CatID";
		sql = sql+") values ("+DbUtil.escSql(p.getId().trim())+","+DbUtil.escSql(p.getTitle().trim())+","+DbUtil.escSql(p.getDescription().trim())+","+DbUtil.escSql(p.getCategory())+","+DbUtil.escSql(p.getSubcategory())+","+DbUtil.escSql(p.getCatID());
		sql=sql+");";
		runner.update(sql);
		return p;
	}
	public  ProductDTO insertProduct_eng(ProductDTO p) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="insert into product_eng(ID,Title,Description,Category,SubCategory,CatID";
		sql = sql+") values ("+DbUtil.escSql(p.getId().trim())+","+DbUtil.escSql(p.getTitle().trim())+","+DbUtil.escSql(p.getDescription().trim())+","+DbUtil.escSql(p.getCategory())+","+DbUtil.escSql(p.getSubcategory())+","+DbUtil.escSql(p.getCatID());
		sql=sql+");";
		runner.update(sql);

		return p;
	}
	public  void updateProduct(ProductDTO p) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="update product set Title="+DbUtil.escSql(p.getTitle())+",Description="+DbUtil.escSql(p.getDescription());
		sql+=",Category="+DbUtil.escSql(p.getCategory())+",SubCategory="+DbUtil.escSql(p.getSubcategory())+",CatID="+DbUtil.escSql(p.getCatID())+" where ID="+DbUtil.escSql(p.getId());
		runner.update(sql);
	}
	

	public  void updateProduct_big(ProductDTO p) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="update product_big set Title="+DbUtil.escSql(p.getTitle())+",Description="+DbUtil.escSql(p.getDescription());
		sql+=",Category="+DbUtil.escSql(p.getCategory())+",SubCategory="+DbUtil.escSql(p.getSubcategory())+",CatID="+DbUtil.escSql(p.getCatID())+" where ID="+DbUtil.escSql(p.getId());
		runner.update(sql);
	}

	public  void updateProduct_eng(ProductDTO p) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="update product_eng set Title="+DbUtil.escSql(p.getTitle())+",Description="+DbUtil.escSql(p.getDescription());
		sql+=",Category="+DbUtil.escSql(p.getCategory())+",SubCategory="+DbUtil.escSql(p.getSubcategory())+",CatID="+DbUtil.escSql(p.getCatID())+" where ID="+DbUtil.escSql(p.getId());
		runner.update(sql);
	}
	
	public void deleteProduct(String id) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "delete FROM product WHERE ID = " + DbUtil.escSql(id);
		runner.update(sql);
	}
	
	
	
	private List<Map> getAllCategories()throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT distinct Category FROM product";
		return runner.query(sql);
	}
	
	private List<Map> getSubCategories(String category)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT distinct CatID, SubCategory FROM product where Category="+DbUtil.escSql(category);
		return runner.query(sql);
	}
	public Map<String,List<Map>> getAllCats() throws SQLException{
		Map<String,List<Map>> ret = new HashMap<String,List<Map>>();
		List<Map> cats = getAllCategories();
		for(Map m:cats){
			String catName = (String)m.get("Category");
			List<Map> subcats = getSubCategories(catName);
			ret.put(catName, subcats);
		}
		return ret;
	}

	public List<Map> getAllProducts_big() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product_big";
		return runner.query(sql);
	}
	public List<Map> getAllProducts_eng() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product_eng";
		return runner.query(sql);
	}


}
