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
	
	public List<Map> getGlobalCatProducts(int id,String lang)throws SQLException{
		List<Map> ret = new ArrayList<Map>();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT ProductIDs FROM globalcat where ID="+DbUtil.escSql(id);
		Map map = runner.queryForMap(sql);
		if(map == null)return ret;
		String str = (String)map.get("ProductIDs");
		if(str==null || str.equals(""))return null;
		String[] ids = str.split(",");
		for(String pid:ids){
			Map p = getMapByID(pid,lang);
			ret.add(p);
		}
		return formatProductMap(ret,lang);
	}
//	public List<Map> getCatProducts(String cat)throws SQLException{
//		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
//		String sql = "SELECT * FROM product where SubCategory ="+DbUtil.escSql(cat);
//		List<Map> ret = runner.query(sql);
//		return ret;
//	}
	public List<Map> getCatProductsByCatID(String id,String lang)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product where CatID ="+DbUtil.escSql(id);
		if("eng".equals(lang))sql="SELECT * FROM product_eng where CatID ="+DbUtil.escSql(id);
		if("big".equals(lang))sql="SELECT * FROM product_big where CatID ="+DbUtil.escSql(id);
		return formatProductMap(runner.query(sql),lang);
	}
	
	public List<Map> getAllProducts(String lang)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product";
		if("eng".equals(lang))sql = "SELECT * FROM product_eng";
		if("big".equals(lang))sql = "SELECT * FROM product_big";
		return formatProductMap(runner.query(sql),lang);
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
	public  boolean productTitleExits(String title,String lang) throws SQLException{
		ProductDTO u=new ProductDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product WHERE Title = " + DbUtil.escSql(title);
		if("eng".equals(lang))
			sql = "SELECT * FROM product_eng WHERE Title = " + DbUtil.escSql(title);
		if("big".equals(lang))
			sql = "SELECT * FROM product_big WHERE Title = " + DbUtil.escSql(title);
		Map m=runner.queryForMap(sql);
		if(m==null)return false;
		return true;
	}

	public  ProductDTO getProductByID(String id,String lang) throws SQLException{
		ProductDTO u=new ProductDTO();
		Map m=getMapByID(id,lang);
		if(m==null)return null;
		else{
			u.setId(((Integer)m.get("ID")).toString());
			u.setTitle((String)m.get("Title"));
			u.setDescription((String)m.get("Description"));
			u.setCategory((String)m.get("Category"));
			u.setSubcategory((String)m.get("SubCategory"));
			u.setCatID(((Integer)m.get("CatID")).toString());
		}
		return u;
	}
	
	private  Map getMapByID(String id,String lang) throws SQLException{
		ProductDTO u=new ProductDTO();
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM product WHERE ID = " + DbUtil.escSql(id);
		if("eng".equals(lang))
			sql = "SELECT * FROM product_eng WHERE ID = " + DbUtil.escSql(id);
		if("big".equals(lang))
			sql = "SELECT * FROM product_big WHERE ID = " + DbUtil.escSql(id);
		return runner.queryForMap(sql);
	}
	
	public  ProductDTO insertProduct(ProductDTO p,String lang) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="insert into product(Title,Description,Category,SubCategory,CatID";
		sql = sql+") values ("+DbUtil.escSql(p.getTitle().trim())+","+DbUtil.escSql(p.getDescription().trim())+","+DbUtil.escSql(p.getCategory())+","+DbUtil.escSql(p.getSubcategory())+","+DbUtil.escSql(p.getCatID());
		sql=sql+");";
		if("eng".equals(lang)){
			sql="insert into product_eng(ID,Title,Description,Category,SubCategory,CatID";
			sql = sql+") values ("+DbUtil.escSql(p.getId().trim())+","+DbUtil.escSql(p.getTitle().trim())+","+DbUtil.escSql(p.getDescription().trim())+","+DbUtil.escSql(p.getCategory())+","+DbUtil.escSql(p.getSubcategory())+","+DbUtil.escSql(p.getCatID());
			sql=sql+");";
		}
		if("big".equals(lang)){
			sql="insert into product_big(ID,Title,Description,Category,SubCategory,CatID";
			sql = sql+") values ("+DbUtil.escSql(p.getId().trim())+","+DbUtil.escSql(p.getTitle().trim())+","+DbUtil.escSql(p.getDescription().trim())+","+DbUtil.escSql(p.getCategory())+","+DbUtil.escSql(p.getSubcategory())+","+DbUtil.escSql(p.getCatID());
			sql=sql+");";
		}
		runner.update(sql);
		Map m= runner.queryForMap("select ID from product where Title="+DbUtil.escSql(p.getTitle()));
		if(m!=null)p.setId(((Integer)m.get("ID")).toString());
		return p;
	}

	public  void updateProduct(ProductDTO p,String id,String lang) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="update product set Title="+DbUtil.escSql(p.getTitle())+",Description="+DbUtil.escSql(p.getDescription());
		sql+=",Category="+DbUtil.escSql(p.getCategory())+",SubCategory="+DbUtil.escSql(p.getSubcategory())+",CatID="+DbUtil.escSql(p.getCatID())+" where ID="+DbUtil.escSql(p.getId());
		if("eng".equals(lang)){
			sql="update product_eng set ID="+DbUtil.escSql(p.getId())+", Title="+DbUtil.escSql(p.getTitle())+",Description="+DbUtil.escSql(p.getDescription());
			sql+=",Category="+DbUtil.escSql(p.getCategory())+",SubCategory="+DbUtil.escSql(p.getSubcategory())+",CatID="+DbUtil.escSql(p.getCatID())+" where ID="+DbUtil.escSql(id);
		}
		if("big".equals(lang)){
			sql="update product_big set ID="+DbUtil.escSql(p.getId())+", Title="+DbUtil.escSql(p.getTitle())+",Description="+DbUtil.escSql(p.getDescription());
			sql+=",Category="+DbUtil.escSql(p.getCategory())+",SubCategory="+DbUtil.escSql(p.getSubcategory())+",CatID="+DbUtil.escSql(p.getCatID())+" where ID="+DbUtil.escSql(id);
		}
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
	
	private List<Map> getSubCategories(String category,String lang)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT distinct CatID, SubCategory FROM product where Category="+DbUtil.escSql(category);
		if("eng".equals(lang))
			sql="SELECT distinct CatID, SubCategory FROM product_eng where Category="+DbUtil.escSql(category);
		if("big".equals(lang))
			sql="SELECT distinct CatID, SubCategory FROM product_big where Category="+DbUtil.escSql(category);
		return runner.query(sql);
	}
	
	private List<Map> getAllCategories(String lang)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT distinct Category FROM product";
		if("eng".equals(lang))sql+="_eng";
		if("big".equals(lang))sql+="_big";
		return runner.query(sql);
	}

	public Map<String,List<Map>> getAllCats(String lang) throws SQLException{
		Map<String,List<Map>> ret = new HashMap<String,List<Map>>();
		List<Map> cats = getAllCategories(lang);
		for(Map m:cats){
			String catName = (String)m.get("Category");
			List<Map> subcats = getSubCategories(catName,lang);
			ret.put(catName, subcats);
		}
		return ret;
	}
	private List<Map> formatProductMap(List<Map> lm,String lang) throws SQLException{
		for(Map m:lm){
			Integer id = (Integer)m.get("ID");
			AttributeDAO attrDAO = new AttributeDAO(hostName,dbName,dbUser,password);
			String value = attrDAO.addModelNameToTitleByProductId(id.toString(),lang);
			if(value!=null && value.length() > 1)
			m.put("Title", (value+(String)m.get("Title")));
		}
		return lm;
	}
}
