<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${htmlTitle }</title>
</head>

<body>
<div id="main">
	<div>
		<c:if test="${loginUser != null }">
		${loginUser.loginId } 님 | 
		<a href="${pageContext.request.contextPath }/logout">로그아웃</a>
		</c:if>
		
		<c:if test="${loginUser == null }">
		<a href="${pageContext.request.contextPath }/login">로그인</a>
		</c:if>
		
		<a href="${pageContext.request.contextPath }/user/register/step01">회원가입</a>
	</div>
