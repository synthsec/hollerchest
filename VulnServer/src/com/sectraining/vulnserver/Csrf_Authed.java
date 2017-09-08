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
@WebServlet("/Csrf_Authed")
public class Csrf_Authed extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CsrfDao csrfDao;
    private UserProfile user;
    
    /**hollerDao
     * @see HttpServlet#HttpServlet()
     */
    public Csrf_Authed() {
        super();
        csrfDao = new CsrfDao();
        user = new UserProfile();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("uid") == null)
		{
			response.sendRedirect(request.getContextPath() + "/Csrf_Login");
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

		user.setUsername(request.getParameter("user"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setSsn(request.getParameter("ssn"));
		user.setCcn(request.getParameter("ccn"));
		
		HttpSession session = request.getSession();
		int uid = (int)session.getAttribute("uid");
		user.setUid(uid);
		csrfDao.updateUser(user);
		
		forwardToJSP(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		HttpSession session = request.getSession();
		
		int uid = (int)session.getAttribute("uid");
		user = csrfDao.getUser(uid);
		request.setAttribute("user", user);
		
		getServletConfig().getServletContext().getRequestDispatcher("/Csrf_Authed.jsp").forward(request, response);
	}

}
