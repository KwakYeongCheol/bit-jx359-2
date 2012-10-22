<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div id="register">
	<h2>회원가입 성공</h2>
	
	<div>
		${loginUserProvider.loginUser.name }님 (${loginUserProvider.loginUser.loginId }) 가입을 축하드립니다. <br />
	</div>
	
	<div>
		<a href="${pageContext.request.contextPath }/">메인 페이지로</a> | 
		<a href="${pageContext.request.contextPath }/blog/create">블로그 생성하기</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />