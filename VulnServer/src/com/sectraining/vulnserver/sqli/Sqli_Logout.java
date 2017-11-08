package com.sectraining.vulnserver.sqli;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PersistentXSS
 */
@WebServlet("/02_SQLI/Sqli_Logout")
public class Sqli_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**hollerDao
     * @see HttpServlet#HttpServlet()
     */
    public Sqli_Logout() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		forwardToJSP(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardToJSP(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		HttpSession session = request.getSession();
		session.invalidate();  
		response.sendRedirect(request.getContextPath() + "/02_SQLI/Sqli_Login");
	}

}
