<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sectraining.vulnserver.db.UserProfile" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Remote File Inclusion</title>
</head>
<body>
<h1>Select a view:</h1>

<form method="post">
<select name="view">
        <option value="colors.jsp">colors</option>
        <option value="dogs.jsp">dogs</option>
        <option value="cats.jsp">cats</option>
        <option value="sandwiches.jsp">sandwiches</option>
     </select>
     <input type="hidden" name="host" value="<%=request.getAttribute("host")%>">
 <input type="submit"/> </form>

<%
	String url = (String)request.getAttribute("incl");
%>

<div><c:import url="<%=url%>"/></div>

</body>
</html>