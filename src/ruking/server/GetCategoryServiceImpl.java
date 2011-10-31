package ruking.server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ruking.dao.ProductDAO;
import ruking.utils.Conf;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//http://blog.csdn.net/leoyunfei/article/details/3611565

public class GetCategoryServiceImpl extends HttpServlet {
	   @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        JSONArray ja = null;
			try {
				ja = getJA();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        resp.setCharacterEncoding("utf-8");
	        resp.getWriter().write(ja==null?"":ja.toString());
	    }
	   
	   private JSONArray getJA() throws SQLException, IOException{
	        Conf conf=new Conf();
	        ProductDAO paDAO = new ProductDAO(conf.getHostName(),conf.getDbName(),conf.getDbUser(),conf.getDbPassword());
	       JSONArray ret = new JSONArray();
	       Map<String,List<String>> result = paDAO.getAllCats();
			if(result.size()>0){
				int pos=0;
				for(String m:result.keySet()){
					JSONObject jo = new JSONObject();
					jo.put("CatName", m);
					List<String> arr = result.get(m);
					JSONArray catArray= new JSONArray();
					for(int i=0;i<arr.size();i++){
						catArray.add((String)(arr.get(i)));
					}
					jo.put("CatArray", catArray);
					ret.add(jo); //ret.add(formatJO(jo,m));
					pos++;
				}
				}
		   return ret;
	   }
}
