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
	<script src="${pageContext.request.contextPath }/resources/js/jquery-1.8.2.js"></script>
</head>

<body>
<div id="wrap">
	<div id="header">
		<ul>
			<li><a href="${pageContext.request.contextPath }/user/register/step01">회원가입</a></li>
			<c:if test="${loginUserProvider.loggedIn }">
			<li><a href="${pageContext.request.contextPath }/logout">로그아웃</a></li>
			<li>${loginUserProvider.loginUser.loginId } 님</li>
			</c:if>
			<c:if test="${!loginUserProvider.loggedIn }">
			<li><a href="${pageContext.request.contextPath }/login">로그인</a></li>
			</c:if>
			<li><a href="${pageContext.request.contextPath }/">홈</a></li>
		
			<c:if test="${loginUserProvider.loggedIn }">
			<c:forEach items="${loginUserProvider.blogList }" var="blog">
			<li>내 : <a href="${pageContext.request.contextPath }/${blog.id }">${blog.title }</a></li>
			</c:forEach>
		
			<c:forEach items="${loginUserProvider.loginUser.favoriteList }" var="favorite">
			<li>이웃 : <a href="${pageContext.request.contextPath }/${favorite.blog.id }">${favorite.blog.title }</a></li>
			</c:forEach>
			</c:if>
			<li>
			<form action="${pageContext.request.contextPath }/search" method="post">
				<input type="text" name="query" />
				<input type="submit" value="검색" />
			</form>
			</li>
		</ul>
	</div>
	
	<div id="contents">

	<div id="blog">
		<div id="blogHeader">
			<div id="blogTitle">
				${blog.title }
				<c:if test="${loginUserProvider.loggedIn }">
					<c:if test="${!loginUserProvider.isMyBlog(blog.id) }">
						<c:if test="${!loginUserProvider.isExistFavorite(blog.id) }">
						<a href="${pageContext.request.contextPath }/favorite/addAction?blogId=${blog.id}">이웃 추가</a>
						</c:if>
					</c:if>
				</c:if>
				today : ${blog.todayCount } / total : ${blog.totalCount }
			</div>
		</div>
			
		<div id="blogMenu">
			<ul>
				<li><a href="${pageContext.request.contextPath }/${blog.id}/">홈</a></li>
				<li><a href="${pageContext.request.contextPath }/${blog.id}/guestbook">방명록</a></li>
				<c:if test="${loginUserProvider.loggedIn }">
				<c:if test="${loginUserProvider.loginUser.loginId == blog.owner }">
				<li><a href="${pageContext.request.contextPath }/${blog.id}/admin/post/write">글쓰기</a></li> 
				<li><a href="${pageContext.request.contextPath }/${blog.id}/admin">관리자</a></li>
				</c:if>
				</c:if>
			</ul> 
		</div>
		
		<div id="blogContents">
			<div id="blogCategory">
				<c:forEach items="${categoryList }" var="category">
					<a href="${pageContext.request.contextPath }/${blog.id}/category/${category.displayId}">${category.title }</a><br />
				</c:forEach>
			</div>