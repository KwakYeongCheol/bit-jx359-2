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
			<form:input cssClass="input" path="name" size="20" maxlength="20" /> 
		</div>
		<div>
			<form:label cssClass="label" path="loginId">아이디:</form:label>
			<form:input cssClass="input" path="loginId" size="20" maxlength="20" />
		</div>
		<div>
			<input type="button" id="loginIdCheck" name="loginIdCheck" value="아이디 중복 검사" />
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

<script>
	$(document).ready(function(){
		$('#loginIdCheck').click(function(){
			$.getJSON('${pageContext.request.contextPath}/user/register/checkLoginId/' + $('#loginId').val(), function(result){
				if(result.duplicated == true){
					alert('이미 등록된 로그인 ID입니다. ');
					$('#loginId').val('');
				}else{
					alert('사용할 수 있습니다.');
				}
			});
		});
	});
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />