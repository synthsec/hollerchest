package com.sectraining.vulnserver.sqli;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sectraining.vulnserver.CourseContentLink;

/**
 * Servlet implementation class PersistentXSS
 */
@WebServlet("/02_SQLI/Sqli_Login")
@CourseContentLink(courseId=6, title = "SQL Injection", section = "3.1")
public class Sqli_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserProfileDao userDao;
    
    /**hollerDao
     * @see HttpServlet#HttpServlet()
     */
    public Sqli_Login() {
        super();
        userDao = new UserProfileDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("uid") != null)
		{
			response.sendRedirect(request.getContextPath() + "/02_SQLI/Sqli_Authed");
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
		UserProfile authMeUser = new UserProfile(request.getParameter("user"), request.getParameter("password"));
		HttpSession session = request.getSession();
		UserProfile user = userDao.attemptLogin(authMeUser);
		if(user.getUid() != 0) {
			session.setAttribute("uid", user.getUid());
			response.sendRedirect(request.getContextPath() + "/02_SQLI/Sqli_Authed");
		}
		else
		{
			forwardToJSP(request, response);
		}
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		getServletConfig().getServletContext().getRequestDispatcher("/02_SQLI/Sqli_Login.jsp").forward(request, response);
	}

}
