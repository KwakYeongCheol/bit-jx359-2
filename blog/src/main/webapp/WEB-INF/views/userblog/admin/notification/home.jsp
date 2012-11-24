<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />


<div class="group">
	<div class="groupTitle theme-a">
		알림 목록
	</div>
	<div class="groupContents">
		<table class="table">
			<thead>
				<tr>
					<th style="min-width:500px;">
						내용
					</th>
					<th>
						공개
					</th>
					<th>
						날짜
					</th>
					<th>
						삭제
					</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${adminNotificationList.isEmpty() }">
				<tr>
					<td colspan="4" style="text-align: center;">
						최근 알림이 존재하지 않습니다.
					</td>
				</tr>
				</c:if>
				<c:forEach items="${adminNotificationList }" var="notification">
				<tr>
					<td>
						${notification.contents }
					</td>
					<td>
						<c:if test="${notification.isPublic }">공개</c:if>
						<c:if test="${!notification.isPublic }">비공개</c:if>
					</td>
					<td>
						<spring:eval expression="notification.dateCreated" />
					</td>
					<td>
						<a href="${pageContext.request.contextPath }/${blog.id}/admin/notification/delete?id=${notification.id}&redirectURI=${pageURI }">삭제</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/views/common/page.jsp" />
	</div>

</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />




























