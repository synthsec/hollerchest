package com.sectraining.vulnserver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReflectedXSSBlacklist
 */
@WebServlet("/ReflectedXSSBlacklist")
public class ReflectedXSSBlacklist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReflectedXSSBlacklist() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String[] entityBlacklist = new String[] {"a",
    		"abbr",
    		"acronym",
    		"address",
    		"applet",
    		"area",
    		"article",
    		"aside",
    		"audio",
    		"b",
    		"base",
    		"basefont",
    		"bdi",
    		"bdo",
    		"bgsound",
    		"big",
    		"blink",
    		"blockquote",
    		"body",
    		"br",
    		"button",
    		"canvas",
    		"caption",
    		"center",
    		"cite",
    		"code",
    		"col",
    		"colgroup",
    		"command",
    		"content",
    		"data",
    		"datalist",
    		"dd",
    		"del",
    		"details",
    		"dfn",
    		"dialog",
    		"dir",
    		"div",
    		"dl",
    		"dt",
    		"element",
    		"em",
    		"embed",
    		"fieldset",
    		"figcaption",
    		"figure",
    		"font",
    		"footer",
    		"form",
    		"frame",
    		"frameset",
    		"h1",
    		"h2",
    		"h3",
    		"h4",
    		"h5",
    		"h6",
    		"head",
    		"header",
    		"hgroup",
    		"hr",
    		"html",
    		"i",
    		"iframe",
    		"image",
    		"img",
    		"input",
    		"ins",
    		"isindex",
    		"kbd",
    		"keygen",
    		"label",
    		"legend",
    		"li",
    		"link",
    		"listing",
    		"main",
    		"map",
    		"mark",
    		"marquee",
    		"menu",
    		"menuitem",
    		"meta",
    		"meter",
    		"multicol",
    		"nav",
    		"nobr",
    		"noembed",
    		"noframes",
    		"noscript",
    		"object",
    		"ol",
    		"optgroup",
    		"option",
    		"output",
    		"param",
    		"picture",
    		"plaintext",
    		"pre",
    		"progress",
    		"rp",
    		"rt",
    		"rtc",
    		"ruby",
    		"samp",
    		"script",
    		"section",
    		"select",
    		"shadow",
    		"slot",
    		"small",
    		"source",
    		"spacer",
    		"span",
    		"strike",
    		"strong",
    		"style",
    		"sub",
    		"summary",
    		"sup",
    		"table",
    		"tbody",
    		"td",
    		"template",
    		"textarea",
    		"tfoot",
    		"th",
    		"thead",
    		"time",
    		"title",
    		"tr",
    		"track",
    		"tt",
    		"u",
    		"ul",
    		"var",
    		"video",
    		"wbr"
    		};
    
    private String[] blacklist = new String[] {"href", "onerror", "javascript:", "src"};
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardToJSP(request, response);
	}
	
	private void forwardToJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.addHeader("X-XSS-Protection", "1");
		String query = request.getParameter("query");
		if(query != null && !checkBlacklist(query)) {
			request.setAttribute("sanitizedQuery", "search contains xss");
		}else {
			request.setAttribute("sanitizedQuery", query);
		}
		getServletConfig().getServletContext().getRequestDispatcher("/ReflectedXSSBlacklist.jsp").forward(request, response);
	}
	
	private boolean checkBlacklist(String s) {
		for(String b : entityBlacklist) {
			if(s.toLowerCase().contains("<" + b))
				return false;
		}
		
		for(String b : blacklist) {
			if(s.toLowerCase().contains(b))
				return false;
		}
		return true;
	}
}
