<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sectraining.vulnserver.db.UserProfile" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Traversal</title>
</head>
<body>
<h1>Select a language:</h1>

<form method="post">
<select name="language">
        <option value="english">English</option>
        <option value="spanish">Spanish</option>
        <option value="esparanto">Esparanto</option>
        <option value="nyanja">Nyanja</option>
     </select>
 <input type="submit"/> </form>

<!-- add a second menu w client side checks and a value that gets appended to the filename -->

<%
	String foo = (String)request.getAttribute("blah");
%>

<p><%=foo%></p>

</body>
</html>