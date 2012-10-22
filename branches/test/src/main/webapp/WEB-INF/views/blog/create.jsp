<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="blogSetting">
	<h2>블로그 정보</h2>

	<form:form modelAttribute="blog" action="${pageContext.request.contextPath }/blog/create" method="POST">
	<form:errors cssClass="error" path="title"></form:errors>
		<div>
			<label class="label">Blog Title</label>
			<input class="input" type="text" name="title" autofocus/>
		</div>
		<div>
			<label class="label">블로그 주소</label>
			http://sample.kr/<input class="input" type="text" name="id"></label>
		</div>
		<div>
			<input class="submit" type="submit" value="블로그 생성" />
		</div>
	</form:form>
</div>
	
<jsp:include page="/WEB-INF/views/common/footer.jsp" />