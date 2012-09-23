<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="register">
	<h2>회원가입</h2>
	<form:form modelAttribute="user" action="${pageContext.request.contextPath }/user/register/register" method="POST">
		<form:errors cssClass="error" path="loginId"></form:errors>
		<form:errors cssClass="error" path="password"></form:errors>
		
		<div>
			<form:label cssClass="label" path="name">이름:</form:label>
			<form:input cssClass="input" path="name" size="20" maxlength="20" autofocus /> 
		</div>
		<div>
			<form:label cssClass="label" path="loginId">아이디:</form:label>
			<form:input cssClass="input" path="loginId" size="20" maxlength="20" />
		</div>
		<div>
			<form:label cssClass="label" path="">비밀번호:</form:label>
			<form:password cssClass="input" path="password" showPassword="false" size="20" maxlength="12" />
		</div>
		<div>
			<input class="submit" type="submit" value="회원가입" />
		</div>
	</form:form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />