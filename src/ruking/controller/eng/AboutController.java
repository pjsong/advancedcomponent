package ruking.controller.eng;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;

import ruking.ba.GlobalVariablesBA;
import ruking.controller.BaseController;
import ruking.utils.Util;
import ruking.velocity.VelocityParserFactory;

public class AboutController extends BaseController{

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        VelocityContext vc=new VelocityContext();
        new GlobalVariablesBA().setCommonVariables(request, vc);
        VelocityParserFactory.getVP().render(urlTemplate(request), vc, request, response);
		
	}
    private String urlTemplate(HttpServletRequest request)
    {
		String relativeURL = request.getRequestURI();
    	String id =  request.getParameter("id");
    	if(!Util.getNoNull(id).equals("") && relativeURL.indexOf("aboutus")!=-1){
    		return "include/aboutus_eng/"+id;
    	}
    	if(!Util.getNoNull(id).equals("") && relativeURL.indexOf("customerservice")!=-1){
    		return "include/customerservice_eng/"+id;
    	}
    	else{
        	int startpos = relativeURL.lastIndexOf("about_eng/")+10;
        	return relativeURL.substring(startpos, relativeURL.length()-6);
    	}
    }
}
