<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="common/header.jsp" />

<div>
	HOME PAGE
</div>
	<p>${blog.id }</p>
	<p>${blog.title }</p>
	<p>${blog.owner }</p>
	<p>${blog.dateCreated }</p>

<jsp:include page="common/footer.jsp" />