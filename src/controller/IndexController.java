package controller;


import org.apache.velocity.VelocityContext;

import velocity.VelocityParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IndexController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception{
        VelocityContext vc=new VelocityContext();
        VelocityParser.render("index", vc, request, response);
	}

}