<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div class="complete">
	<h2>축하합니다!</h2>
	<strong>${loginUserProvider.loginUser.name }(${loginUserProvider.loginUser.loginId })</strong>님 회원가입이 정상적으로 완료되었습니다.
	
	<div class="nextStep">
		<a href="${pageContext.request.contextPath }/">메인 페이지로</a> | 
		<a href="${pageContext.request.contextPath }/settings/blog/create">블로그 생성하기</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />