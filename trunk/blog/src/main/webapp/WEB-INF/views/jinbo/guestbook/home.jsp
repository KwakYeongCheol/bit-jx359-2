<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="/WEB-INF/views/jinbo/common/header.jsp" />

<!-- guestbookWrap start -->		
<div class="guestbookWrap">
	<c:if test="${loginUserProvider.loggedIn }">
	<div class="guestbookInput">
		<form:form modelAttribute="guestbook" action="${pageContext.request.contextPath }/${blog.id}/guestbook/wirteAction" method="post">
			<form:textarea path="contents" rows="3"/>
			<input type="submit" class="submit" value="방명록 작성" />
		</form:form>
	</div>
	</c:if>
	<div class="guestbookList">
		<c:forEach items="${guestbookList }" var="guestbook">
		<!-- guestbook start -->
		<div class="guestbook">
			<div class="guestbookTitle">
				${guestbook.writer.name } 님 
				| <span class="date"><spring:eval expression="guestbook.dateCreated"/></span>
				<c:if test="${loginUserProvider.loginUser.loginId == blog.owner || loginUserProvider.loginUser.loginId == guestbook.writer.loginId }">
				| <a href="${pageContext.request.contextPath }/${blog.id}/guestbook/delete?displayId=${guestbook.displayId}&redirectURI=${pageURI}" >삭제</a>
				</c:if>
			</div>
			<hr />
			<div class="guestbookContents">
				${guestbook.contents }
			</div>
			<hr />
			<div class="guestbookCommentInput">
				<c:if test="${!loginUserProvider.loggedIn }">
				댓글을 작성하기 위해서는 로그인이 필요합니다.
				</c:if>
				<c:if test="${loginUserProvider.loggedIn }">
				<form action="${pageContext.request.contextPath }/${blog.id}/comment/writeAction" method="post">
					<input type="hidden" name="targetDisplayId" value="${guestbook.displayId }" />
					<input type="hidden" name="type" value="guestbook" />
					<textarea name="contents" rows="3" style="width:100%"></textarea>
					<div style="text-align:center;padding-top:5px;">
					<input type="submit" class="postActionButton" style="margin:0 auto 0; padding:5px; border:1px solid #000;font-size:13px; width:200px; " value="댓글 작성하기"/>
					</div>
				</form>
				</c:if>
			</div>
			<div class="guestbookCommentList">
				<c:forEach items="${guestbook.commentList }" var="comment">
				<div class="guestbookComment">
					<div>
						${comment.writer.name } 님 
						| <spring:eval expression="comment.dateCreated" />
						<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId  }">
						| <a href="${pageContext.request.contextPath }/${blog.id }/comment/modify?displayId=${comment.displayId}&targetId=${comment.target.id}&type=guestbook" >수정</a>
						</c:if>
						<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId || loginUserProvider.loginUser.loginId == blog.owner }">
						| <a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?displayId=${comment.displayId}&targetId=${comment.target.id}&type=guestbook&redirectURI=${pageURI}" >삭제</a>
						</c:if>
					</div>
					<div>${comment.contents }</div>
				</div>				
				</c:forEach>
			</div>
		</div>
		<!-- guestbook end -->
		</c:forEach>
	</div>
	
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
			<li><a href="${pageURI }?pageNumber=${(page.numPageGroup-2)*page.pageGroupSize+1 }">[이전]</a></li>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<li <c:if test="${page.currentPage == i }">class="currentPage"</c:if>>
				<a href="${pageURI }?pageNumber=${i}">${i}</a>
			</li>
		</c:forEach>
	
		<c:if test="${page.numPageGroup < page.pageGroupCount}">
			<li><a href="${pageURI }?pageNumber=${page.numPageGroup*page.pageGroupSize+1}">[다음]</a></li>
		</c:if>
		</ul>
	</div>
	</c:if>
</div>
<!-- guestbookWrap end -->


<jsp:include page="/WEB-INF/views/jinbo/common/footer.jsp" />

