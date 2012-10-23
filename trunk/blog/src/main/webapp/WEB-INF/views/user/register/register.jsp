<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="register">
	<h2>회원가입</h2>
	<form:form modelAttribute="user" action="${pageContext.request.contextPath }/user/register/register" method="POST">
		<form:errors cssClass="error" path="loginId"></form:errors>
		<form:errors cssClass="error" path="password"></form:errors>
		
		<div>
			<form:label cssClass="label" path="lastName">성:</form:label>
			<form:input cssClass="input" path="lastName" size="20" maxlength="20" /> 
		</div>
		<div>
			<form:label cssClass="label" path="firstName">이름:</form:label>
			<form:input cssClass="input" path="firstName" size="20" maxlength="20" /> 
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
			<label for="label">비밀번호 확인:</label>
			<input type="password" class="input" name="passwordConfirm" />
		</div>
		<div>
			<form:label cssClass="label" path="sex">성별:</form:label>
			<form:radiobutton path="sex" value="male" />남 <form:radiobutton path="sex" value="female"/>
		</div>
		<div>
			<label for="label" >생년월일</label>
			<select name="year">
				<option value="2012">2012</option>
				<option value="2013">2013</option>
			</select>
			년 
			<select name="month">
				<option value="10">10</option>
				<option value="11">11</option>
			</select>
			월
			<select name="day">
				<option value="20">20</option>
				<option value="21">21</option>
			</select>
			일
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