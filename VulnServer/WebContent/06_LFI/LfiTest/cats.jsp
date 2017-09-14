<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sectraining.vulnserver.sqli.UserProfile" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<h2>This is a PoC for LFI, looks like it works.</h2>
<%
   Date date = new Date();
   out.print( "<h2 align=\"left\">" +date.toString()+"</h2>");
%>