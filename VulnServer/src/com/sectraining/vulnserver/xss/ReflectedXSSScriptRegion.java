package com.sectraining.vulnserver.xss;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReflectedXSSScriptRegion
 */
@WebServlet("/01_XSS/ReflectedXSSScriptRegion")
public class ReflectedXSSScriptRegion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReflectedXSSScriptRegion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardToJSP(request, response);
	}

	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		getServletConfig().getServletContext().getRequestDispatcher("/01_XSS/ReflectedXSSScriptRegion.jsp").forward(request, response);
	}
}
