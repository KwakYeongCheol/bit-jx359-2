<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="menu">
	<c:forEach items="${categoryList }" var="category">
		<a href="${pageContext.request.contextPath }/${blog.id}/category/${category.id}">${category.title }</a><br />
	</c:forEach>
</div>