package test.server;

//import java.security.Principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import test.client.UService;
import test.client.shared.dto.UserSignupInfoDTO;
import test.server.jdo.PMF;
import test.server.jdo.UserSignupInfo;
import test.server.utils.Utils;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UServiceImpl extends RemoteServiceServlet implements UService{
	public String getGoogleUserId() {
	    UserService userService = UserServiceFactory.getUserService();
	    User user=userService.getCurrentUser();
	    if(user == null)return "";
		return user.getUserId();
	}
	public String getGoogleUserLoginURL() {
		UserService userService = UserServiceFactory.getUserService();
		return userService.createLoginURL("/");
	}
	public String getUserLoginForPasswordURL() {
		UserService userService = UserServiceFactory.getUserService();
		if(getGoogleUserId()!=null&&!getGoogleUserId().equals("")){
		return userService.createLoginURL("/#list");
		}else
			return userService.createLoginURL("/");
	}
	public String getGoogleUserLogoutURL() {
	    HttpServletRequest request = this.getThreadLocalRequest();
	    UserService userService = UserServiceFactory.getUserService();
//		return userService.createLogoutURL(request.getRequestURI());
		return userService.createLogoutURL("/");
	}
	public String getGoogleUserName() throws IllegalArgumentException {
		HttpSession session = this.getThreadLocalRequest().getSession();
		HashMap map=Utils.getHashMapFromSession("user", session);
		Object user=map.get("userSignupInfo");
		if(user==null||"".equals(user.toString()))return null;
		else return ((UserSignupInfoDTO)user).getLoginName();
	}
	public String getGoogleUserNickName() {
	    UserService userService = UserServiceFactory.getUserService();
	    User user=userService.getCurrentUser();
	    if(user == null)return "";
		return user.getNickname();
	}
	public ArrayList<String> getValidAccounts(){
		return new ArrayList<String>();
	}
	public Boolean userExists(String email) {
		if(email==null||"".equals(email))return null;
    	PersistenceManager pm = PMF.get().getPersistenceManager(); 
    	String jdoql = "select from " + UserSignupInfo.class.getName()+" where emailAddress == \""+ email +"\""; 
    	Query query =pm.newQuery(jdoql);
    	try{
    	List<UserSignupInfo> results = (List<UserSignupInfo>) query.execute();
    	if (results.iterator().hasNext())return true;
        else  return false;
        } catch(Exception e){
        	return false;
        }finally { 
        query.closeAll(); 
        }
	}
	public Boolean storeUserSignupInfo(UserSignupInfoDTO user){
		UserSignupInfo u=new UserSignupInfo();
		u.setAddress(user.getAddress());
		u.setEmailAddress(user.getEmailAddress());
		u.setFullName(user.getLoginName());
		u.setPassword(user.getLoginPassword());
		u.setPhoneNum(user.getPhoneNum());
		u.setLatlng(user.getLatlng());
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
        try { 
            pm.makePersistent(u); 
			Utils.addUserSignupInfoToSession("user",getThreadLocalRequest().getSession(),user);
            return true;
        } catch(Exception e){
        	return false;
        }finally { 
            pm.close(); 
        } 
	}
	public Boolean userLoggedin(String userName,String password){
    	PersistenceManager pm = PMF.get().getPersistenceManager(); 
    	String jdoql = "select from " + UserSignupInfo.class.getName()+" where fullName == \""+ userName +"\" "+ "&& password == \""+password+"\""; 
    	Query query =pm.newQuery(jdoql);
    	try{
    	List<UserSignupInfo> results = (List<UserSignupInfo>) query.execute();
    	if (results.iterator().hasNext()){
    		UserSignupInfoDTO u=new UserSignupInfoDTO();
    		u.setAddress(results.get(0).getAddress());
    		u.setEmailAddress(results.get(0).getEmailAddress());
    		u.setLoginName(results.get(0).getFullName());
    		u.setLatlng(results.get(0).getLatlng());
    		u.setLoginPassword(results.get(0).getPassword());
    		u.setPhoneNum(results.get(0).getPhoneNum());
			Utils.addUserSignupInfoToSession("user",getThreadLocalRequest().getSession(),u);
    		return true;
    		}
        else  return false;
        } catch(Exception e){
        	return false;
        }finally { 
        query.closeAll(); 
        }
	}
	public void removeUserInfoSignup() {
             Utils.removeUserSignupInfoFromSession("user", getThreadLocalRequest().getSession());		
	}
}
