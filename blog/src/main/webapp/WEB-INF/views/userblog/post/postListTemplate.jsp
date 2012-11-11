<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div id="blogArticles">
	<c:set var="page" value="${page }"></c:set>
	<c:forEach items="${postList }" var="post">
	<div class="blogArticle">
		<a href="${pageContext.request.contextPath }/${blog.id}/category/${post.category.displayId}">${post.category.title }</a> | 
		${post.displayId } |
		${post.title } | 
		<spring:eval expression="post.dateCreated" /> |		
		공개여부 : ${post.postMetadata.isPublic } | 
		Version : ${post.postRevisionList.get(0).displayId }
		<br /><br />
		
		${post.contents }
		<br />
		Tag : 
		<c:forEach items="${post.postTagList }" var="postTag">
		<span style="margin:5px;">${postTag.value }</span>
		</c:forEach>
	</div>
	
	<c:if test="${post.postMetadata.canScrap }">
	<div>
		<form action="${pageContext.request.contextPath }/${loginUserProvider.blog.id }/admin/post/scrap" method="post">
			<input type="hidden" name="targetBlogId" value="${blog.id }">
			<input type="hidden" name="targetPostDisplayId" value="${post.displayId }">
			<input type="hidden" name="targetPostRevisionId" value="${post.postRevisionList.get(0).displayId }">
			<input type="submit" value="스크랩">
		</form>
	</div>
	</c:if>
	<c:if test="${loginUserProvider.loggedIn }">
	<div>
		<form action="${pageContext.request.contextPath }/${blog.id}/comment/writeAction" method="post">
			<input type="hidden" name="targetDisplayId" value="${post.displayId }" />
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
	
	<c:if test="${page.count > 0}">
	<c:set var="pageCount" value="${page.count / page.pageSize + ( page.count % page.pageSize == 0 ? 0 : 1)}"/>
 	  <c:set var="startPage" value="${page.pageGroupSize*(page.numPageGroup-1)+1}"/>
 	  <c:set var="endPage" value="${startPage + page.pageGroupSize-1}"/>
   
 	  <c:if test="${endPage > pageCount}" >
 	    <c:set var="endPage" value="${pageCount}" />
 	  </c:if>
          
 	  <c:if test="${page.numPageGroup > 1}">
 	       <a href="${pageContext.request.contextPath }/${blog.id }?pageNum=${(page.numPageGroup-2)*page.pageGroupSize+1 }">[이전]</a>
 	  </c:if>

  	 <c:forEach var="i" begin="${startPage }" end="${endPage }">
     	  <a href="${pageContext.request.contextPath }/${blog.id }?pageNum=${i}">[
    	    <font color="#000000" />
   	       <c:if test="${page.currentPage == i}">
   	       <font color="#bbbbbb" />
  	      </c:if>
  	      ${i}
  	     </font>]
   	    </a>
 	  </c:forEach>

 	  <c:if test="${page.numPageGroup < page.pageGroupCount}">
 	       <a href="${pageContext.request.contextPath }/${blog.id }?pageNum=${page.numPageGroup*page.pageGroupSize+1}">[다음]</a>
 	  </c:if>
	</c:if>
	
</div>