package com.sectraining.vulnserver.decereal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sectraining.vulnserver.CourseContentLink;

/**
 * Servlet implementation class Decereal
 */
@WebServlet("/06_Decereal/Decereal")
@CourseContentLink(courseId=10, title = "Deserialization", section = "7.1")
public class Decereal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Decereal() {
        super();
    }
   
    private String seshCookie = "customSession";
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customSession = getCookie(seshCookie, request);
		BenignSession session = null;
		if(customSession != null && !customSession.trim().equals("")) {
			session = deserialize(customSession);
		}else {
	        session = new BenignSession();
	        session.dollars = 20;
	        session.name = "Bob";
		}
		
        request.setAttribute("sessionObject", session);
        response.addCookie(new Cookie(seshCookie, serialize(session)));
        request.getRequestDispatcher("/06_DECEREAL/Decereal.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
	
	private String getCookie(String cookieName, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	private BenignSession deserialize(String serializedSession) throws IOException {
		 byte[] bytes = Base64.getMimeDecoder().decode(serializedSession.trim());
	        
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        bis.close();
        ObjectInputStream ois = new ObjectInputStream(bis);
        BenignSession session = null;

        try {
            session = (BenignSession) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(session);
            oos.close();
        }	
        
        return session;
	}
	
	private String serialize(BenignSession session) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(session);
        oos.close();


        byte[] bytes = bos.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);		
	}
}
