package controller;


import org.apache.velocity.VelocityContext;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

public class HelloController extends BaseController {
	public void process(HttpServletRequest request, HttpServletResponse response){
        VelocityContext vc=new VelocityContext();
        return;
	}

}