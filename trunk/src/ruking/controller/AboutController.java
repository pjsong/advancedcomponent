package ruking.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.springframework.web.servlet.ModelAndView;

import ruking.ba.GlobalVariablesBA;
import ruking.velocity.VelocityParserFactory;

public class AboutController extends BaseController{

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        VelocityContext vc=new VelocityContext();
        new GlobalVariablesBA().setCommonVariables(request, vc);
        String a = request.getRequestURI();
        VelocityParserFactory.getVP().render(urlTemplate(a), vc, request, response);
		
	}
    private String urlTemplate(String relativeURL)
    {
    	int startpos = relativeURL.lastIndexOf("about/")+6;
    	return relativeURL.substring(startpos, relativeURL.length()-6);
    }
}
