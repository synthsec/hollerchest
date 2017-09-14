package com.sectraining.vulnserver.csrf;

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
@WebServlet("/03_CSRF/Csrf_Logout")
public class Csrf_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**hollerDao
     * @see HttpServlet#HttpServlet()
     */
    public Csrf_Logout() {
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
		response.sendRedirect(request.getContextPath() + "/03_CSRF/Csrf_Login");
		// getServletConfig().getServletContext().getRequestDispatcher("/Csrf_Logout.jsp").forward(request, response);
	}

}
