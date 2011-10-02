package controller;


import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;

public class CookieController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//    	request.setCharacterEncoding("ISO8859-1");
//    	response.setContentType("text/html; charset=ISO8859-1");
        String now = (new Date()).toString();
        HashMap map=new HashMap<String,String>();
        if(request.getCookies()!=null && request.getCookies().length>0)
        	map.put("userName", request.getCookies()[0].getValue());
        return new ModelAndView("index0",map);
    }
}