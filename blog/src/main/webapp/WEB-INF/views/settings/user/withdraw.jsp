<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/settings/common/header.jsp"></jsp:include>

<div class="group">
	<div class="groupTitle">
		회원 탈퇴
	</div>
	<div class="groupContents">
		<div class="row">
			회원을 탈퇴하시면 모든 정보가 삭제됩니다. 
		</div>
		
		<form action="#" method="post">
		<div class="row">
			<div class="key">
				비밀번호 :
			</div>
			<div class="value">
				<input type="password" name="password" />
			</div>
		</div>
		<hr />
		<div class="row">
			<div class="submit">
				<input type="submit" value="회원 탈퇴하기" />
			</div>
		</div>
		</form>
	</div>
</div>

<jsp:include page="/WEB-INF/views/settings/common/footer.jsp"></jsp:include>