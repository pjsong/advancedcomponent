package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class BaseController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest req,HttpServletResponse res) throws Exception {
		process(req,res);
		return null;
	}

	public void process(HttpServletRequest req,HttpServletResponse res) throws Exception{
		
	}
}
