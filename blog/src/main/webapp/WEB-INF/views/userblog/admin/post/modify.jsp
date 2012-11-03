<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/ckeditor/ckeditor.js"></script>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />
</head>
<body>

<div id="blogArticles">
	<form:form modelAttribute="post" action="${pageContext.request.contextPath }/${blog.id }/admin/post/modifyAction" method="POST">
		
		<input type="hidden" name="displayId" value="${post.displayId }" />
		<div>
			<form:label path="category.displayId">Category</form:label>
			<form:select path="category.displayId">
				<c:forEach items="${categoryList}" var="category">
					<form:option value="${category.displayId }">
						${category.title }
					</form:option>
				</c:forEach>
			</form:select>
		</div>
		<div>
			<form:errors cssClass="errors" path="title"></form:errors>
			<form:label cssClass="label" path="title">Title</form:label>
			<form:input cssClass="input" path="title"/>
		</div>
		<div>
			<form:errors cssClass="errors" path="contents"></form:errors>
			<form:label cssClass="label" path="contents">Contents</form:label>
			<form:textarea path="contents" />
		</div>
		<div>
			<input type="submit" value="수정" />
		</div>
	</form:form>
</div>

<script type="text/javascript">
	CKEDITOR.replace('contents',{
		enterMode:'2',
	    shiftEnterMode:'3',
		width:'100%',
	    height:'150',
	    toolbar:[
	    ['Bold','Italic','-','NumberedList','BulletedList','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','Styles','Format','Font','FontSize']],
	    skin:'office2003'
	});
</script>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />