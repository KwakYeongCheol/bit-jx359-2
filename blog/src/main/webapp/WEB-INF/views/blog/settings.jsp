<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="blogSetting">
	<h2>블로그 정보</h2>
	<c:if test="${loginUserProvider.blog != null }">
	<form:form modelAttribute="blog" action="${pageContext.request.contextPath }/blog/modify" method="POST">
	<form:errors cssClass="error" path="title"></form:errors>
		<div>
			<label class="label">Blog Title</label>
			<input class="input" type="text" name="title" value="${loginUserProvider.blog.title }" autofocus />
		</div>
		<div>
			<input class="submit" type="submit" value="블로그 수정" />
		</div>
	</form:form>
	</c:if>

	<c:if test="${loginUserProvider.blog == null }">
	<form:form modelAttribute="blog" action="${pageContext.request.contextPath }/blog/create" method="POST">
	<form:errors cssClass="error" path="title"></form:errors>
		<div>
			<label class="label">Blog Title</label>
			<input class="input" type="text" name="title" autofocus/>
		</div>
		<div>
			<input class="submit" type="submit" value="블로그 생성" />
		</div>
	</form:form>
	</c:if>
</div>
	
<jsp:include page="/WEB-INF/views/common/footer.jsp" />