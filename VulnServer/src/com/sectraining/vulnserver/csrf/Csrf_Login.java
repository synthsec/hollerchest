package com.sectraining.vulnserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sectraining.vulnserver.db.CsrfDao;
import com.sectraining.vulnserver.db.UserProfile;

/**
 * Servlet implementation class PersistentXSS
 */
@WebServlet("/Csrf_Login")
public class Csrf_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CsrfDao csrfDao;
    private UserProfile user;
    
    /**hollerDao
     * @see HttpServlet#HttpServlet()
     */
    public Csrf_Login() {
        super();
        user = new UserProfile();
        csrfDao = new CsrfDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("uid") != null)
		{
			response.sendRedirect(request.getContextPath() + "/Csrf_Authed");
		}
		else
		{
			user.setPassword(null);
			user.setUsername(null);
			forwardToJSP(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserProfile authMeUser = new UserProfile(request.getParameter("user"), request.getParameter("password"));
		HttpSession session = request.getSession();
		user = csrfDao.attemptLogin(authMeUser);
		if(user.getUid() != 0) {
			session.setAttribute("uid", user.getUid());
			response.sendRedirect(request.getContextPath() + "/Csrf_Authed");
		}
		else
		{
			forwardToJSP(request, response);
		}
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		request.setAttribute("user", user);
		getServletConfig().getServletContext().getRequestDispatcher("/Csrf_Login.jsp").forward(request, response);
	}

}
