<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${blog.title } </title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/jinbo_userblog.css">
	<script src="${pageContext.request.contextPath }/resources/js/jquery-1.8.2.js"></script>
	
	<!--  user blog theme start -->
	<style>
		.jinbo .buttonBox{			background-color: ${blog.blogTheme.backgroundColor};		}
		.jinbo .nav-title{			background-color: ${blog.blogTheme.backgroundColor};	border-radius: 4px;		}
		.jinbo .nav-title:hover{	background-color: #026987;		}
		.blogHeader{			background-color: ${blog.blogTheme.backgroundColor};		}
		footer{			background-color: ${blog.blogTheme.backgroundColor};		}
	</style>
	<!--  user blog theme end -->
</head>

<body>
<div id="wrap">
	<header>
		<!-- jinbo header start -->
		<div class="jinboHeader">
			<div class="jinbo">
				<div class="title">
					<a href="${pageContext.request.contextPath }/">JINBO</a>
				</div>
				
				<div class="searchBox">
					<form action="${pageContext.request.contextPath }/search" method="get">
						<input type="text" name="query" placeholder="JINBO 전체 검색" class="search" />
						<input type="submit" value="검색" class="searchBtn"/>
					</form>
				</div>
				
				<c:if test="${!loginUserProvider.loggedIn }">
				<div class="buttonBox registerBox">
					<a href="${pageContext.request.contextPath }/user/register/step01">회원가입</a>
				</div>
				
				<div class="buttonBox loginBox">
					<a href="${pageContext.request.contextPath }/login">로그인</a>
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
						<li>
							<a href="${pageContext.request.contextPath }/${favorite.blog.id }">${favorite.blog.title }</a>
							| <a href="${pageContext.request.contextPath }/settings/favorite/delete?blogId=${favorite.blog.id}&redirectURI=${pageURI}">삭제</a>
						</li>
						</c:forEach>				
					</ul>
				</nav>
				</c:if>
			</div>
		</div>
		<!-- jinbo header end -->
		
		<!-- blog header start -->
		<div class="blogHeader">
			<div class="title">
				<a href="${pageContext.request.contextPath }/${blog.id}">${blog.title }</a>
				<c:if test="${loginUserProvider.loggedIn }">
					<c:if test="${!loginUserProvider.isMyBlog(blog.id) }">
						<c:if test="${!loginUserProvider.isExistFavorite(blog.id) }">
							<span class="addFavorite"><a href="${pageContext.request.contextPath }/favorite/addAction?blogId=${blog.id}">내 이웃으로 추가</a></span>
						</c:if>
					</c:if>
				</c:if>
			</div>
			<div class="searchBox">
				<form action="${pageContext.request.contextPath }/${blog.id}/search" method="get">
					<input type="text" class="input" name="query" placeholder="블로그 내 검색"/>
					<input type="submit" class="submit" value="검색" />
				</form>
			</div>
		</div>
		<!-- blog header end -->
		
		<!-- blog menu start -->
		<div class="blogMenu">
			<ul>
				<li><a href="${pageContext.request.contextPath }/${blog.id}">홈</a></li>
				<li><a href="${pageContext.request.contextPath }/${blog.id}/guestbook">방명록</a></li>
				
				<c:if test="${loginUserProvider.loggedIn }">
					<c:if test="${loginUserProvider.loginUser.loginId == blog.owner }">
						<li><a id="btnEditor" href="openEditor();">글쓰기</a></li>
						<li><a href="${pageContext.request.contextPath }/${blog.id}/admin">관리자</a></li>
					</c:if>
				</c:if>
			</ul>
			<nav class="notification">
				<div class="notification-nav-title">최근 알림 #</div>
				<ul>
					<c:if test="${notificationList.isEmpty() }">
					<li>최근 알림이 없습니다.</li>
					</c:if>
					<c:forEach items="${notificationList }" var="notification">
					<li>
						${notification.contents } | <span style="color:#CCC;">${notification.dateCreated }</span>
					</li>
					</c:forEach>
				</ul>
			</nav>
		</div>
		<!-- blog menu end -->
	</header>
	
	<section>
		<div id="background"></div>
		<div class="sidebar">
			<div class="blogInfo">
				<div class="today">
					<span class="label">Today</span> ${blog.todayCount } <br />
					<span class="label">Total</span> ${blog.totalCount }					
				</div>				
			</div>
			<hr />
			<div class="category">
				<span class="label">카테고리</span>
				<hr />
				<ul>
					<li><a href="${pageContext.request.contextPath }/${blog.id}">${allCategory.title } <span>(${allCategory.totalPostCount })</span></a></li>
					<c:forEach items="${categoryList }" var="category">
					<li <c:if test="${category.displayId == pageCategory.displayId }">class="selected"</c:if>>
						<a href="${pageContext.request.contextPath }/${blog.id }/category/${category.displayId}">${category.title } <span>(${category.totalPostCount })</span></a>
					</c:forEach>
				</ul>
			</div>
		</div>
		
		<!--contentsWrap start -->
		<div class="contentsWrap">
