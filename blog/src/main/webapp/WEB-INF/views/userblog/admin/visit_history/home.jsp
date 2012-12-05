<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />


<div class="group">
	<div class="groupTitle theme-a">
		방문자 목록
	</div>
	<div class="groupContents">
		<table class="table">
			<thead>
				<tr>
					<th style="min-width:300px;">방문 IP</th>
					<th>접속 시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${visitHistoryList }" var="visitHistory">
				<tr>
					<td>${visitHistory.connectIPAddress }</td>
					<td><spring:eval expression="visitHistory.visited" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<jsp:include page="/WEB-INF/views/common/page.jsp" />
	</div>
</div>
<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />