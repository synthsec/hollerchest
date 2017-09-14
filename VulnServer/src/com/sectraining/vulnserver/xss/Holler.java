package com.sectraining.vulnserver.xss;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Holler {
	private Date createTime;
	private String user;
	private String message;
	
	public String getFormattedCreateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(createTime);
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
