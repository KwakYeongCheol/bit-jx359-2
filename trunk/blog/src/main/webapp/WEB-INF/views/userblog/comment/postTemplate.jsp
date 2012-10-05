<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="blogArticles">
	<div class="blogArticle">
		<a href="${pageContext.request.contextPath }/${blog.id}/category/${post.category.id}">${post.category.title }</a> | 
		${post.title } | 
		${post.dateCreated } <br /><br />
		
		<c:if test="${post.scrap != null }">
		출처: <a href="${pageContext.request.contextPath }/${post.scrap.scrappedBlog.id }">${post.scrap.scrappedBlog.title }</a>
		<div>${post.scrap.scrappedPostTitle }</div>
		<div>${post.scrap.scrappedPostContents }</div>
		</c:if>
		<br /><br />
		
		${post.contents }
	</div>
		<form action="${pageContext.request.contextPath }/blog/scrap" method="post">
			<input type="hidden" name="scrappedBlog.id" value="${blog.id }">
			<input type="hidden" name="scrappedBlog.title" value="${blog.title }">
			<input type="hidden" name="scrappedPostId" value="${post.id }">
			<input type="hidden" name="scrappedPostTitle" value="${post.title }">
			<input type="hidden" name="scrappedPostContents" value="${post.contents }">
			<input type="submit" value="스크랩">
		</form>
	<div>
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
			<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId  }">
			<a href="${pageContext.request.contextPath }/${blog.id }/comment/modify?id=${comment.id}&targetId=${comment.targetId}&type=post">수정</a>
			</c:if>
			<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId || loginUserProvider.loginUser.loginId == blog.owner }">
			<a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?id=${comment.id}&targetId=${comment.targetId}&type=post">삭제</a>
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
</div>