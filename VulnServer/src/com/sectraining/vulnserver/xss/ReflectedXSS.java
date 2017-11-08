package com.sectraining.vulnserver.xss;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sectraining.vulnserver.CourseContentLink;

/**
 * Servlet implementation class ReflectedXSS
 */
@WebServlet("/01_XSS/ReflectedXSS")
@CourseContentLink(courseId=1, title = "Reflected XSS", section = "1.1")
public class ReflectedXSS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReflectedXSS() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardToJSP(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		getServletConfig().getServletContext().getRequestDispatcher("/01_XSS/ReflectedXSS.jsp").forward(request, response);
	}

}
