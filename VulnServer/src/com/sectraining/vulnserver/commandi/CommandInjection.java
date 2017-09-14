package com.sectraining.vulnserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommandInjection
 */
@WebServlet("/CommandInjection")
public class CommandInjection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandInjection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String file = request.getParameter("fileName");
		//file = file == null ? "/etc/passwd" : file;
		File catalinaBase = new File( System.getProperty( "catalina.base" ) ).getAbsoluteFile();
		String workingDir = catalinaBase.getAbsolutePath();
		Runtime runtime = Runtime.getRuntime();		
		String[] cmd = new String[] {"/bin/bash", "-c", "/usr/bin/touch", "/tmp/fffff"};
		
		Process p = runtime.exec(cmd);
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String result = "";
		while((result = br.readLine()) != null) {
			
		}
		
		
		request.setAttribute("output", result);
		forwardToJSP(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/CommandInjection.jsp").forward(request, response);
	}

}
