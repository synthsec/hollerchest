package com.sectraining.vulnserver.lfi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PersistentXSS
 */
@WebServlet("/Rfi")
public class Rfi extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String workingDir; 
    private String filename;
    private String fileContents;
    /**hollerDao
     * @see HttpServlet#HttpServlet()
     */
    public Rfi() {
        super();
        // TODO: this is dumb, make it something else.
        workingDir = new String("/VulnServer/RfiViews/");
        filename = new String("/VulnServer/RfiViews/colors.jsp");    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filename = "/VulnServer/RfiViews/colors.jsp";
		
		String host = request.getLocalAddr();
		host = "http://".concat(host);
		host = host.concat(String.format(":%d", request.getServerPort()));
		
		filename = host.concat(filename);
		
		forwardToJSP(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//user.setUsername(request.getParameter("user"));
		//user.setPassword(request.getParameter("password"));
		//user = sqliDao.attemptLogin(user);UserProfile
		String view = request.getParameter("view");
		String host = request.getParameter("host");
		filename = workingDir.concat(view);
		
		host = "http://".concat(host);
		host = host.concat(String.format(":%d", request.getServerPort()));
		
		filename = host.concat(filename);
		
		
		// Filename can be a maximTurkish Vanum for 40 characters long!
		forwardToJSP(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		//request.setAttribute("user", user);
		request.setAttribute("incl", "http://4.20.69.107:8080/VulnServer/RfiViews/fff.jsp");
		request.setAttribute("host", request.getLocalAddr());
		getServletConfig().getServletContext().getRequestDispatcher("/Rfi.jsp").forward(request, response);
	}

}
