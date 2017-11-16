package com.sectraining.vulnserver.commandi;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sectraining.vulnserver.CourseContentLink;

/**
 * Servlet implementation class CommandInjection
 */
@WebServlet("/CommandInjection")
@CourseContentLink(courseId=9, title = "Command Injection", section = "6.1")
public class CommandInjection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandInjection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.getRequestDispatcher("/05_COMMANDI/CommandInjection.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchOutput = "";
		String cmd = request.getParameter("cmd").toString();
		
		try { searchOutput = searchFile(cmd); }
		catch (Exception e ) {
			e.printStackTrace();
		}		
				
        request.setAttribute("results", searchOutput);
        request.getRequestDispatcher("/05_COMMANDI/CommandInjection.jsp").forward(request, response);
        
	}
	
	  public String searchFile(String input) throws Exception {		    
		    String output = "";
		    String wordFile = getServletContext().getRealPath("/WEB-INF/words.txt");
		    
		  	Runtime rt = Runtime.getRuntime();
		    Process proc = rt.exec(new String[] {"sh", "-c",  "grep -i " + input + " " + wordFile});
		    int result = proc.waitFor();
		    if (result != 0) {
		      System.out.println("process error: " + result);
		    }
		    InputStream in = (result == 0) ? proc.getInputStream() :
		                                     proc.getErrorStream();
		    int c;
		    while ((c = in.read()) != -1) {
		      System.out.print((char) c);
		      output +=(char) c;
		    }
		    return output;
		  }


}