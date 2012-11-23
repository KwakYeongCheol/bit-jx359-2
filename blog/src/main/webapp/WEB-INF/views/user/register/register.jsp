<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<article>
	<div class="articleWrap">
		<div class="articleTitle">
			회원가입
		</div>
		<form:form modelAttribute="user" id="registerForm" action="${pageContext.request.contextPath }/user/register/register" onsubmit="return registerFormCheck();" method="post">
			<div class="group">
				<div class="groupTitle">기본정보</div>
				<div class="groupContents">
					<div class="row">
						<form:errors path="lastName" cssClass="error" />
						<div class="key">성 : </div>
						<div class="value">
							<form:input type="text" path="lastName" placeholder="홍" autofocus="autofocus" />
						</div>
					</div>
					<div class="row">
						<form:errors path="firstName" cssClass="error" />
						<div class="key">이름 : </div>
						<div class="value">
							<form:input path="firstName" placeholder="길동"/>
						</div>
					</div>
					<div class="row">
						<form:errors path="loginId" cssClass="error" />
						<div class="key">이메일 주소 : </div>
						<div class="value">
							<form:input path="loginId" placeholder="sample@gmail.com"/>
						</div>
					</div>
					<div class="row">
						<form:errors path="password" cssClass="error" />
						<div class="key">비밀번호 (8자리 이상) : </div>
						<div class="value">
							<form:password path="password" placeholder="password" />
						</div>
					</div>
					<div class="row">
						<div class="key">비밀번호 확인 : </div>
						<div class="value">
							<input type="password" id="passwordConfirm" name="passwordConfirm" placeholder="password confirm" />
						</div>
					</div>
				</div>					
			</div>		
			
			<div class="group" style="margin-top:40px;">
				<div class="groupTitle">추가정보</div>
				<div class="groupContents">
					<div class="row">
						<div class="key">성별 : </div>
						<div class="value">
							<form:radiobutton cssClass="radio" path="sex" value="male" checked="true" />남 
							<form:radiobutton cssClass="radio" path="sex" value="female" />여
						</div>
					</div>
					<div class="row">
						<div class="key">생년월일 : </div>
						<select id="birthYear" name="year">
							<option value="-1">연도:</option>
						</select>
						<select id="birthMonth" name="month">
							<option value="-1">월:</option>
						</select>
						<select id="birthDay" name="day">
							<option value="-1">일:</option>
						</select>
					</div>
				</div>					
			</div>
			
			<div class="row">
				<div class="submit">
					<input class="inputSubmit" type="submit" value="Register" />
				</div>
			</div>	
		</form:form>
	</div>
</article>

<script>
function registerFormCheck(){
	var birthYear = $("#birthYear").val();
	var birthMonth = $("#birthMonth").val();
	var birthDay = $("#birthDay").val();
	
	if(birthYear == -1 || birthMonth == -1 || birthDay == -1){
		alert("생년월일을 선택해주세요.");
		return false;
	}		

	return true;
}
</script>

<script>
$(document).ready(function() {
	initBirthSelect();	
});

function initBirthSelect(){
	initBirthYear();
	initBirthMonth();
	initBirthDay();
}

function initBirthYear(){
	var date = new Date();
	var startYear = date.getFullYear();
	var endYear = 1900;
	
	for(var i = startYear; i > endYear; i--){
		$("<option>").val(i).html(i).appendTo($("#birthYear"));
	}
}

function initBirthMonth(){
	for(var i = 1; i <= 12; i++){
		$("<option>").val(i).html(i).appendTo($("#birthMonth"));
	}
}

function initBirthDay(){
	for(var i = 1; i <= 31; i++){
		$("<option>").val(i).html(i).appendTo($("#birthDay"));
	}
}


</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />