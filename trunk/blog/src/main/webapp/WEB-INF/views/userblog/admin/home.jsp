<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div id="blogArticles">
	글 목록
	<c:forEach items="${postList }" var="post">
	<div class="blogArticle">
		<a href="${pageContext.request.contextPath }/${blog.id}/category/${post.category.id}">${post.category.title }</a> | 
		${post.title } | 
		${post.dateCreated } | 
		<a href="${pageContext.request.contextPath }/${blog.id}/admin/post/modify?displayId=${post.displayId}">수정</a> |
		<a href="${pageContext.request.contextPath }/${blog.id}/admin/post/delete?displayId=${post.displayId}">삭제</a><br /><br />
		
		<br /><br />
		
		${post.contents }
	</div>
	<div>
		<c:forEach items="${post.commentList }" var="comment">
		<div>
			${comment.writer.loginId } | ${comment.dateCreated } <br />
			${comment.contents } 
			<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId  }">
			<a href="${pageContext.request.contextPath }/${blog.id }/comment/modify?displayId=${comment.displayId}&targetId=${comment.target.id}&type=post">수정</a>
			</c:if>
			<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId || loginUserProvider.loginUser.loginId == blog.owner }">
			<a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?displayId=${comment.displayId}&targetId=${comment.target.id}&type=post">삭제</a>
			</c:if>
		</div>
		</c:forEach>
	</div>
	<div>
		<c:forEach items="${post.trackbackList }" var="trackback">
		<div>
			<a href="${trackback.url }">${trackback.title }</a> | ${trackback.dateCreated } <br />
		</div>
		</c:forEach>
	</div>
	</c:forEach>
</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />