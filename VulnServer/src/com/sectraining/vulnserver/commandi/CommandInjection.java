package com.sectraining.vulnserver.commandi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;

/**
 * Servlet implementation class CommandInjection
 */
@WebServlet("/05_COMMANDI/CommandInjection")
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
		    String wordFile = new String("/home/dev/hollerchest/VulnServer/WebContent/WEB-INF/words.txt");
		    
		    // A Byte Array to capture the output stream.
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		    
		    // Not strictly nessesary, but hey
		    Map<String, String> map = new HashMap<String, String>();
		    map.put("file", wordFile);
		    map.put("userInput", input);
		    
		    // Build the command, piecemeal
			CommandLine cl = new CommandLine("grep");
			cl.addArgument("-i");
			cl.addArgument("${userInput}");
			cl.addArgument("${file}");
			cl.setSubstitutionMap(map);
			
			// Instantiate and set up out executor
			DefaultExecutor executor = new DefaultExecutor();
			executor.setStreamHandler(streamHandler);
			
			// If the executor throws an exception, just print a
			// generic error and move on...
			try {
				executor.execute(cl);
			} catch (ExecuteException e) {
				// Abort!
				return new String("Oh no something went wrong.");
			}
			
		    return outputStream.toString();		    
		  }


}