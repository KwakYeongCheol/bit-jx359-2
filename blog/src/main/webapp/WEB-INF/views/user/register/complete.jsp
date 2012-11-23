<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div style="margin:50px 0 0 30px;">
	<h2>가입을 축하합니다!</h2>
	<hr />
	<strong>${loginUserProvider.loginUser.name }(${loginUserProvider.loginUser.loginId })</strong>님 회원가입이 정상적으로 완료되었습니다.
	<div style="margin-top:30px;">
		<a href="${pageContext.request.contextPath }/">메인 페이지로</a> | 
		<a href="${pageContext.request.contextPath }/settings/blog/create">블로그 생성하기</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />