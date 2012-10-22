<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="articles">
	이웃들 목록 &nbsp; &nbsp;
	<br /><br />
	<div>
		<c:forEach items="${loginUserProvider.favoriteList }" var="favorite">
		<div>
			<a href="${pageContext.request.contextPath }/${favorite.blog.id}">${favorite.blog.title } (${favorite.blog.id}님의 블로그)</a>
			<a href="${pageContext.request.contextPath }/favorite/delete?blogId=${favorite.blog.id}">삭제</a>
		</div>
		</c:forEach>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />