<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Generic Search Page</title>
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function encodeData(){
	$('#searchBox').val($('<div/>').text(document.getElementById("searchBox").value).html());
	return true;
}


</script>
</head>
<body>
<H2>Search</H2>
<form name="searchForm" method="POST" onsubmit="encodeData()">
	<input type="text" id="searchBox" name="postQuery" value=""></input>
	<input type="submit" value="Submit" ></input>
</form>
<%
	String query = request.getParameter("postQuery");
	if(query != null &&  !query.trim().equals("") && request.getMethod().equals("POST")){
%> 
<H2>Your search for: <%=query %> returned no results</H2>
<%
	}
%>
</body>
</html>