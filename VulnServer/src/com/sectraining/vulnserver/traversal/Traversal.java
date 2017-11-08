package com.sectraining.vulnserver.traversal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.sectraining.vulnserver.CourseContentLink;

/**
 * Servlet implementation class PersistentXSS
 */
@WebServlet("/04_TRAVERSAL/Traversal")
@CourseContentLink(courseId=8, title = "File Traversal", section = "4.0")

public class Traversal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String workingDir; 
    private String filename;
    /**hollerDao
     * @see HttpServlet#HttpServlet()
     */
    public Traversal() {
        super();
        // TODO: this is dumb, make it something else.
        workingDir = new String("/home/dev/hollerchest/VulnServer/WebContent/04_TRAVERSAL/TraversalFiles/");
        filename = new String("/home/dev/hollerchest/VulnServer/WebContent/04_TRAVERSAL/TraversalFiles/english.txt");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filename = "/home/dev/hollerchest/VulnServer/WebContent/04_TRAVERSAL/TraversalFiles/english.txt";
		forwardToJSP(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//user.setUsername(request.getParameter("user"));
		//user.setPassword(request.getParameter("password"));
		//user = sqliDao.attemptLogin(user);
		String lang = request.getParameter("language");
		filename = lang.concat(".txt");
		filename = workingDir.concat(filename);
		
		// Filename can be a maximum for 123 characters long!
		filename = filename.substring(0, Math.min(123, filename.length()));
		forwardToJSP(request, response);
	}
		
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		//request.setAttribute("user", user);

		String fileContents = new String(Files.readAllBytes(Paths.get(filename)));
		
		request.setAttribute("blah", fileContents);
		getServletConfig().getServletContext().getRequestDispatcher("/04_TRAVERSAL/Traversal.jsp").forward(request, response);
	}

}
