package com.sectraining.vulnserver.xss;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sectraining.vulnserver.CourseContentLink;

/**
 * Servlet implementation class ReflectedXSSClientMitigation
 */
@WebServlet("/01_XSS/ReflectedXSSClientMitigation")
@CourseContentLink(courseId=2, title = "Reflected XSS Client Mitigation", section = "2.2")
public class ReflectedXSSClientMitigation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReflectedXSSClientMitigation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		forwardToJSP(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardToJSP(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		getServletConfig().getServletContext().getRequestDispatcher("/01_XSS/ReflectedXSSClientMitigation.jsp").forward(request, response);
	}

}
