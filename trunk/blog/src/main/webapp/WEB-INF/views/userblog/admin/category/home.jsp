<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div class="group">

</div>

<div class="group" style="margin-top:40px;">
	<div class="groupTitle theme-a">
		카테고리 목록 
	</div>
	<div class="groupContents">
		<form:form modelAttribute="category" action="${pageContext.request.contextPath }/${blog.id }/admin/category/add" method="POST">
			<form:errors cssClass="error" path="title"></form:errors>
			<form:input path="title" cssStyle="width:250px;" autofocus="autofocus" />
			<input style="margin-left:15px;" type="submit" value="카테고리 추가" />
		</form:form>
		
		<hr />
		
		<table class="table" style="margin-top:30px;">
			<thead>
				<tr>
					<th style="min-width:300px;">카테고리명</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${categoryList }" var="category">
				<tr>
					<td><a href="${pageContext.request.contextPath }/${blog.id}/category/${category.displayId}">${category.title }</a></td> 
					<td><a href="${pageContext.request.contextPath }/${blog.id}/admin/category/modify?displayId=${category.displayId}">수정</a></td>
					<td><a href="${pageContext.request.contextPath }/${blog.id}/admin/category/delete?displayId=${category.displayId}">삭제</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />