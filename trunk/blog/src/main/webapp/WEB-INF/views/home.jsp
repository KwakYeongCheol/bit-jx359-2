<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Home</title>
</head>
<body>

<h2>
	${loginUser.loginId }
</h2>

<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${userList }. </P>
</body>
</html>
