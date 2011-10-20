package ruking.controller;


import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.springframework.web.bind.ServletRequestDataBinder;

import ruking.ba.GlobalVariablesBA;
import ruking.dto.UserSignUpDTO;
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
		if(act.equals("regact")){
	        VelocityParserFactory.getVP().render("registerYes", vc, request, response);
	        return;
		}
	}
	protected void registerUser(HttpServletRequest request, HttpServletResponse response){
		UserSignUpDTO userSignUpDTO = new UserSignUpDTO();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(userSignUpDTO, "userSignUpDTO");
		binder.bind(request);
		Map<String,String> error=check(userSignUpDTO);
	}
	
	private Map<String,String> check(UserSignUpDTO u){
		Map<String,String> ret = new HashMap<String,String>();
		if(Util.getNoNull(u.getLoginName()).trim().equals(""))ret.put("loginNameError", "请填写登录名");
		if(Util.getNoNull(u.getPassword()).trim().equals(""))ret.put("passwordError", "请填写密码");
		if(Util.getNoNull(u.getPasswordV()).trim().equals(""))ret.put("passwordVError", "请确认密码");
		if(!Util.getNoNull(u.getPassword()).equals(Util.getNoNull(u.getPasswordV())))ret.put("passwordAndVError", "密码确认有误，请重新填写密码并确认");
		if(Util.getNoNull(u.getQuestion()).trim().equals("-1"))ret.put("questionError", "请选择用于找回密码的提示问题");
		if(Util.getNoNull(u.getLoginName()).trim().equals(""))ret.put("answerError", "请填写问题答案");
		if(Util.getNoNull(u.getQuestion().trim()).equals("birthdayOfMom") && !Util.getNoNull(u.getAnswer()).matches("\\d\\d\\d\\d-\\d\\d-\\d\\d"))ret.put("birthdayError", "生日输入格式有误");
		if(Util.getNoNull(u.getEmail()).trim().equals(""))ret.put("loginNameError", "请填写邮箱");
		if(Util.getNoNull(u.getLoginName()).trim().equals(""))ret.put("loginNameError", "请填写登录名");
		if(Util.getNoNull(u.getLoginName()).trim().equals(""))ret.put("loginNameError", "请填写登录名");
		if(Util.getNoNull(u.getLoginName()).trim().equals(""))ret.put("loginNameError", "请填写登录名");
		if(Util.getNoNull(u.getLoginName()).trim().equals(""))ret.put("loginNameError", "请填写登录名");
		
		return ret;
	}
}