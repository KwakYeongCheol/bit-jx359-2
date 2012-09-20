<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	${blogId } 카테고리 페이지
	
	<div>
		<a href="${pageContext.request.contextPath }/${loginUser.loginId }/admin/category/add">카테고리 추가</a>
	</div>
	<div>
		카테고리 목록
		<div>
			<c:forEach items="${categoryList }" var="category">
			<div>
				${category.title } | 
				<a href="${pageContext.request.contextPath }/${loginUser.loginId}/admin/category/modify?id=${category.id}">수정</a> |
				<a href="${pageContext.request.contextPath }/${loginUser.loginId}/admin/category/delete?id=${category.id}">삭제</a><br />
			</div>
			</c:forEach>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />