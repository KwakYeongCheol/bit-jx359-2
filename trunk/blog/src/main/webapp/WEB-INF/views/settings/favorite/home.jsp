<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/settings/common/header.jsp"></jsp:include>

<div class="group">
	<div class="groupTitle theme-a">
		이웃 목록
	</div>
	<div class="groupContents">
		<c:forEach items="${loginUserProvider.loginUser.favoriteList }" var="favorite">
		<div class="row">
			<div class="key">
				<a href="${pageContext.request.contextPath }/${favorite.blog.id }">${favorite.blog.title }</a> :
			</div>
			<div class="value">
				<a href="#">삭제하기</a>
			</div>
		</div>
		</c:forEach>
	</div>
</div>

<jsp:include page="/WEB-INF/views/settings/common/footer.jsp"></jsp:include>