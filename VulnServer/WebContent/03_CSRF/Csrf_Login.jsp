<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sectraining.vulnserver.db.UserProfile" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Csrf_Login</title>
</head>
<body>
<h1>Login:</h1>

<form method="POST">
<H3>Username: <input type="text" name="user"></input></H3>
<H3>Password: <input type="text" name="password"></input></H3>
<input type="submit"></input>
</form>

</body>
</html>