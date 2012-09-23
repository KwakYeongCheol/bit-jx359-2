<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${htmlTitle }</title>
	<link href="${pageContext.request.contextPath }/resources/css/common.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${pageContext.request.contextPath }/resources/css/home.css" rel="stylesheet" type="text/css" media="screen" />
	<c:forEach items="${cssList}" var="css">
	<link href="${pageContext.request.contextPath }/resources/${css}" rel="stylesheet" type="text/css" media="screen" />
	</c:forEach>
</head>

<body>
<div id="wrap">
	<div id="header">
		<ul>
			<li><a href="${pageContext.request.contextPath }/user/register/step01">회원가입</a></li>
			<c:if test="${loginUserProvider.loggedIn }">
			<li><a href="${pageContext.request.contextPath }/logout">로그아웃</a></li>
			<li>${loginUserProvider.loginId } 님</li>
			</c:if>
			<c:if test="${!loginUserProvider.loggedIn }">
			<li><a href="${pageContext.request.contextPath }/login">로그인</a></li>
			</c:if>
			<li><a href="${pageContext.request.contextPath }">홈</a></li>
		</ul>
	</div>
	
	<div id="contents">
