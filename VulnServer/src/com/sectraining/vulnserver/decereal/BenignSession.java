package com.sectraining.vulnserver.decereal;

import java.io.Serializable;

public class BenignSession implements Serializable{
	String name = "Joe";
	int dollars = 0;
	Object placeholder;
	
	public String toString() {
		return name + " has " + dollars + " dollars";
	}
}
