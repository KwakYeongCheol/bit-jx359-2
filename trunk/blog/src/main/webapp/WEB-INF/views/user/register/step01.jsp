<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	<form:form modelAttribute="user" action="${pageContext.request.contextPath }/user/register/register" method="POST">
		<p>
			<form:label path="name">이름:</form:label><br/>
			<form:input path="name" size="20" maxlength="20" /> 
		</p>
		<p>
			<form:label path="loginId">아이디:</form:label><br/>
			<form:input path="loginId" size="20" maxlength="20" /> 
		</p>
		<p>
			<form:label path="">비밀번호:</form:label><br/>
			<form:password path="password" showPassword="true" size="20" maxlength="12" /> 
		</p>
		<p>
			<input type="submit" value="회원가입" />
		</p>
	</form:form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />