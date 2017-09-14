<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sectraining.vulnserver.db.UserProfile" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<h2>Here are your sandwiches for today:</h2>
<%
   Date date = new Date();
   out.print( "<h2 align=\"left\">" +date.toString()+"</h2>");
%>

<p>beef on weck</p>
<p>po boy</p>
<p>reuben</p>
<p>shawarma</p>
<p>de miga</p>
<p>blt</p>
<p>sloppie joe</p>
<p>roast beef</p>
<p>chicken salad</p>
