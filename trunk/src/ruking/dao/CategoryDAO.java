package ruking.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ruking.db.DataSourceFactory;
import ruking.db.DbUtil;
import ruking.db.MDTMySQLRowMapper;
import ruking.db.QueryRunner;
import ruking.db.TransRunner;
import ruking.dto.CategoryDTO;
import ruking.dto.ProductDTO;
import ruking.utils.Conf;

public class CategoryDAO {
	public String hostName;
	public String dbName ;//= "zkm0m1_db";
	public String password ;//= "pjsong";
	public String dbUser;
    Conf conf=new Conf();

	public CategoryDAO() throws IOException {
		super();
		this.hostName = conf.getHostName();
		this.dbName = conf.getDbName();
		this.dbUser = conf.getDbUser();
		this.password = conf.getDbPassword();
	}
	private  Map getMapByID(String id,String lang) throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM category WHERE ID = " + DbUtil.escSql(id);
		if("eng".equals(lang))
			sql = "SELECT * FROM category_eng WHERE ID = " + DbUtil.escSql(id);
		if("big".equals(lang))
			sql = "SELECT * FROM category_big WHERE ID = " + DbUtil.escSql(id);
		return runner.queryForMap(sql);
	}
	public  CategoryDTO getCategoryByID(String id,String lang) throws SQLException{
		CategoryDTO u=new CategoryDTO();
		Map m=getMapByID(id,lang);
		if(m==null)return null;
		else{
			u.setId(((Integer)m.get("ID")).toString());
			u.setCategory((String)m.get("Category"));
			u.setSubcategory((String)m.get("SubCategory"));
		}
		return u;
	}
	public void deleteCategory(String id) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "delete FROM Category WHERE ID = " + DbUtil.escSql(id);
		runner.update(sql);
	}
	
	public List<Map> getAllCategories(String lang)throws SQLException, IOException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT * FROM category";
		if("eng".equals(lang))sql = "SELECT * FROM category_eng";
		if("big".equals(lang))sql = "SELECT * FROM category_big";
		return runner.query(sql);
	}
	
	
	private List<Map> getSubCategories(String category,String lang)throws SQLException{
		QueryRunner runner = new QueryRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql = "SELECT SubCategory FROM category where Category="+DbUtil.escSql(category);
		if("eng".equals(lang))
			sql="SELECT SubCategory FROM category_eng where Category="+DbUtil.escSql(category);
		if("big".equals(lang))
			sql="SELECT SubCategory FROM category_big where Category="+DbUtil.escSql(category);
		return runner.query(sql);
	}
	

	public Map<String,List<Map>> getAllCats(String lang) throws SQLException, IOException{
		Map<String,List<Map>> ret = new HashMap<String,List<Map>>();
		List<Map> cats = getAllCategories(lang);
		for(Map m:cats){
			String catName = (String)m.get("Category");
			List<Map> subcats = getSubCategories(catName,lang);
			ret.put(catName, subcats);
		}
		return ret;
	}
	public  CategoryDTO insertCategory(CategoryDTO p,String lang) throws SQLException{
		TransRunner runner = new TransRunner(DataSourceFactory.getDataSource(hostName,dbName,dbUser,password), new MDTMySQLRowMapper());
		String sql="insert into category(Category,SubCategory";
		sql = sql+") values ("+DbUtil.escSql(p.getCategory().trim())+","+DbUtil.escSql(p.getSubcategory().trim());
		sql=sql+");";
		if("eng".equals(lang)){
			sql="insert into category_eng(Category,SubCategory";
			sql = sql+") values ("+DbUtil.escSql(p.getId().trim())+","+DbUtil.escSql(p.getCategory().trim())+","+DbUtil.escSql(p.getSubcategory().trim());
			sql=sql+");";
		}
		if("big".equals(lang)){
			sql="insert into category_big(Category,SubCategory";
			sql = sql+") values ("+DbUtil.escSql(p.getId().trim())+","+DbUtil.escSql(p.getCategory().trim())+","+DbUtil.escSql(p.getSubcategory().trim());
			sql=sql+");";
		}
		runner.update(sql);
		Map m= runner.queryForMap("select ID from category where Category="+DbUtil.escSql(p.getCategory()));
		if(m!=null)p.setId(((Integer)m.get("ID")).toString());
		return p;
	}

}
