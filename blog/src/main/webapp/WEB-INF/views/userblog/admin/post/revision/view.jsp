<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />


<div class="group">
	<div class="groupTitle theme-a">
		변경 내역 목록
	</div>
	<div class="groupContents">
		
		<span style="font-weight:bold;font-size:20px;margin-left:10px">
			<a href="${pageContext.request.contextPath }/${post.blogId}/${post.displayId}">${post.title }</a>
		</span>
	
		<table class="table" style="margin-top:30px;">
			<thead>
				<tr>
					<th>버전</th>
					<th>변경 내역</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${postRevisionList }" var="postRevision">
				<tr>
					<td>${postRevision.displayId }</td>
					<td>${postRevision.diff }</td>
				</tr>
				</c:forEach>
				<c:if test="${postRevisionList.isEmpty() }">
				<tr>
					<td colspan="2" style="text-align: center;">
						변경내역이 존재하지 않습니다.
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		<jsp:include page="/WEB-INF/views/common/page.jsp" />
	</div>
</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />




























