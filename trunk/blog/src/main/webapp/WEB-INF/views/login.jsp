<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<form action="${pageContext.request.contextPath }/loginAction" method="POST">
	<label>ID </label><input type="text" name="loginId" /> <br />
	<label>Password </label><input type="password" name="password" /> <br />
	<input type="submit" value="로그인" />
</form>


</body>
</html>