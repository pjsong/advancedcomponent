package ruking.controller.manage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;

import ruking.ba.GlobalVariablesBA;
import ruking.controller.BaseController;
import ruking.dao.ProductDAO;
import ruking.db.DataSourceFactory;
import ruking.db.MDTMySQLRowMapper;
import ruking.session.SessionUtil;
import ruking.velocity.VelocityParserFactory;

public class ListSolutionsController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        VelocityContext vc=new VelocityContext();
        new GlobalVariablesBA().setCommonVariables(request, vc);
		SessionUtil sessUtil = new SessionUtil(DataSourceFactory.getDataSource((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbName"),(String)vc.get("dbPWD")), new MDTMySQLRowMapper());
    	Map<String, Object> sessData = (Map<String, Object>) request.getAttribute(SessionUtil.SESS_DATA);
    		vc.put("currentTab", "solution");
//    		List<Map> products = new ProductDAO((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbUser"),(String)vc.get("dbPWD")).getAllProducts();
//    		vc.put("products", products);
//    		VelocityParserFactory.getVP().render("listusers", vc, request, response);

	}
}