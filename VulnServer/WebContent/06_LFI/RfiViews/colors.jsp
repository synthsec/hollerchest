<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sectraining.vulnserver.sqli.UserProfile" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<h2>Here are your colors for today:</h2>
<%
   Date date = new Date();
   out.print( "<h2 align=\"left\">" +date.toString()+"</h2>");
%>

<h1>Current Colors:</h1>

<p>pantone 231</p>
<p>pantone 200</p>
<p>pantone 204</p>
<p>pantone 122</p>
<p>pantone 112</p>
<p>pantone 202</p>
<p>pantone 217</p>
<p>pantone 235</p>
<p>pantone 130</p>
