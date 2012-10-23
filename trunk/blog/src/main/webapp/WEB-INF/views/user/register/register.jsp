<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	<form:form modelAttribute="user" id="registerForm" action="${pageContext.request.contextPath }/user/register/register" method="post">
		<legend>회원가입</legend>
		<div class="group">
			<div class="groupTitle">기본정보</div>
			<div class="groupContents">
				<div class="row">
					<div class="label">성 :</div>
					<form:input path="lastName" cssClass="input" placeholder="홍" autofocus="true" />
					<form:errors path="lastName" cssClass="error" />
				</div>
				<div class="row">
					<div class="label">이름 :</div>
					<form:input path="firstName" cssClass="input" placeholder="길동" />
					<form:errors path="firstName" cssClass="error" />
				</div>
				<div class="row">
					<div class="label">이메일 주소 :</div>
					<form:input path="loginId" cssClass="input" placeholder="sample@gmail.com" />
					<form:errors path="loginId" cssClass="error" />
				</div>
				<div class="row">
					<div class="label">비밀번호 (8자리 이상) :</div>
					<form:password path="password" cssClass="input" placeholder="password" />
					<form:errors path="password" cssClass="error" />
				</div>
				<div class="row">
					<div class="label">비밀번호 확인 :</div>
					<input class="input" type="password" id="passwordConfirm" name="passwordConfirm" placeholder="password confirm" />
				</div>
			</div>
		</div>

		<div class="group">
			<div class="groupTitle">추가정보</div>
			<div class="groupContents">
				<div class="row">
					<div class="label">성별 :</div>
					<form:radiobutton path="sex" value="male" checked="true" />남
					<form:radiobutton path="sex" value="female" />여
				</div>
				<div class="row">
					<div class="label">생년월일 :</div>
					<select name="year">
						<option value="2012">2012</option>
						<option value="2011">2011</option>
					</select> 년 <select name="month">
						<option value="10">10</option>
						<option value="9">9</option>
					</select> 월 <select name="day">
						<option value="20">20</option>
						<option value="21">21</option>
					</select> 일
				</div>
			</div>
		</div>

		<div class="submit">
			<input type="submit" value="Register" />
		</div>
	</form:form>
</div>

<script>
$(document).ready(function() {
	$('#loginIdCheck').click(function() {
		$.getJSON('${pageContext.request.contextPath}/user/register/checkLoginId/' + $('#loginId').val(), function(result) {
			if (result.duplicated == true) {
				alert('이미 등록된 로그인 ID입니다. ');
				$('#loginId').val('');
			} else {
				alert('사용할 수 있습니다.');
			}
		});
	});
});
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />