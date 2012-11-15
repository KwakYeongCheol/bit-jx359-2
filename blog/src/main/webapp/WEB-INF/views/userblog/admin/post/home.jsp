<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />


<div class="group">
	<div class="groupTitle theme-a">
		글 목록
	</div>
	<div class="groupContents">
		<table class="table">
			<thead>
				<tr>
					<th>카테고리</th>
					<th>제목</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${postList }" var="post">
				<tr>
					<td>${post.category.title }</td>
					<td>${post.title }</td>
					<td>${post.dateCreated }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>
<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />