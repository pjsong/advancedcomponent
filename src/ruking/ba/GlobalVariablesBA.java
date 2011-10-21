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
import ruking.utils.RegExp;
import ruking.utils.Util;
import ruking.velocity.EscapeTool;
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
        ctx.put("dbName", conf.getDbName());
        ctx.put("dbPWD", conf.getDbPassword());
        
		// get session
    	Map<String, Object> sessData = (Map<String, Object>) request.getAttribute(SessionUtil.SESS_DATA);
    	if(sessData!=null){
		UserSignUpDTO sessionCustomerDTO = (UserSignUpDTO) sessData.get(SessionName.customerDTO);
		if(sessionCustomerDTO != null)
		{
		    ctx.put("customer", sessionCustomerDTO);

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

	}
}
