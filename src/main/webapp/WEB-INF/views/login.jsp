<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="common/header.jsp" />

<div>
	<form action="${pageContext.request.contextPath }/loginAction" method="POST">
		<label>ID </label><input type="text" name="loginId" /> <br />
		<label>Password </label><input type="password" name="password" /> <br />
		<input type="submit" value="로그인" />
	</form>
</div>

<jsp:include page="common/footer.jsp" />