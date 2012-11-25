<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>JINBO </title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/jinbo.css">
	<script src="${pageContext.request.contextPath }/resources/js/jquery-1.8.2.js"></script>
</head>
<body>
<div id="wrap">
<section>
<div class="contentsLoginContainer">
	<div class="contentsLoginWrap">
		<div class="title">
			<a href="${pageContext.request.contextPath }/">JINBO</a>
		</div>
		<div class="contentsLoginBox">
			<form action="${pageContext.request.contextPath }/loginAction" method="POST">
				<c:if test="${redirectURI != null}">
				<input type="hidden" name="redirectURI" value="${redirectURI }" />
				</c:if>
				<div class="inputLoginInfo">
					<input type="text" name="loginId" placeholder="input email address" autofocus/><br />
					<input type="password" name="password" placeholder="input password" /> 
				</div>
				<input type="submit" class="btnContentsLogin" value="로그인" />
			</form>
		</div>
		<div>
			회원이 아니시라면 <a href="${pageContext.request.contextPath }/user/register/step01" class="register">회원가입</a>을 해주십시오.
		</div>
	</div>
</div>
</section>
</div>
</body>
</html>