package ruking.controller.manage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;

import ruking.ba.GlobalVariablesBA;
import ruking.controller.BaseController;
import ruking.dao.UserSignUpDAO;
import ruking.db.DataSourceFactory;
import ruking.db.MDTMySQLRowMapper;
import ruking.dto.UserSignUpDTO;
import ruking.session.SessionUtil;
import ruking.utils.Util;
import ruking.velocity.VelocityParserFactory;

public class EditUserController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        VelocityContext vc=new VelocityContext();
        new GlobalVariablesBA().setCommonVariables(request, vc);
		SessionUtil sessUtil = new SessionUtil(DataSourceFactory.getDataSource((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbName"),(String)vc.get("dbPWD")), new MDTMySQLRowMapper());
    	Map<String, Object> sessData = (Map<String, Object>) request.getAttribute(SessionUtil.SESS_DATA);
    	if(vc.get("administrator")==null){
    		VelocityParserFactory.getVP().render("index", vc, request, response);
    	}else{
    		vc.put("currentTab", "user");
    		String loginName = Util.getNoNull(request.getParameter("name"));
    		if("".equals(loginName))return;
    		UserSignUpDTO user = new UserSignUpDAO((String)vc.get("hostName"),(String)vc.get("dbName"),(String)vc.get("dbUser"),(String)vc.get("dbPWD")).getUserByLoginName(loginName);
    		vc.put("user", user);
    		VelocityParserFactory.getVP().render("edituser", vc, request, response);
        }
	}
}
