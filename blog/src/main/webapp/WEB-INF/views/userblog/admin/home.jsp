<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />


<div class="infoBox">
	<div class="infoBoxTitle">
		글 목록<span style="float:right;font-size:10px;"><a href="${pageContext.request.contextPath }/${blog.id}/admin/post">더보기</a></span>
	</div>
	<div class="infoBoxContents">
	<hr />
	<table>
	<c:forEach items="${postList }" var="post">
		<tr>
			<th class="txt"><a href="${pageContext.request.contextPath }/${blog.id}/${post.displayId}">${post.title }</a></th>
			<td class="date"><fmt:formatDate pattern="yy-MM-dd" value="${post.dateCreated }" /></td>
		</tr>
	</c:forEach>
	</table>
	<hr />
	</div>
</div>

<div class="infoBox">	
	<div class="infoBoxTitle">
		최근 목록<span style="float:right;font-size:10px;"><a href="${pageContext.request.contextPath }/${blog.id}/admin/guestbook">더보기</a></span>
	</div>
	<div class="infoBoxContents">
	<hr />
	<table>
	<c:forEach items="${guestbookList }" var="guestbook">
		<tr>
			<th class="txt"><a href="${pageContext.request.contextPath }/${blog.id}/guestbook/${guestbook.displayId}">${guestbook.contents }</a></th>
			<td class="date"><fmt:formatDate pattern="yy-MM-dd" value="${guestbook.dateCreated }" /></td>
		</tr>
	</c:forEach>
	</table>
	<hr />
	</div>
</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />