package test.client;

import java.util.ArrayList;

import test.client.shared.dto.UserSignupInfoDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;


public class UServiceGet {
	UServiceAsync uservice;
	private String googleName;
	private String LoginForPasswordUrl;
	private String googleLoginURL;
	private Button b;
	private String googleLogoutURL;
	private String googleUserId;
	private String googleNickName;
	private ArrayList<String> ValidAccounts;
	private Boolean storeUserSignupInfo;
	private Boolean userExists;
	private Boolean userLoggedin;
	
	public UServiceGet(){
		uservice= GWT.create(UService.class);
	}

	public String getGoogleName() {
		GWT.runAsync(new RunAsyncCallback(){
			public void onFailure(Throwable reason) {
			}
			public void onSuccess() {
				uservice.getGoogleUserName(new AsyncCallback<String>(){
					public void onFailure(Throwable caught) {
					}
					public void onSuccess(String result) {
						googleName=result;
					}});
				
			}});
		return googleName;
	}

	public String getLoginForPasswordUrl() {
		uservice.getUserLoginForPasswordURL(new AsyncCallback<String>(){
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(String result) {
				LoginForPasswordUrl=result;
			}});
		return LoginForPasswordUrl;
	}

	public String getGoogleLoginURL() {
		uservice.getGoogleUserLoginURL(new AsyncCallback<String>(){
		public void onFailure(Throwable caught) {
		}
		public void onSuccess(String result) {
//		GWT.runAsync(new RunAsyncCallback(){
//							public void onFailure(Throwable reason) {
//							}
//							public void onSuccess() {
			setGoogleLoginUrl(result);
			
					}});
//			}});
		Window.alert(googleLoginURL);
		return googleLoginURL;
	}

	public void setGoogleLoginUrl(String s){
		this.googleLoginURL = s;
	}
	public String getGoogleLogoutUrl() {
		uservice.getGoogleUserLogoutURL(new AsyncCallback<String>(){
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(String result) {
				googleLogoutURL=result;
			}});
		return googleLogoutURL;
	}

	public String getGoogleUserId() {
		uservice.getGoogleUserId(new AsyncCallback<String>(){
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(String result) {
				googleUserId=result;
			}});
		return googleUserId;
	}

	public String getNickName() {
		uservice.getGoogleUserNickName(new AsyncCallback<String>(){
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(String result) {
				googleNickName=result;
			}});
		return googleNickName;
	}

	public ArrayList<String> getValidAccounts() {
		uservice.getValidAccounts(new AsyncCallback<ArrayList<String>>(){
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(ArrayList<String> result) {
				ValidAccounts=result;
			}});
		return ValidAccounts;
	}

	public Boolean getStoreUserSignupInfo(UserSignupInfoDTO user) {
		uservice.storeUserSignupInfo(user,new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Boolean result) {
				storeUserSignupInfo=result;
			}});
		return storeUserSignupInfo;
	}

	public Boolean getUserExists(UserSignupInfoDTO user) {
		uservice.storeUserSignupInfo(user,new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Boolean result) {
				userExists=result;
			}});
		return userExists;
	}

	public Boolean getUserLogin(String userName,String password) {
		uservice.userLoggedin(userName, password,new AsyncCallback<Boolean>(){
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Boolean result) {
				userExists=result;
			}});
		return userLoggedin;
	}
	
}
