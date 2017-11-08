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
@WebServlet("/03_CSRF/Csrf_Authed")
public class Csrf_Authed extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserProfileDao userDao;
    
    /**hollerDao
     * @see HttpServlet#HttpServlet()
     */
    public Csrf_Authed() {
        super();
        userDao = new UserProfileDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("uid") == null)
		{
			response.sendRedirect(request.getContextPath() + "/03_CSRF/Csrf_Login");
		}
		else
		{
			forwardToJSP(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserProfile user = new UserProfile();
		user.setUsername(request.getParameter("user"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setSsn(request.getParameter("ssn"));
		user.setCcn(request.getParameter("ccn"));
		
		HttpSession session = request.getSession();
		int uid = (int)session.getAttribute("uid");
		user.setUid(uid);
		userDao.updateUser(user);
		
		forwardToJSP(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		HttpSession session = request.getSession();
		
		int uid = (int)session.getAttribute("uid");
		UserProfile user = userDao.getUser(uid);
		request.setAttribute("user", user);
		
		getServletConfig().getServletContext().getRequestDispatcher("/03_CSRF/Csrf_Authed.jsp").forward(request, response);
	}

}
