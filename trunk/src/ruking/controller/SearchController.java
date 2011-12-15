package ruking.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.apache.velocity.VelocityContext;

import ruking.ba.GlobalVariablesBA;
import ruking.dao.ProductDAO;
import ruking.search.SearchTool;
import ruking.utils.Util;
import ruking.velocity.VelocityParserFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SearchController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        VelocityContext vc=new VelocityContext();
        String searchKeyword = Util.getNoNull(request.getParameter("searchword"));
        if(!searchKeyword.equals("")){
        	String ja = getJA(vc,searchKeyword,"");
        	vc.put("searchResult", ja);
        	vc.put("searchKeyword", searchKeyword);
        }
        new GlobalVariablesBA().setCommonVariables(request, vc);
        VelocityParserFactory.getVP().render("search", vc, request, response);
	}
	   private String getJA(VelocityContext vc,String keywords,String lang) throws SQLException, CorruptIndexException, IOException, ParseException{
	       ProductDAO productDAO = new ProductDAO((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbUser"),(String)vc.get("dbPWD"));
	       JSONArray ret = new JSONArray();
		   List<Map> result= SearchTool.getResultLM(keywords,lang);
			if(result.size()>0){
				for(Map m:result){
					JSONObject jo = JSONObject.fromObject(m);
					ret.add(jo);
				}
			}
		   return ret.toString();
	   }
}