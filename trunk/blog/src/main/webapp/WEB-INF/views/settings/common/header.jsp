<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
		<div class="menu">
			<div class="menu-title">설정</div>
			<hr />
			<div class="menu-group">
				<ul>
					<li><a href="${pageContext.request.contextPath }/settings/user">개인 정보</a></li>
					<li><a href="${pageContext.request.contextPath }/settings/user/changePassword">비밀번호 변경</a></li>
					<li><a href="${pageContext.request.contextPath }/settings/user/withdraw">회원 탈퇴</a></li>
				</ul>				
			</div>
			<hr />
			<div class="menu-group">
				<ul>
					<li><a href="${pageContext.request.contextPath }/settings/blog">블로그 목록</a></li>
					<li><a href="${pageContext.request.contextPath }/settings/blog/create">블로그 생성하기</a></li>
				</ul>				
			</div>
			<hr />
			<div class="menu-group">
				<ul>
					<li><a href="${pageContext.request.contextPath }/settings/favorite">이웃 목록</a></li>
				</ul>				
			</div>
		</div>
		<article class="articleB">