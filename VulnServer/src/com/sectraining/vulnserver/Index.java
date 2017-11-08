package com.sectraining.vulnserver;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index.html")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private TreeMap<Integer, CourseContentMeta> servletInfo = null;
    public Index() {
        super();
        // TODO Auto-generated constructor stub
        
		List<String> courseContentServlets = new FastClasspathScanner(this.getClass().getPackage().getName()).verbose().scan().getNamesOfClassesWithAnnotation(CourseContentLink.class);
		servletInfo = new TreeMap<Integer, CourseContentMeta>();
		
		for(String courseContentServlet : courseContentServlets) {
			Class servlet;
			try {
				servlet = Class.forName(courseContentServlet);
				CourseContentLink courseContentLink = (CourseContentLink) servlet.getAnnotation(CourseContentLink.class);
				WebServlet webServlet = (WebServlet) servlet.getAnnotation(WebServlet.class);
				
				CourseContentMeta meta = new CourseContentMeta();
				meta.setCourseOrderId(courseContentLink.courseId());
				meta.setTitle(courseContentLink.title());
				meta.setUri(webServlet.value()[0]);
				meta.setSection(courseContentLink.section());
				servletInfo.put(courseContentLink.courseId(), meta);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		request.setAttribute("courseMetadata", servletInfo);
		request.setAttribute("contextPath", getServletContext().getContextPath());
		request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
