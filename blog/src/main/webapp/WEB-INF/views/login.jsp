<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="common/header.jsp" />

<div id="login">
	<form id="loginForm" action="${pageContext.request.contextPath }/loginAction" method="POST">
		<c:if test="${redirectURI != null}">
		<input type="hidden" name="redirectURI" value="${redirectURI }" />
		</c:if>
		<h2>Login</h2>
		<div>
			<div class="label">ID</div><input type="text" class="input" name="loginId" autofocus />
		</div>
		<div>
			<div class="label">Password</div><input type="password" class="input" name="password" />
		</div>
		<div>
			<input type="submit" class="submit" value="로그인" />
			<a href="${pageContext.request.contextPath }/user/register/step01" class="register">회원가입</a>
		</div>
		 
		
	</form>
</div>

<jsp:include page="common/footer.jsp" />