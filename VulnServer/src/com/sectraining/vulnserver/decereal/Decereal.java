package com.sectraining.vulnserver.decereal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sectraining.vulnserver.CourseContentLink;

/**
 * Servlet implementation class Decereal
 */
@WebServlet("/Decereal")
@CourseContentLink(courseId=10, title = "Deserialization", section = "6.0")
public class Decereal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Decereal() {
        super();
    }

    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        foo f = new foo();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(f);
        oos.close();


        byte[] bytes = bos.toByteArray();

        response.setContentType("text/html");
        request.setAttribute("message", "does this change");
        request.setAttribute("serializedobj", Base64.getEncoder().encodeToString(bytes));
        request.getRequestDispatcher("/06_DECEREAL/Decereal.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String serializedobjinb64 = request.getParameter("serializedobj").toString();
        
        byte[] bytes = Base64.getMimeDecoder().decode(serializedobjinb64.trim());
        

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        bis.close();
        ObjectInputStream ois = new ObjectInputStream(bis);
        foo ownme = new foo();

        try {
            ownme = (foo) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(ownme);
            oos.close();
            response.setContentType("text/html");
            request.setAttribute("message", "does this change");
            request.setAttribute("serializedobj", Base64.getEncoder().encodeToString(bos.toByteArray()));
            request.getRequestDispatcher("/06_DECEREAL/Decereal.jsp").forward(request, response);
        }	    
	}
}

	

class foo implements Serializable {
    String state = "foo";
    Integer one = 1;
    double onepointtwo = 1.2;
    public void bar(){
        System.out.println("foobar");
    }
}
