package test.client;

import java.util.ArrayList;

import test.client.shared.dto.UserSignupInfoDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("user")
public interface UService extends RemoteService {
//	String getUserName() throws IllegalArgumentException;

	String getGoogleUserId();
	String getGoogleUserNickName();

	String getGoogleUserLoginURL();
	String getUserLoginForPasswordURL();
	String getGoogleUserLogoutURL();
	
	ArrayList<String> getValidAccounts();
	
	Boolean userExists(String email);
	
	Boolean userLoggedin(String userName,String password);
	
	Boolean storeUserSignupInfo(UserSignupInfoDTO user);
	
	String getGoogleUserName();
    
	void removeUserInfoSignup();
}
