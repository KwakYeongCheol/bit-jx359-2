<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div id="articles">
	<form:form modelAttribute="post" action="${pageContext.request.contextPath }/${blog.id }/admin/post/writeAction" method="POST">
		<p>
			<form:label path="category.id">카테고리</form:label>
			<form:select path="category.id">
				<c:forEach items="${categoryList }" var="category">
					<form:option value="${category.id }">${category.title }</form:option>
				</c:forEach>
			</form:select>
		</p>
		<p>
			<form:label path="title">Title</form:label>
			<form:input path="title"/>
		</p>
		<p>
			<form:label path="contents">Contents</form:label>
			<form:input path="contents"/>
		</p>
		<p>
			<input type="submit" value="글쓰기" />
		</p>
	</form:form>
</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />