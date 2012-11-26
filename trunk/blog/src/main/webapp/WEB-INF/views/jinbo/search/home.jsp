<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/jinbo/common/header.jsp" />

<!-- contentsWrap category start -->
<div class="category">		
	<div class="title">
		[ ${query } ]에 대한 검색 결과 : ${page.count } 건
		
		<!--contentsWrap menu start -->
		<div class="menu">
			<input id="postFoldAll" type="button" value="모두접기" />
			<input id="postOpenAll" type="button" value="모두펼치기" />
		</div>
		<!--contentsWrap menu end -->
	</div>
	
	<c:forEach items="${pageCategory.postList }" var="post">
	<!-- contentsWrap postWrap start -->
	<div class="postWrap">
		<div class="postSimple">
			<span style="font-weight:bold;">${post.title }</span> 
			<span style="float:right;">${post.category.title } | <spring:eval expression="post.dateCreated" /></span>
		</div>
		
		<!-- contentsWrap post start -->
		<div class="post">
			<div class="postTitle">
				${post.title }
				<span class="postFold" style="margin-left:10px;font-size:13px; color:#888;">접기</span>
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
				<form action="${pageContext.request.contextPath }/${loginUserProvider.blog.id }/admin/post/scrap" method="post" style="display:inline;">
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
	</c:forEach>
	
	<c:if test="${page.count > 0}">
	<div class="page">
		<ul>
		<c:set var="pageCount" value="${page.count / page.pageSize + ( page.count % page.pageSize == 0 ? 0 : 1)}" />
		<c:set var="startPage" value="${page.pageGroupSize*(page.numPageGroup-1)+1}" />
		<c:set var="endPage" value="${startPage + page.pageGroupSize-1}" />
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
		<c:if test="${page.numPageGroup > 1}">
			<li><a href="${pageURI }?query=${query }&pageNumber=${(page.numPageGroup-2)*page.pageGroupSize+1 }">[이전]</a></li>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<li <c:if test="${page.currentPage == i }">class="currentPage"</c:if>>
				<a href="${pageURI }?query=${query }&pageNumber=${i}">${i}</a>
			</li>
		</c:forEach>
	
		<c:if test="${page.numPageGroup < page.pageGroupCount}">
			<li><a href="${pageURI }?query=${query }&pageNumber=${page.numPageGroup*page.pageGroupSize+1}">[다음]</a></li>
		</c:if>
		</ul>
	</div>
	</c:if>
</div>
<!-- contentsWrap category end -->

<jsp:include page="/WEB-INF/views/jinbo/common/footer.jsp" />

