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

/**
 * Servlet implementation class Decereal
 */
@WebServlet("/Decereal")
public class Decereal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Decereal() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        foo f = new foo();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(f);
        oos.close();


        byte[] bytes = bos.toByteArray();

        response.setContentType("text/html");
        request.setAttribute("message", "does this change");
        request.setAttribute("serializedobj", Base64.getEncoder().encodeToString(bytes));
        request.getRequestDispatcher("/XX_DECEREAL/Decereal.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String serializedobjinb64 = request.getParameter("serializedobj").toString();
        //serializedobjinb64 = java.net.URLDecoder.decode(serializedobjinb64, "UTF-8");
        
        //response.getWriter().append(serializedobjinb64);
        byte[] bytes = Base64.getMimeDecoder().decode(serializedobjinb64.trim());
        
        //response.getWriter().append(bytes.toString());
        //byte[] decompressedbytes = decompress(compressedBytes);

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
            request.getRequestDispatcher("/XX_DECEREAL/Decereal.jsp").forward(request, response);
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
