package test.client.shared.dto;

import java.io.Serializable;

public class AccountDTO  implements Serializable{
  public String id;
  public String website;
  public String loginName;
  public String loginPassword;
  public AccountDTO() {
		super();
	}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getWebsite() {
	return website;
}
public void setWebsite(String website) {
	this.website = website;
}
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public String getLoginPassword() {
	return loginPassword;
}
public void setLoginPassword(String loginPassword) {
	this.loginPassword = loginPassword;
}
  
}
