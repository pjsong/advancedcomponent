package test.client.shared.dto;

import java.io.Serializable;

public class UserSignupInfoDTO extends AccountDTO
{
   private String emailAddress = "";
   private String phoneNum = "";
   private String repassword = "";
   private String address = "";
   private String errorMsg = "";
   private String latlng="";


//   @Persistent(serialized = "true", defaultFetchGroup = "true")
//   private HashMap<String,String> testMap=new HashMap<String, String>();
//   @Persistent(defaultFetchGroup = "true")
//   ContactInfo contactInfo=null;
   public UserSignupInfoDTO()
   {
   }

//   public ContactInfo getContactInfo() {
//		return contactInfo;
//	}
//
//	public void setContactInfo(ContactInfo contactInfo) {
//		this.contactInfo = contactInfo;
//	}
//   public HashMap<String, String> getTestMap() {
//		return testMap;
//	}
//
//	public void setTestMap(HashMap<String,String> pTestMap) {
//		this.testMap=pTestMap;
//	}



   public String toString()
   {
      return "UserInfo { "
             + ", loginName: " + loginName
             + ", Address: " + emailAddress
             + ", City: " + phoneNum
             + ", State: " + loginPassword
             + ", Zip: " + address
//             +", testMap: " + testMap.toString()
//             +", contactInfo: " + contactInfo.getAddress()
             +" }";
   }


public String getEmailAddress() {
	return emailAddress;
}

public String getPhoneNum() {
	return phoneNum;
}


public String getRePassword() {
	return repassword;
}
public String getAddress() {
	return address;
}
public String getErrorMsg() {
	return errorMsg;
}
public String reSetErrorMsg() {
	return "";
}

public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}

public void setPhoneNum(String phoneNum) {
	this.phoneNum = phoneNum;
}

public void setRePassword(String repassword) {
	this.repassword = repassword;
}
public void setAddress(String community) {
	this.address = community;
}
public String getLatlng() {
	return latlng;
}

public void setLatlng(String latlng) {
	this.latlng = latlng;
}
public void setErrorMsg() {
	if(checkingNull(loginName)) {errorMsg="null name";return;}
	if(checkingNull(emailAddress)||emailAddress.indexOf('@')==-1) {errorMsg="invalid email address" + emailAddress;return;}
	if(checkingNull(phoneNum)) {errorMsg="null phone number";return;}
	if(checkingNull(loginPassword)) {errorMsg="null password";return;}
	else if(!loginPassword.equals(repassword)){errorMsg="password not consistent";return;}
	if(checkingNull(address)) {errorMsg="null address";return;}
	if(checkingNull(latlng)) {errorMsg="null latlng";return;}
}
private boolean checkingNull(String s){
	if(s==null||"".equals(s))return true;
	else return false;
}
}

