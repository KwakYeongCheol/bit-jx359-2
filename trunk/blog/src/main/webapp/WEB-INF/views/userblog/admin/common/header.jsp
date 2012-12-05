<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${blog.title} </title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/jinbo.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/admin.css">
	<script src="${pageContext.request.contextPath }/resources/js/jquery-1.8.2.js"></script>
</head>
<body>
<div id="wrap">
	<header>
		<div class="title">
			<a href="${pageContext.request.contextPath }/${blog.id}">${blog.title }</a> &gt; 
			<a href="${pageContext.request.contextPath }/${blog.id }/admin">관리자</a>
		</div>
		<div class="searchBox">
			<form action="${pageContext.request.contextPath }/search" method="get">
				<input type="text" name="query" placeholder="search" class="search" />
				<input type="submit" value="Search" class="searchBtn" />
			</form>
		</div>
		
		<c:if test="${!loginUserProvider.loggedIn }">
		<div class="registerBox">
			<a href="${pageContext.request.contextPath }/user/register/step01">회원가입</a>
		</div>
		
		<div class="loginBox">
			<form action="${pageContext.request.contextPath }/loginAction" method="post">
				<input type="text" name="loginId" placeholder="sample@gmail.com"/> 
				<input type="password" name="password" placeholder="password" />
				<input type="submit" value="Log In" />
			</form>
		</div>
		</c:if>
		
		<c:if test="${loginUserProvider.loggedIn }">
		<nav class="nav">
			<div class="nav-title">${loginUserProvider.loginUser.name } 님</div>
			<ul class="nav-menu">
				<li><a href="${pageContext.request.contextPath }/settings/user">계정설정</a></li>
				<hr />
				<li><a href="${pageContext.request.contextPath }/logout?redirectURI=${pageURI}">로그아웃</a></li>
			</ul>
		</nav>
		
		<nav class="nav">
			<div class="nav-title">바로가기</div>
			<ul class="nav-menu nav-menu-blog">
				<li class="nav-menu-title"><a href="${pageContext.request.contextPath }/settings/blog">내 블로그 관리</a></li>
				<hr />
				<c:forEach items="${loginUserProvider.blogList }" var="blog">
				<li>
					<span class="nav-menu-blog-title"><a href="${pageContext.request.contextPath }/${blog.id }">${blog.title }</a></span>	|
					<span class="nav-menu-blog-admin"><a href="${pageContext.request.contextPath }/${blog.id }/admin">관리자</a></span>					
				</li>
				</c:forEach>
				
				<li class="nav-menu-title" style="margin-top:20px;"><a href="${pageContext.request.contextPath }/settings/favorite">이웃 블로그 설정</a></li>
				<hr />
				<c:forEach items="${loginUserProvider.loginUser.favoriteList }" var="favorite">
				<li><a href="${pageContext.request.contextPath }/${favorite.blog.id }">${favorite.blog.title }</a></li>
				</c:forEach>
			</ul>
		</nav>
		</c:if>
	</header>
	
	<section>
<div class="menu">
	<div class="menu-title"><a href="${pageContext.request.contextPath }/${blog.id}/admin">관리자페이지</a></div>
	<hr />
	<div class="menu-group">
		<ul>
			<li><a href="${pageContext.request.contextPath }/${blog.id}">블로그 홈</a></li>
			<li><a href="${pageContext.request.contextPath }/${blog.id }/admin/post/editor">글쓰기</a></li>
		</ul>				
	</div>
	<hr />
	<div class="menu-group">
		<ul>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/category">카테고리 관리</a></li>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/post">게시물 관리</a></li>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/guestbook">방명록 관리</a></li>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/comment">댓글 관리</a></li>
		</ul>				
	</div>
	<hr />
	<div class="menu-group">
		<ul>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/external">외부 컨텐츠 관리</a>
		</ul>
	</div>	
	<hr />
	<div class="menu-group">
		<ul>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/notification">알림 관리</a>
		</ul>
	</div>
	<hr />
	<div class="menu-group">
		<ul>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/post/revision">게시글 변경 내역</a></li>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/visit_history">방문자 목록</a></li>
			<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/scrap">스크랩 통계</a></li>
		</ul>				
	</div>
</div>
		<article class="articleB">
<script>
function openEditor(){
	console.log("openEditor");
	var postBackground = $("<div>").addClass("postBackground").appendTo($("body"));
	var postEditor = $("<div>").addClass("postEditor").appendTo($("body"));
	var editorBox = $("<div>").addClass("editorBox").appendTo(postEditor);
	$.ajax({
		url : '${pageContext.request.contextPath }/${blog.id }/admin/post/write',
		type : 'GET',
		success : function(result){
			if(result != null){
				editorBox.append(result);
			}
		},
		error : function(result){
			console.log('error');
		}
	}, 'json');
	
	$(".postBackground").bind('click', function(){
		$(this).remove();
		$(".postEditor").remove();
	});
}

$(document).ready(function(){
	$(".openEditor").bind('click', function(){
		openEditor();
	});
});
</script>		