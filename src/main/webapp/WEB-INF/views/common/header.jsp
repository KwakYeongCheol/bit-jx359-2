<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${htmlTitle }</title>
	<link href="${pageContext.request.contextPath }/resources/css/common.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="${pageContext.request.contextPath }/resources/css/home.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<body>
<div id="wrap">
	<div id="header">
		<ul>
			<c:if test="${loginUser != null }">
			<li>${loginUser.loginId } 님</li>
			<li><a href="${pageContext.request.contextPath }/logout">로그아웃</a></li>
			</c:if>
			<li><a href="${pageContext.request.contextPath }">홈</a></li>
			<c:if test="${loginUser == null }">
			<li><a href="${pageContext.request.contextPath }/login">로그인</a></li>
			</c:if>
			<li><a href="${pageContext.request.contextPath }/user/register/step01">회원가입</a></li>
		</ul>
	</div>
	
	<div id="contents">
