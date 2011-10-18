package ruking.controller;


import org.apache.velocity.VelocityContext;

import ruking.ba.GlobalVariablesBA;
import ruking.utils.Util;
import ruking.velocity.VelocityParserFactory;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
		VelocityContext vc=new VelocityContext();
		new GlobalVariablesBA().setCommonVariables(request, vc);
        String act=Util.getNoNull(request.getParameter("act"));
		if(act.equals("")){
        VelocityParserFactory.getVP().render("register", vc, request, response);
        return;
	}
		if(act.equals("regyes")){
	        VelocityParserFactory.getVP().render("registerYes", vc, request, response);
	        return;
		}
	}
}