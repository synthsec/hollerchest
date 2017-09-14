<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sectraining.vulnserver.sqli.UserProfile" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<h2>Here are your dogs for today:</h2>
<%
   Date date = new Date();
   out.print( "<h2 align=\"left\">" +date.toString()+"</h2>");
%>

<p>american water spaniel</p>
<p>border terrier</p>
<p>pekingese</p>
<p>sloughi</p>
<p>german shorthaired pointer</p>
<p>pug</p>
<p>jack russell terrier</p>
<p>leonberger</p>
<p>golden retriever</p>
