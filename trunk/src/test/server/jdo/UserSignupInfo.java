package test.server.jdo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserSignupInfo implements Serializable
{

//	@PrimaryKey
//    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
//    private Key key;
    @Persistent
   private String fullName = "";
	@PrimaryKey
    @Persistent
   private String emailAddress = "";
    @Persistent
   private String phoneNum = "";
    @Persistent
   private String password = "";
    @Persistent
   private String address = "";
    @Persistent
    private String latlng = "";

//   @Persistent(serialized = "true", defaultFetchGroup = "true")
//   private HashMap<String,String> testMap=new HashMap<String, String>();
//   @Persistent(defaultFetchGroup = "true")
//   ContactInfo contactInfo=null;
   public UserSignupInfo()
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
             + ", FullName: " + fullName
             + ", Address: " + emailAddress
             + ", City: " + phoneNum
             + ", State: " + password
             + ", Zip: " + address
//             +", testMap: " + testMap.toString()
//             +", contactInfo: " + contactInfo.getAddress()
             +" }";
   }

public String getFullName() {
	return fullName;
}

public String getEmailAddress() {
	return emailAddress;
}

public String getPhoneNum() {
	return phoneNum;
}

public String getPassword() {
	return password;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}

public void setPhoneNum(String phoneNum) {
	this.phoneNum = phoneNum;
}

public void setPassword(String password) {
	this.password = password;
}

public String getAddress() {
	return address;
}

public String getLatlng() {
	return latlng;
}

public void setAddress(String address) {
	this.address = address;
}

public void setLatlng(String latlng) {
	this.latlng = latlng;
}

}

