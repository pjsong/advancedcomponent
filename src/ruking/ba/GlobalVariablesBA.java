package ruking.ba;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.context.Context;

import ruking.dto.UserSignUpDTO;
import ruking.session.SessionName;
import ruking.session.SessionUtil;
import ruking.utils.Conf;
import ruking.utils.EscapeTool;
import ruking.utils.RegExp;
import ruking.utils.Util;
import ruking.velocity.MathTool;
import ruking.velocity.VelocityUtil;



public class GlobalVariablesBA {
	public void setCommonVariables(HttpServletRequest request, Context ctx) throws Exception
	{
        // regular expression class used for displaying price
        ctx.put("formatter", new Formatter());
        ctx.put("regExp", new RegExp());
        ctx.put("math", new MathTool());
        ctx.put("esc", new EscapeTool());
        ctx.put("velocityUtil", new VelocityUtil());

        // image server
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	    String currYear = sdf.format(new Date());
        ctx.put("currYear", currYear);
        Conf conf=new Conf();
        ctx.put("dbHost", conf.getHostName());
        ctx.put("dbName", conf.getDbName());
        ctx.put("dbUser", conf.getDbUser());
        ctx.put("dbPWD", conf.getDbPassword());
        
		// get session
    	Map<String, Object> sessData = (Map<String, Object>) request.getAttribute(SessionUtil.SESS_DATA);
    	if(sessData!=null){
		UserSignUpDTO sessionCustomerDTO = (UserSignUpDTO) sessData.get(SessionName.customerDTO);
		if(sessionCustomerDTO != null)
		{
		    ctx.put("customer", sessionCustomerDTO);
		    if(sessionCustomerDTO.getLoginName().equals("彭劲松"))
		    	ctx.put("administrator", true);
		    String username = sessionCustomerDTO.getLoginName();
	    	if(Util.isAdministrator(username))
	    	{
	    		ctx.put("adminflag", "true");
	    	}
		}
    	}
		String s1 = request.getRequestURL().toString();
		String s2=request.getRequestURI().substring(0);
		String s3=request.getContextPath();
		String s4 = s1.substring(0,8)+s1.substring(8).replace(s2, s3);
        ctx.put("_url", s4);
        
		if(s2.length()>1 && !s2.equals("/index.jhtml"))
			ctx.put("breadcrum", getBreadCrum(request));
	}
	
	private String getBreadCrum(HttpServletRequest request){
		String uri = request.getRequestURI().substring(1);
		if("reg.jhtml".equals(uri))
			return "<a href = '/index.jhtml'>首页</a> > <a href='reg.jhtml' > 注册</a>";
		if("about/aboutus.jhtml".equals(uri))
			return "<a href = '/about/about.jhtml'>首页</a> > <a href='/about/aboutus.jhtml' > 关于如金</a>";
		if("about/customerservice.jhtml".equals(uri))
			return "<a href = '/index.jhtml'>首页</a> > <a href='customerservice.jhtml' > 客服中心</a>";
		if("products.jhtml".equals(uri))
			return "<a href = '/index.jhtml'>首页</a> > <a href='products.jhtml' > 产品浏览</a>";
		if("item.jhtml".equals(uri))
			return "<a href = '/index.jhtml'>首页</a> > <a href='products.jhtml' > 产品浏览</a> > <a href='item.jhtml' > 产品详细</a>";
		if("solution.jhtml".equals(uri))
			return "<a href = '/index.jhtml'>首页</a> > <a href='solution.jhtml' > 解决方案</a>";
		if("news.jhtml".equals(uri))
			return "<a href = '/index.jhtml'>首页</a> > <a href='news.jhtml' > 产业资讯</a>";
		return "<a href = '/index.jhtml'>首页</a>";
	}
}
