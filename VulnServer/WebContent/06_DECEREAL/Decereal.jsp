<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Decereal</title>
</head>
<body>
    <h2>Serialized object looks like this: ${serializedobj}</h2>
    <form action="/VulnServer/06_Decereal/Decereal" method="post">
        <input type="text" name="serializedobj" value="${serializedobj}" /> </input>
        <input type="submit">go</input>
    </form>
</body>
</html>