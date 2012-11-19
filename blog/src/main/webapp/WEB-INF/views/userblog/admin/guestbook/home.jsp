<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div class="group">
	<div class="groupTitle theme-a">
		방명록 목록
	</div>
	<div class="groupContents">
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>내용</th>
					<th>삭제</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${guestbookList }" var="guestbook">
				<tr>
					<td>${guestbook.displayId }</td>
					<td>
						<a href="${pageContext.request.contextPath }/${blog.id}/guestbook?displayId=${guestbook.displayId}">${guestbook.contents }</a>
					</td>
					<td><a href="${pageContext.request.contextPath }/${blog.id}/guestbook/delete?displayId=${guestbook.displayId}&redirectURI=${pageURI }">삭제</a></td>
					<td>
						${guestbook.writer.name }
					</td>
					<td><spring:eval expression="guestbook.dateCreated" /></td>
				</tr>
				</c:forEach>
				<c:if test="${guestbookList.isEmpty() }">
				<tr>
					<td colspan="5" style="text-align: center;">
						방명록이 존재하지 않습니다.
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		
		<jsp:include page="/WEB-INF/views/common/page.jsp" />
	</div>
</div>
<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />