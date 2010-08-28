package test.server.utils;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import test.client.shared.dto.UserSignupInfoDTO;

public class Utils {
	  public static HashMap getHashMapFromSession(String key, HttpSession session) {
		    if (session.getAttribute(key) == null) {
		      session.setAttribute(key, new HashMap());
		    }
		    return (HashMap) session.getAttribute(key);
		  }
	  public static void addUserSignupInfoToSession(String key, HttpSession session,UserSignupInfoDTO user) {
		    HashMap mapOfResults = Utils.getHashMapFromSession(key, session);
		    mapOfResults.put("userSignupInfo", user);
		  }
	  public static void removeUserSignupInfoFromSession(String key, HttpSession session) {
		    HashMap mapOfResults = Utils.getHashMapFromSession(key, session);
		    mapOfResults.put("userSignupInfo", null);
		  }
}
