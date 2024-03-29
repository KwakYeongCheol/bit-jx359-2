<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />
<div class="contentsWrap">
	<div style="min-height: 190px;">
		<div class="infoBox">
			<div class="infoBoxTitle">
				컨텐츠 정보
			</div>
			<div class="infoBoxContents">
			<hr />
			<table>
				<tr>
					<th class="txt">글</th>
					<td class="name">${blog.totalPostCount }</td>
				</tr>
				<tr>
					<th class="txt">방명록</th>
					<td class="name">${blog.totalGuestbookCount }</td>
				</tr>
				<tr>
					<th class="txt">댓글</th>
					<td class="name">${blog.totalCommentCount }</td>
				</tr>
				<tr>
					<th class="txt">스크랩</th>
					<td class="name">${blog.totalScrapCount }</td>
				</tr>
			</table>
			<hr />
			</div>
		</div>
		
		<div class="infoBox">
			<div class="infoBoxTitle">
				최근 알림<span style="float:right;font-size:10px;"><a href="/1/admin/notification">더보기</a></span>
			</div>
			<div class="infoBoxContents">
			<hr />
			<c:if test="${adminNotificationList.isEmpty() }">
			<div class="adminNotificationLine">
				최근 알림이 없습니다.
			</div>
			</c:if>
			
			<c:forEach items="${adminNotificationList }" var="notification">
			<div class="adminNotificationLine">
				${notification.contents } | <span class="date"><fmt:formatDate pattern="yy-MM-dd" value="${notification.dateCreated }" /></span>
			</div>
			</c:forEach>

			<hr />
			</div>
		</div>
	</div>
	
	<div class="notificationCenter">
		<h3>알림센터</h3>
		<hr />
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
				<c:if test="${postList.isEmpty() }">
				<tr>
					<td colspan="2">
						최근에 작성한 글이 없습니다.
					</td>
				</tr>
				</c:if>
			</table>
			<hr />
			</div>
		</div>
		
		<div class="infoBox">	
			<div class="infoBoxTitle">
				최근 방명록<span style="float:right;font-size:10px;"><a href="${pageContext.request.contextPath }/${blog.id}/admin/guestbook">더보기</a></span>
			</div>
			<div class="infoBoxContents">
			<hr />
			<table>
				<c:forEach items="${guestbookList }" var="guestbook">
				<tr>
					<th class="txt"><a href="${pageContext.request.contextPath }/${blog.id}/guestbook/${guestbook.displayId}">${guestbook.contents }</a></th>
					<td class="name">${guestbook.writer.name }</td>
				</tr>
				</c:forEach>
				<c:if test="${guestbookList.isEmpty() }">
				<tr>
					<td colspan="2">
						최근에 작성된 방명록이 없습니다.
					</td>
				</tr>
				</c:if>
			</table>
			<hr />
			</div>
		</div>
		
		<div class="infoBox">	
			<div class="infoBoxTitle">
				최근 댓글<span style="float:right;font-size:10px;"><a href="${pageContext.request.contextPath }/${blog.id}/admin/comment">더보기</a></span>
			</div>
			<div class="infoBoxContents">
			<hr />
			<table>
				<c:forEach items="${commentList }" var="comment">
				<tr>
					<th class="txt">
						<a href="${pageContext.request.contextPath }${comment.targetUri}">
							${comment.contents }
						</a>
					</th>
					<td class="name">${comment.writer.name }</td>
				</tr>
				</c:forEach>
				<c:if test="${commentList.isEmpty() }">
				<tr>
					<td colspan="2">
						최근 댓글이 없습니다.
					</td>
				</tr>
				</c:if>
			</table>
			<hr />
			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />