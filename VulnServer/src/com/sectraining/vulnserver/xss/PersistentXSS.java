package com.sectraining.vulnserver.xss;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PersistentXSS
 */
@WebServlet("/01_XSS/PersistentXSS")
public class PersistentXSS extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HollerDao hollerDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersistentXSS() {
        super();
        hollerDao = new HollerDao();
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
		Holler h = new Holler();
		h.setUser(request.getParameter("user"));
		h.setMessage(request.getParameter("message"));
		hollerDao.insertHoller(h);
		doGet(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		List<Holler> hollers = hollerDao.getLatestHollers();
		request.setAttribute("hollers", hollers);
		getServletConfig().getServletContext().getRequestDispatcher("/01_XSS/PersistentXSS.jsp").forward(request, response);
	}

}
