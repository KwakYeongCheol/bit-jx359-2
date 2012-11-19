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
					<th>삭제</th>
					<th>작성자</th>
					<th>작성일</th>
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
					<td><a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?displayId=${comment.displayId}&targetId=${comment.targetDisplayId}&type=${comment.targetType}">삭제</a></td>
					<td>
						${comment.writer.name }
					</td>
					<td><spring:eval expression="comment.dateCreated" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${page.count > 0}">
		<div class="page">
			<ul>
			<c:set var="pageCount" value="${page.count / page.pageSize + ( page.count % page.pageSize == 0 ? 0 : 1)}" />
			<c:set var="startPage" value="${page.pageGroupSize*(page.numPageGroup-1)+1}" />
			<c:set var="endPage" value="${startPage + page.pageGroupSize-1}" />
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>
			<c:if test="${page.numPageGroup > 1}">
				<li><a href="${pageContext.request.contextPath }/${blog.id }/admin/guestbook?pageNumber=${(page.numPageGroup-2)*page.pageGroupSize+1 }">[이전]</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li><a href="${pageContext.request.contextPath }/${blog.id }/admin/guestbook?pageNumber=${i}">[
					<font color="#000000" /> <c:if test="${page.currentPage == i}">
						<font color="#bbbbbb" />
					</c:if> ${i} </font>]
				</a></li>
			</c:forEach>

			<c:if test="${page.numPageGroup < page.pageGroupCount}">
				<li><a href="${pageContext.request.contextPath }/${blog.id }/admin/guestbook?pageNumber=${page.numPageGroup*page.pageGroupSize+1}">[다음]</a></li>
			</c:if>
			</ul>
		</div>
		</c:if>
	</div>
	
	

</div>
<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />