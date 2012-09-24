<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />


<div id="blogArticles">
	<c:forEach items="${postList }" var="post">
	<div class="blogArticle">
		<a href="${pageContext.request.contextPath }/${blog.id}/category/${post.category.id}">${post.category.title }</a> | 
		${post.title } | 
		${post.dateCreated } <br />
		
		${post.contents }
	</div>
	<c:if test="${loginUserProvider.loggedIn }">
	<div>
		<form action="${pageContext.request.contextPath }/${blog.id}/comment/writeAction" method="post">
			<input type="hidden" name="targetId" value="${post.id }" />
			<input type="hidden" name="type" value="post" />
			<input type="text" name="contents" />
			<input type="submit" value="댓글작성" />
		</form>
	</div>
	</c:if>
	<div>
		<c:forEach items="${post.commentList }" var="comment">
		<div>
			${comment.writer.loginId } | ${comment.dateCreated } <br />
			${comment.contents }
		</div>
		</c:forEach>
	</div>
	</c:forEach>
</div>

<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />