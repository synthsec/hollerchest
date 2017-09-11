package com.sectraining.vulnserver;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Math;


import com.sectraining.vulnserver.db.UserProfile;
import com.sectraining.vulnserver.db.SqliDao;

/**
 * Servlet implementation class PersistentXSS
 */
@WebServlet("/Traversal")
public class Traversal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String workingDir; 
    private String filename;
    private String fileContents;
    /**hollerDao
     * @see HttpServlet#HttpServlet()
     */
    public Traversal() {
        super();
        // TODO: this is dumb, make it something else.
        workingDir = new String("/tmp/");
        filename = new String("/tmp/english.txt");
        fileContents = new String("");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filename = "/tmp/english.txt";
		forwardToJSP(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//user.setUsername(request.getParameter("user"));
		//user.setPassword(request.getParameter("password"));
		//user = sqliDao.attemptLogin(user);
		String lang = request.getParameter("language");
		filename = lang.concat(".txt");
		filename = workingDir.concat(filename);
		// Filename can be a maximum for 40 characters long!
		filename = filename.substring(0, Math.min(40, filename.length()));
		forwardToJSP(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		//request.setAttribute("user", user);
		
		String blah = new String(Files.readAllBytes(Paths.get(filename)));
		request.setAttribute("blah", blah);
		getServletConfig().getServletContext().getRequestDispatcher("/Traversal.jsp").forward(request, response);
	}

}
