package ruking.server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

import ruking.dao.ProductDAO;
import ruking.utils.Conf;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


//http://blog.csdn.net/leoyunfei/article/details/3611565

public class GetDTOServiceImpl extends HttpServlet {
	   @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   String s = req.getParameter("category");
	        if(s == null || s.equals(""))
	        	s="0";
	        JSONArray ja = null;
			try {
				ja = getJA(s);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        resp.setCharacterEncoding("utf-8");
	        resp.getWriter().write(ja==null?"":ja.toString());
	    }
	   
	   private JSONArray getJA(String category) throws SQLException, IOException{
	        Conf conf=new Conf();
	    	ProductDAO paDAO = new ProductDAO(conf.getHostName(),conf.getDbName(),conf.getDbUser(),conf.getDbPassword());
	       JSONArray ret = new JSONArray();
		   List<Map> result=null;
		   if(NumberUtils.isDigits(category)){
		        Integer globalcat = Integer.parseInt(category);
		        switch(globalcat){
		        case 0:{
			  	result = paDAO.getAllProducts();
			  	break;
		        }
		        default:{
		        	result = paDAO.getGlobalCatProducts(globalcat);
		        }
		    }}else{
		    	result = paDAO.getCatProducts(category);
		    }   
			if(result.size()>0){
				for(Map m:result){
					JSONObject jo = JSONObject.fromObject(m);
					ret.add(jo);
				}
			}
		   return ret;
	   }
//		private JSONObject formatJO(JSONObject jo,Map map){
//			if(jo.containsKey("ProductID")){
//				jo.remove("ProductID");
//				String id = ((Integer)map.get("ProductID")).toString();
//				jo.element("ProductID", id);
//			}
//			return jo;
//		}
}
