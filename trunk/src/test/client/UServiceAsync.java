package test.client;

import java.util.ArrayList;

import test.client.shared.dto.UserSignupInfoDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface UServiceAsync {
//	void getUserName(AsyncCallback<String> callback) throws IllegalArgumentException;
	void getGoogleUserId(AsyncCallback<String> callback) throws IllegalArgumentException;
	void getGoogleUserNickName(AsyncCallback<String> callback) throws IllegalArgumentException;
	void getGoogleUserLoginURL(AsyncCallback<String> callback) throws IllegalArgumentException;
	void getGoogleUserLogoutURL(AsyncCallback<String> callback) throws IllegalArgumentException;
	void getValidAccounts(AsyncCallback<ArrayList<String>> callback);
	void userExists(String email, AsyncCallback<Boolean> callback);
	void getGoogleUserName(AsyncCallback<String> callback);
	void storeUserSignupInfo(UserSignupInfoDTO user,AsyncCallback<Boolean> callback);
	void userLoggedin(String userName,String password,AsyncCallback<Boolean> callback);
	void removeUserInfoSignup(AsyncCallback<Void> callback);
	void getUserLoginForPasswordURL(AsyncCallback<String> callback);
}
