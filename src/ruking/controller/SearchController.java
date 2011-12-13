package ruking.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.velocity.VelocityContext;

import ruking.ba.GlobalVariablesBA;
import ruking.dao.ProductDAO;
import ruking.utils.Util;
import ruking.velocity.VelocityParserFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SearchController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        VelocityContext vc=new VelocityContext();
        String searchKeyword = Util.getNoNull(request.getParameter("searchword"));
        if(!searchKeyword.equals("")){
        	
        }
        new GlobalVariablesBA().setCommonVariables(request, vc);
        VelocityParserFactory.getVP().render("search", vc, request, response);
	}
	   private String getJA(VelocityContext vc,String keywords,String lang) throws SQLException{
	       ProductDAO productDAO = new ProductDAO((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbUser"),(String)vc.get("dbPWD"));
	       JSONArray ret = new JSONArray();
		   List<Map> result=null;
       		result = productDAO.getAllProducts(lang);
			if(result.size()>0){
				for(Map m:result){
					JSONObject jo = JSONObject.fromObject(m);
					ret.add(jo);
				}
			}
		   return ret.toString();
	   }
}