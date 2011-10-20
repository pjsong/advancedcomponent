package ruking.interceptor;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import db.DataSourceFactory;
import db.MDTMySQLRowMapper;
import db.RowMapper;

import ruking.dto.SessionDTO;
import ruking.log.SessionLogger;
import ruking.session.SessionName;
import ruking.session.SessionUtil;
import ruking.utils.Util;


public class SessionInterceptor extends HandlerInterceptorAdapter 
{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
		String relativeUrl = Util.getRelativeUrl(request);
		
		// skip the search suggest
		if (relativeUrl.startsWith("/do/dosuggest"))
		{
			return true;
		}
		
		boolean firstVisit = false;
		
		// check session, if not available, create one
		SessionUtil sessUtil = new SessionUtil(DataSourceFactory.getDataSource("",""), new MDTMySQLRowMapper());
    	String sessId = sessUtil.getSessIdFromCookie(request);
    	
    	SessionDTO sessDTO = sessUtil.readSessDTO(sessId);
    	if (sessDTO == null)
    	{
    		sessId = sessUtil.createSession(request, response);
        	sessDTO = sessUtil.readSessDTO(sessId);
        	firstVisit = true;
    	}

    	// this is very important to keep the session fresh
    	sessUtil.updateLastUpdatedField(sessId);

    	Map<String, Object> sessData = sessDTO.getSessData();
    	Date lastUpdated = sessDTO.getLastUpdated();

    	if (firstVisit)
    	{
    		SessionLogger.log(sessId, lastUpdated, relativeUrl, Util.getIP(request), Util.getReferralUrl(request));

    		// set entry relative url here
	    	sessUtil.putAndWrite(request, sessData,SessionName.entryRelativeUrl, relativeUrl);
    		
        	//set REFFERAL_BASE_URL and REFFERAL_EXTENDED_URL into session
    		String referralBaseUrl = Util.getReferralBaseUrl(request);
    		if(referralBaseUrl == null)
    		{
    			referralBaseUrl = "";
    		}
   			sessUtil.putAndWrite(request, sessData,SessionUtil.REFERRAL_BASE_URL, referralBaseUrl);
    			
   			// store referral extended url
    		String referralExtendedUrl = Util.getReferralExtendedUrl(request);    		
    		if(referralExtendedUrl == null)
    		{
    			referralExtendedUrl = "";
    		}
    		sessUtil.putAndWrite(request, sessData,SessionUtil.REFERRAL_EXTENDED_URL,referralExtendedUrl); 
    	}
    	else
    	{
    		SessionLogger.log(sessId, lastUpdated, relativeUrl, "", "");
    	}
    	
    	// store sessId into request
    	request.setAttribute(SessionUtil.SESS_ID, sessId);
    	request.setAttribute(SessionUtil.SESS_DATA, sessData);
    	
		return true;
	}
}