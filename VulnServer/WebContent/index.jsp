<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="java.util.TreeMap" %>
<%@ page import="com.sectraining.vulnserver.CourseContentMeta" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to training</title>
</head>
<body>
<%
TreeMap<Integer, CourseContentMeta> courseData = (TreeMap<Integer, CourseContentMeta>)request.getAttribute("courseMetadata");
String contextPath = (String)request.getAttribute("contextPath");
for(Integer courseId : courseData.keySet()){
	CourseContentMeta course = courseData.get(courseId);
%>
	<div><%=course.getSection()%> <a href="<%=contextPath%><%=course.getUri()%>"><%=course.getTitle()%></a></div>
<%
}
%>
</body>
</html>