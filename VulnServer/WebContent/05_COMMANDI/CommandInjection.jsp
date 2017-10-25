<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Word Finder</title>
</head>
<body>
    <h2>Find words containing:</h2>
    <form action="/VulnServer/CommandInjection" method="post">
        <input type="text" name="cmd" value="${cmd}"> </input>
        <input type="submit" value="Search"></input>
        <br /><br /><br />
        <textarea cols="auto" rows="10" name="results" >${results} </textarea>
    </form>

</body>
</html>