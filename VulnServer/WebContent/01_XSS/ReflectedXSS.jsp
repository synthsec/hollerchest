<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Generic Search Page</title>
</head>
<body>
<H2>Search</H2>
<form method="GET">
	<input type="text" name="query" value=""></input>
	<input type="submit" value="Submit"></input>
</form>
<c:if test="${not empty param.query }">
<H2>Your search for: ${param.query}  returned no results</H2>
</c:if>
</body>
</html>