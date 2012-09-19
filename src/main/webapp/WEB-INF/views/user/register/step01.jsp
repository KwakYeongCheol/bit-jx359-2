<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	<form action="${pageContext.request.contextPath }/user/register/register" method="POST">
		<label>ID </label><input type="text" name="loginId" /> <br />
		<label>Password </label><input type="password" name="password" /> <br />
		<label>Name </label><input type="text" name="name" /> <br />
		<input type="submit" value="회원가입" />
	</form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />