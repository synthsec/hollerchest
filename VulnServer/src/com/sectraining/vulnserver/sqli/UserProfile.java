package com.sectraining.vulnserver.sqli;

public class UserProfile {
	private int uid;
	private String username;
	private String password;
	private String email;
	private String ssn;
	private String ccn;	
	
	public UserProfile(String username, String password)
	{
		this.username = username;
		this.password = password;
		this.email = null;
		this.ssn = null;
		this.ccn = null;
		this.uid = 0;
	}
	
	public UserProfile()
	{
		this.username = null;
		this.password = null;
		this.email = null;
		this.ssn = null;
		this.ccn = null;
		this.uid = 0;
	}
	
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public String getSsn() {return ssn;}
	public void setSsn(String ssn) {this.ssn = ssn;}
	
	public String getCcn() {return ccn;}
	public void setCcn(String ccn) {this.ccn = ccn;}
	
	public int getUid(){return this.uid;}
	public void setUid(int uid){this.uid = uid;}
	
}
