package com.sectraining.vulnserver.db;

public class UserProfile {
	private String username;
	private String password;
	
	public UserProfile(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public UserProfile()
	{
		this.username = null;
		this.username = null;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
