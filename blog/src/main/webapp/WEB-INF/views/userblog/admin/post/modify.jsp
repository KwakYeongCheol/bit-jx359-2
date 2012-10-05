<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div id="blogArticles">
	<form:form modelAttribute="post" action="${pageContext.request.contextPath }/${blog.id }/admin/post/modifyAction" method="POST">
		
		<input type="hidden" name="id" value="${post.id }" />
		<div>
			<form:label path="category.id">Category</form:label>
			<form:select path="category.id">
				<c:forEach items="${categoryList}" var="category">
					<form:option value="${category.id }">
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
			<form:input cssClass="input" path="contents"/>
		</div>
		<div>
			<input type="submit" value="수정" />
		</div>
	</form:form>
</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />