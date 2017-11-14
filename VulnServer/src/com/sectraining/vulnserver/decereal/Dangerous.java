package com.sectraining.vulnserver.decereal;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dangerous implements Serializable{

	private List<String> stuff;
	
	private void badthing() {
		if(stuff != null && !stuff.isEmpty()) {
			Runtime rt = Runtime.getRuntime();
		    Process proc;
		    int result = 0;
			try {
				String cmds[] = new String[stuff.size()];
				stuff.toArray(cmds);
				proc = rt.exec(cmds);
				result = proc.waitFor();
				
			    InputStream in = (result == 0) ? proc.getInputStream() : proc.getErrorStream();
			    String output = "";
				int c;
				while ((c = in.read()) != -1) {
					System.out.print((char) c);
					output +=(char) c;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	 private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
		 in.defaultReadObject();
		 badthing();
	 }
}
