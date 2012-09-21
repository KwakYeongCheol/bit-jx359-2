<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	${blogId } 관리자 페이지
	
	<div>
		<a href="${pageContext.request.contextPath }/${loginUser.loginId }/admin/post/write">글 쓰기</a>
		<a href="${pageContext.request.contextPath }/${loginUser.loginId }/admin/category">카테고리 설정</a>
	</div>
	<div>
		글 목록
		<div>
			<c:forEach items="${postList }" var="post">
			<div>
				<a href="${pageContext.request.contextPath }/${blog.id}/category/${post.category.id}">${post.category.title }</a> | 
				${post.title } | 
				${post.dateCreated } | 
				<a href="${pageContext.request.contextPath }/${loginUser.loginId}/admin/post/modify?id=${post.id}">수정</a> |
				<a href="${pageContext.request.contextPath }/${loginUser.loginId}/admin/post/delete?id=${post.id}">삭제</a><br />
				${post.contents }
			</div>
			</c:forEach>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />