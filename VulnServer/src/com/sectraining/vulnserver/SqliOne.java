package com.sectraining.vulnserver;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sectraining.vulnserver.db.UserProfile;
import com.sectraining.vulnserver.db.SqliDao;

/**
 * Servlet implementation class PersistentXSS
 */
@WebServlet("/SqliOne")
public class SqliOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SqliDao sqliDao;
    private UserProfile user;
    
    /**hollerDao
     * @see HttpServlet#HttpServlet()
     */
    public SqliOne() {
        super();
        user = new UserProfile();
        sqliDao = new SqliDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		user.setPassword(null);
		user.setUsername(null);
		forwardToJSP(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		user.setUsername(request.getParameter("user"));
		user.setPassword(request.getParameter("password"));
		user = sqliDao.attemptLogin(user);
		forwardToJSP(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		request.setAttribute("user", user);
		getServletConfig().getServletContext().getRequestDispatcher("/SqliOne.jsp").forward(request, response);
	}

}
