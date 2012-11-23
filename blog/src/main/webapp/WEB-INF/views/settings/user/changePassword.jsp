<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="/WEB-INF/views/settings/common/header.jsp"></jsp:include>

<div class="group">
	<div class="groupTitle">
		비밀번호 변경
	</div>
	<div class="groupContents">
		<form action="${pageContext.request.contextPath }/settings/user/changePassword" method="post">
			<div class="row">
				<div class="key">
					기존 비밀번호 :
				</div>
				<div class="value">
					<input type="password" name="password" autofocus="autofocus" />
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="key">
					새 비밀번호 : 
				</div>
				<div class="value">
					<input type="password" name="newPassword" />
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="key">
					새 비밀번호 확인 : 
				</div>
				<div class="value">
					<input type="password" name="newPasswordConfirm" />
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="submit">
					<input type="submit" value="비밀번호 변경하기" />
				</div>
			</div>
		</form>
	</div>
</div>

<jsp:include page="/WEB-INF/views/settings/common/footer.jsp"></jsp:include>
