<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sectraining.vulnserver.xss.Holler" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HollerChest</title>
</head>
<body>
<h3>Welcome to the holler chest! Put your name in here to give me a holler!</h3>

<form method="POST">
<H2>Name: <input type="text" name="user"></input></H2>
<H2>Message: <textarea name="message" cols="128" rows="2"></textarea></H2>
<input type="submit"></input>
</form>

<H2>Latest Hollers:</H2>

<c:forEach items="${requestScope.hollers}" var="holler">
	<div>
		<b>User: ${holler.getUser()}</b>
		<b>Message:${holler.getMessage()}</b>
		<b>Time: ${holler.getFormattedCreateTime()}</b>
	</div>
</c:forEach>


</body>
</html>