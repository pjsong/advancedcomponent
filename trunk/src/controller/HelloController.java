package controller;


import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

public class HelloController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap map=new HashMap<String,String>();
        return new ModelAndView(urlTemplate(request.getPathInfo()),map);
    }
    
    
    //simple mapping: remove relativeURL '/' and .html 
    private String urlTemplate(String relativeURL)
    {
    	HashMap<String,String> ret=new HashMap<String,String>();
    	if(relativeURL.lastIndexOf('/')==0)
    		ret.put("/", "/index");
    	else
    		ret.put(relativeURL.substring(1), relativeURL.substring(1, relativeURL.lastIndexOf('.')));
    	return ret.get(relativeURL);
    }
}