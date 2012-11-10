<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />

<div id="blogArticles">
	<div class="blogArticle">
		<div style="margin: 5px; ">
			<a href="${pageContext.request.contextPath }/${guestbook.writer.loginId}">${guestbook.writer.loginId }</a> | 
			${guestbook.dateCreated } <br />
			${guestbook.contents } <br />
			
			<c:if test="${loginUserProvider.loggedIn }">
				<c:if test="${loginUserProvider.loginUser.loginId == guestbook.writer.loginId }">
					<a href="${pageContext.request.contextPath }/${blog.id }/guestbook/modify?displayId=${guestbook.displayId}">수정</a>
				</c:if>
				<c:if test="${loginUserProvider.loginUser.loginId == blog.owner || loginUserProvider.loginUser.loginId == guestbook.writer.loginId }">
					<a href="${pageContext.request.contextPath }/${blog.id}/guestbook/delete?displayId=${guestbook.displayId}">삭제</a>
				</c:if>
			</c:if>
		</div>
		
		<c:if test="${loginUserProvider.loggedIn }">
		<div>
			<form action="${pageContext.request.contextPath }/${blog.id}/comment/writeAction" method="post">
				
				<input type="hidden" name="targetDisplayId" value="${guestbook.displayId }" />
				<input type="hidden" name="type" value="guestbook" />
				<input type="text" name="contents" />
				<input type="submit" value="댓글작성" />
			</form>
		</div>
		</c:if>
		<div>
			<c:forEach items="${guestbook.commentList }" var="comment">
			<div>
				${comment.writer.loginId } | ${comment.dateCreated } <br />
				${comment.contents } 
				<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId  }">
				<a href="${pageContext.request.contextPath }/${blog.id }/comment/modify?displayId=${comment.displayId}&targetId=${comment.target.id}&type=guestbook">수정</a>
				</c:if>
				<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId || loginUserProvider.loginUser.loginId == blog.owner }">
				<a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?displayId=${comment.displayId}&targetId=${comment.target.id}&type=guestbook">삭제</a>
				</c:if>
			</div>
			</c:forEach>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />