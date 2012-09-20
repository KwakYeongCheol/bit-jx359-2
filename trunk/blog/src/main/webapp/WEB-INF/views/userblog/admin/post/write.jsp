<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	<form action="${pageContext.request.contextPath }/${loginUser.loginId }/admin/post/writeAction" method="POST">
		<label>카테고리 </label>
		<select name="categoryId">
			<c:forEach items="${categoryList }" var="category">
				<option value="${category.id }">${category.title }</option>
			</c:forEach>
		</select>
		<label>Title </label><input type="text" name="title" /> <br />
		<label>Contents </label><input type="text" name="contents" /> <br />
		<input type="submit" value="글쓰기" />
	</form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />