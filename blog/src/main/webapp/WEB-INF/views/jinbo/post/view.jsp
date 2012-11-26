<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/jinbo/common/header.jsp" />

<!-- contentsWrap category start -->
<div class="category">	
	<!-- contentsWrap postWrap start -->
	<div class="postWrap">		
		<!-- contentsWrap post start -->
		<div class="post" style="display:block;">
			<div class="postTitle">
				${post.title }
				<span class="postURL" style="float:right; color:#888; font-size:12px;">
					http://localhost:8080/${blog.id }/${post.displayId }
				</span>
			</div>
			<div class="postInfo">
				<a href="${pageContext.request.contextPath }/${blog.id}/category/${post.category.displayId}">${post.category.title }</a> | <spring:eval expression="post.dateCreated" />
				
				<c:if test="${loginUserProvider.loginUser.loginId == blog.owner }">
				<span style="float:right;">
					<c:if test="${post.isTemp }">임시</c:if><c:if test="${!post.isTemp && post.isPublic }">공개</c:if><c:if test="${!post.isTemp && !post.isPublic }">비공개</c:if>
					| <a href="${pageContext.request.contextPath }/${blog.id}/admin/post/modify?displayId=${post.displayId}">수정</a> 
					| <a href="${pageContext.request.contextPath }/${blog.id}/admin/post/delete?displayId=${post.displayId}&redirectURI=${pageURI }">삭제</a>
				</span>
				</c:if>
			</div>
			<hr />
			<div class="postContents">
				${post.contents }
			</div>
			<hr />
			<div class="postTagList">
				<c:forEach items="${post.postTagList }" var="postTag">
				
				<span class="postTag">
					<a href="${pageContext.request.contextPath }/${blog.id }/search?query=${postTag.value }">${postTag.value }</a>
				</span>
				</c:forEach>
			</div>
			
			<div class="postAction">
				<c:if test="${post.canScrap }">
				<form action="${pageContext.request.contextPath }/${loginUserProvider.blog.id }/admin/post/scrap" method="post" style="display:inline;" onsubmit="return scrapFormCheck();">
					<input type="hidden" name="targetBlogId" value="${blog.id }">
					<input type="hidden" name="targetPostDisplayId" value="${post.displayId }">
					<input type="hidden" name="targetPostRevisionId" value="${post.postRevisionList.get(0).displayId }"> 
					<input type="submit" class="postActionButton" value="스크랩" />
				</form>				
				</c:if>
				<c:if test="${post.canComment }">
				<input type="button" class="btnPostActionComment postActionButton" value="댓글" />
				</c:if>
				<c:if test="${post.canTrackback }">
				<input type="button" class="btnPostActionTrackback postActionButton" value="트랙백" />
				</c:if>
			</div>
			
			<div class="postActionContents">
				<div class="postActionComment">
					<c:if test="${!loginUserProvider.loggedIn }">
					<a href="${pageContext.request.contextPath }/login?redirectURI=${pageURI }">로그인</a>이 필요합니다.
					</c:if>
					<c:if test="${loginUserProvider.loggedIn }">
					<form action="${pageContext.request.contextPath }/${blog.id}/comment/writeAction" method="post">
						<input type="hidden" name="targetDisplayId" value="${post.displayId }" /> 
						<input type="hidden" name="type" value="post" /> 
						<textarea name="contents" rows="3" style="width:100%"></textarea>
						<div style="text-align:center;">
							<input type="submit" class="postActionButton" style="margin:0 auto 0; padding:5px; border:1px solid #000;font-size:13px; width:200px; " value="댓글 작성하기"/>
						</div>
					</form>
					</c:if>
				</div>
				<div class="postActionTrackback">
					http://localhost:8080/${blog.id }/trackback/${post.displayId }
				</div>
			</div>
			
			<div class="postCommentList">
				<c:forEach items="${post.commentList }" var="comment">
				<div class="postComment">					
					<div>
						${comment.writer.loginId }
						| <spring:eval expression="comment.dateCreated" />
						<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId  }">
						| <a href="${pageContext.request.contextPath }/${blog.id }/comment/modify?displayId=${comment.displayId}&targetId=${comment.target.id}&type=post">수정</a>
						</c:if>
						<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId || loginUserProvider.loginUser.loginId == blog.owner }">
						| <a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?displayId=${comment.displayId}&targetId=${comment.target.id}&type=post">삭제</a>
						</c:if>
					</div>
					<div>
						${comment.contents }
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
		<!-- contentsWrap post end -->
	</div>
	<!-- contentsWrap postWrap end -->		
</div>
<!-- contentsWrap category end -->
<script>
function scrapFormCheck(){
	var login = ${loginUserProvider.loggedIn };
	var blogId = "${loginUserProvider.blog.id }";
	
	if(login && blogId != ''){
		return true;
	}
	
	if(!login){
		alert("로그인이 필요한 서비스입니다.");
	}else if(blogId == ''){
		alert("스크랩이 가능한 블로그가 없습니다. 블로그를 생성해주세요!");
	}
	
	return false;
}
</script>


<jsp:include page="/WEB-INF/views/jinbo/common/footer.jsp" />

