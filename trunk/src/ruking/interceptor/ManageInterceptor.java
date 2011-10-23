package ruking.interceptor;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ruking.db.DataSourceFactory;
import ruking.db.MDTMySQLRowMapper;
import ruking.dto.SessionDTO;
import ruking.log.SessionLogger;
import ruking.session.SessionName;
import ruking.session.SessionUtil;
import ruking.utils.Conf;
import ruking.utils.Util;

public class ManageInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
		Conf conf = new Conf();
		// check session, if not available, create one
		String dbName = conf.getDbName();
		String dbPWD = conf.getDbPassword();
		String hostName = conf.getHostName();
		String dbUser = conf.getDbUser();
		SessionUtil sessUtil = new SessionUtil(DataSourceFactory.getDataSource(hostName,dbName,dbUser,dbPWD), new MDTMySQLRowMapper());
    	String sessId = sessUtil.getSessIdFromCookie(request);
    	SessionDTO sessDTO = sessUtil.readSessDTO(sessId);
    	if (sessDTO == null)
    	{
    		return true;
    	}
		return true;
	}
}
