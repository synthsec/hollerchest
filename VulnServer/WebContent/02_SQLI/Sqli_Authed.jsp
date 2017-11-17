<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.sectraining.vulnserver.sqli.UserProfile" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sqli_Authed</title>
</head>
<body>

<h1>User Details for user ${requestScope.user.getUsername()}:</h1>

<form method="POST">
<H3>Username: <input type="text" name="user" value="${requestScope.user.getUsername()}"></input></H3>
<H3>Password: <input type="text" name="password" value="${requestScope.user.getPassword()}" ></input></H3>
<H3>E-mail: <input type="text" name="email" value="${requestScope.user.getEmail()}" ></input></H3>
<H3>SSN: <input type="text" name="ssn" value="${requestScope.user.getSsn()}" ></input></H3>
<H3>CCN: <input type="text" name="ccn" value="${requestScope.user.getCcn()}" ></input></H3>
<input type="submit"></input>
</form>

<a href="Sqli_Logout">Logout</a>

</body>
</html>