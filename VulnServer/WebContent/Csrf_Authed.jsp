<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sectraining.vulnserver.db.UserProfile" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Csrf_Authed</title>
</head>
<body>

<%
	UserProfile ussrr = (UserProfile)request.getAttribute("user");
%>

<h1>User Details for user <%=ussrr.getUsername()%>:</h1>

<form method="POST">
<H3>Username: <input type="text" name="user" value="<%=ussrr.getUsername()%>"></input></H3>
<H3>Password: <input type="text" name="password" value="<%=ussrr.getPassword()%>" ></input></H3>
<H3>E-mail: <input type="text" name="email" value="<%=ussrr.getEmail()%>" ></input></H3>
<H3>SSN: <input type="text" name="ssn" value="<%=ussrr.getSsn()%>" ></input></H3>
<H3>CCN: <input type="text" name="ccn" value="<%=ussrr.getCcn()%>" ></input></H3>
<input type="submit"></input>
</form>

<a href="Csrf_Logout">Logout</a>

</body>
</html>