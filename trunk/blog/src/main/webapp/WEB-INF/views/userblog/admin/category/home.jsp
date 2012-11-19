<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div class="group">
<form:form modelAttribute="category" action="${pageContext.request.contextPath }/${blog.id }/admin/category/add" method="POST">
	<form:errors cssClass="error" path="title"></form:errors>
	<form:input path="title" cssStyle="width:250px;" autofocus="autofocus" />
	<input style="margin-left:15px;" type="submit" value="카테고리 추가" />
</form:form>
</div>

<div class="group">
	<div class="groupTitle theme-a">
		카테고리 목록 <a href="${pageContext.request.contextPath }/${blog.id }/admin/category/add">카테고리 추가</a> 
	</div>
	<div class="groupContents">
		<c:forEach items="${categoryList }" var="category">
		<div>
			${category.title } (order value : ${category.orderValue }) | 
			<a href="${pageContext.request.contextPath }/${blog.id}/admin/category/modify?displayId=${category.displayId}">수정</a> |
			<a href="${pageContext.request.contextPath }/${blog.id}/admin/category/delete?displayId=${category.displayId}">삭제</a><br />
		</div>
		</c:forEach>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />