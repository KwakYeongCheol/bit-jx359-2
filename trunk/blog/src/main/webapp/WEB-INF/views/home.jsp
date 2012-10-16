<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="common/header.jsp" />

<div id="blogInfo">
	<c:if test="${loginUserProvider.loggedIn }">
	<div>내 블로그 &nbsp; &nbsp; 
		<a href="${pageContext.request.contextPath }/blog">블로그 설정</a>
		<a href="${pageContext.request.contextPath }/user/home">회원 정보</a>
		<a href="${pageContext.request.contextPath }/favorite">이웃들</a> <br /><br />
	</div>
	<br /><br /><br />
	</c:if>
</div>

<jsp:include page="common/footer.jsp" />