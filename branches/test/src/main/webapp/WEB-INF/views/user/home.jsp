<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="userInfo">
		<div>개인 정보 &nbsp; &nbsp; <a href="${pageContext.request.contextPath }/user/modify">정보수정</a>
		</div>
			<p>
				<label>아이디</label>
				${user.loginId }  
			</p>
			<p>
				<label>이름</label>
				${user.name }  
			</p>		
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />