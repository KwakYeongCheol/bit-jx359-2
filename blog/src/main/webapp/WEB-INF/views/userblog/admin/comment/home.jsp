<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />


<div class="group">
	<div class="groupTitle theme-a">
		댓글 목록
	</div>
	<div class="groupContents">
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${commentList }" var="comment">
				<tr>
					<td>${comment.displayId }</td>
					<td>
						<a href="${pageContext.request.contextPath}${comment.targetUri }">
							${comment.contents }
						</a>
					</td>
					<td>
						${comment.writer.name }
					</td>
					<td><spring:eval expression="comment.dateCreated" /></td>
					<td><a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?displayId=${comment.displayId}&targetId=${comment.targetDisplayId}&type=${comment.targetType}">삭제</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<jsp:include page="/WEB-INF/views/common/page.jsp" />
	</div>
</div>
<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />