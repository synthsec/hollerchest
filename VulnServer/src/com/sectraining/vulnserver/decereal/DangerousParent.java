package com.sectraining.vulnserver.decereal;

public class DangerousParent {
	protected String str = "Uh oh, a bad thing happened";
	
	
	public DangerousParent() {
		System.out.println(str);
	}
}
