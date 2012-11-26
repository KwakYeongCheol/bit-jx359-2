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
		<!-- guestbook start -->
		<div class="guestbook">
			<div class="guestbookTitle">
				<c:if test="${findGuestbook.writer.blog == null}">
				${findGuestbook.writer.name } 님 
				</c:if>
				<c:if test="${findGuestbook.writer.blog != null}">
				<a href="${pageContext.request.contextPath }/${findGuestbook.writer.blog.id}">${findGuestbook.writer.name }</a> 님
				</c:if>
				 
				| <span class="date"><spring:eval expression="findGuestbook.dateCreated"/></span>
				<c:if test="${loginUserProvider.loginUser.loginId == blog.owner || loginUserProvider.loginUser.loginId == findGuestbook.writer.loginId }">
				| <a href="${pageContext.request.contextPath }/${blog.id}/guestbook/delete?displayId=${findGuestbook.displayId}&redirectURI=${pageURI}" >삭제</a>
				</c:if>
			</div>
			<hr />
			<div class="guestbookContents">
				${findGuestbook.contents }
			</div>
			<hr />
			<div class="guestbookCommentInput">
				<c:if test="${!loginUserProvider.loggedIn }">
				댓글을 작성하기 위해서는 로그인이 필요합니다.
				</c:if>
				<c:if test="${loginUserProvider.loggedIn }">
				<form action="${pageContext.request.contextPath }/${blog.id}/comment/writeAction" method="post">
					<input type="hidden" name="targetDisplayId" value="${findGuestbook.displayId }" />
					<input type="hidden" name="type" value="guestbook" />
					<textarea name="contents" rows="1" style="width:100%"></textarea>
					<div style="text-align:center;padding-top:5px;">
					<input type="submit" class="postActionButton" style="margin:0 auto 0; padding:5px; border:1px solid #000;font-size:13px; width:200px; " value="댓글 작성하기"/>
					</div>
				</form>
				</c:if>
			</div>
			<div class="guestbookCommentList">
				<c:forEach items="${findGuestbook.commentList }" var="comment">
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
	</div>
</div>
<!-- guestbookWrap end -->


<jsp:include page="/WEB-INF/views/jinbo/common/footer.jsp" />

