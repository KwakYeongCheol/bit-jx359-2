<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${htmlTitle }</title>
	<link href="${pageContext.request.contextPath }/resources/css/userblog/userblog.css" rel="stylesheet" type="text/css" media="screen" />
	<c:forEach items="${cssList}" var="css">
	<link href="${pageContext.request.contextPath }/resources/${css}" rel="stylesheet" type="text/css" media="screen" />
	</c:forEach>
	<script src="${pageContext.request.contextPath }/resources/js/jquery-1.8.2.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/userblog.js"></script>
</head>

<body>
<header>
			<div class="menu"> 
				<div class="title">
					<a href="${pageContext.request.contextPath }/${blog.id }">${blog.title }</a>
				</div>
	   			<div class="addFavorite">
					<c:if test="${loginUserProvider.loggedIn }">
						<c:if test="${!loginUserProvider.isMyBlog(blog.id) }">
							<c:if test="${!loginUserProvider.isExistFavorite(blog.id) }">
								<a href="${pageContext.request.contextPath }/favorite/addAction?blogId=${blog.id}"> [이웃 추가] </a>
							</c:if>
						</c:if>
				</c:if>
				</div>
				<div class="userblog_admin">
					<c:if test="${loginUserProvider.loggedIn }">
						<c:if test="${loginUserProvider.loginUser.loginId == blog.owner }">
							<a href="${pageContext.request.contextPath }/${blog.id}/admin">관리자</a>
						</c:if>
					</c:if>
				</div>
				<div class="searchBox">
					<form action="${pageContext.request.contextPath }/search" method="get">
						<input type="text" name="query" placeholder="search" class="input_box" />
							<input type="submit" value="Search" class="btn" />
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
			<div class="nav-title">${loginUserProvider.loginUser.name }님</div>
			<ul class="nav-menu">
				<li><a href="${pageContext.request.contextPath }/settings/user">계정설정</a></li>
				<hr />
				<li><a href="${pageContext.request.contextPath }/logout">로그아웃</a></li>
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
			<div class="clear"></div>
			<div class="menu_bar">
				<div class="visitorCount">today : ${blog.todayCount } / total : ${blog.totalCount }</div>
				<nav class="nav2">
					<div class="nav-notification">[이 블로그에 대한 최근 활동]</div>
					<ul class="nav-notify-menu">
							
							<c:forEach items="${notificationList }" var="notification">
								<li>
								<a href="${pageContext.request.contextPath }${notification.uri }">
							${notification.contents } | ${notification.dateCreated }
								</a>
								</li>
								</c:forEach>
					</ul>
				</nav>
				<div class="guestbook">
					<a href="${pageContext.request.contextPath }/${blog.id}/guestbook">방명록</a>
				</div>
			</div>
		</header>
	<section>