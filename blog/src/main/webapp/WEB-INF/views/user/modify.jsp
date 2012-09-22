<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="userInfo">
	<form action="${pageContext.request.contextPath }/user/modifyAction" method="post">
		<div>개인 정보 수정&nbsp; &nbsp; 
			<p>
				<label>아이디</label>
				${user.loginId }  
			</p>
			<p>
				<label>이름</label>
				${user.name }  
			</p>
			<p>
				<label>현재 비밀번호</label>
				<input type="password"  name="password">
			</p>
			<p>
				<label>새 비밀번호</label>
				<input type="password"  name="newPassword">
			</p>				
			<input type="submit" value="수정">
			<input type="button" value="취소" onclick="javascript:history.back();">				
	</form>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />