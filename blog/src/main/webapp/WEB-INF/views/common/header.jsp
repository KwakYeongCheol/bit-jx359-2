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
	<header>
		<div class="title">
			<a href="${pageContext.request.contextPath }/">JINBO</a>
		</div>
		
		<div class="searchBox">
			<form action="${pageContext.request.contextPath }/search" method="get">
				<input type="text" name="query" placeholder="search" class="search" />
				<input type="submit" value="Search" class="searchBtn"/>
			</form>
		</div>
		
		<c:if test="${!loginUserProvider.loggedIn }">
		<div class="loginBox">
			<form action="${pageContext.request.contextPath }/loginAction" method="post">
				<input type="hidden" name="redirectURI" value="${pageURI }" />
				<input type="text" name="loginId" placeholder="sample@gmail.com" class="loginId"/> 
				<input type="password" name="password" placeholder="password" class="password"/>
				<input type="submit" value="Log In" class="submitBtn"/>
			</form>
		</div>
		<div class="registerBox">
			<a href="${pageContext.request.contextPath }/user/register/step01"><input type="submit" value="회원가입" class="submitBtn"/></a>
		</div>
		</c:if>
		
		<c:if test="${loginUserProvider.loggedIn }">
		<nav class="nav">
			<div class="nav-title">${loginUserProvider.loginUser.name }님</div>
			<ul class="nav-menu">
				<li><a href="${pageContext.request.contextPath }/settings/user">계정설정</a></li>
				<hr />
				<li><a href="${pageContext.request.contextPath }/logout?redirectURI=${pageURI}">로그아웃</a></li>
			</ul>
		</nav>
		
		<nav class="nav">
			<div class="nav-title">바로가기</div>
			<ul class="nav-menu">
				<c:forEach items="${loginUserProvider.blogList }" var="blog">
				<li><a href="${pageContext.request.contextPath }/${blog.id }">${blog.title }</a></li>
				</c:forEach>
				<hr />
				<li><a href="${pageContext.request.contextPath }/settings/blog">내 블로그 관리</a></li>
				<hr />
				<c:forEach items="${loginUserProvider.loginUser.favoriteList }" var="favorite">
				<li><a href="${pageContext.request.contextPath }/${favorite.blog.id }">${favorite.blog.title }</a></li>
				</c:forEach>
				<hr />
				<li><a href="${pageContext.request.contextPath }/settings/favorite">이웃 블로그 설정</a></li>
			</ul>
		</nav>
		</c:if>
	</header>
	
	<section>
