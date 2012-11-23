<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />


<div id="blogArticles">
	<div style="margin: 20px">
		<form:form modelAttribute="guestbook" action="${pageContext.request.contextPath }/${blog.id}/guestbook/wirteAction" method="post">
			<p>
				<form:input path="contents" size="90%"/>
				<input type="submit" value="방명록 작성">			
			</p>
		</form:form>
	</div>
	<div class="blogArticle">
		<c:forEach items="${guestbookList }" var="guestbook">
		<div class="blogGuestbook">
		<div  style="margin: 5px; ">
			<a href="${pageContext.request.contextPath }/${guestbook.writer.loginId}">${guestbook.writer.loginId }</a> | 
			<spring:eval expression="guestbook.dateCreated"/> <br />
			${guestbook.contents } 
			
			<c:if test="${loginUserProvider.loggedIn }">
				<div class="guestBookright">
				<c:if test="${loginUserProvider.loginUser.loginId == guestbook.writer.loginId }">
					<a href="${pageContext.request.contextPath }/${blog.id }/guestbook/modify?displayId=${guestbook.displayId}" >수정 | </a>
				</c:if>
				<c:if test="${loginUserProvider.loginUser.loginId == blog.owner || loginUserProvider.loginUser.loginId == guestbook.writer.loginId }">
					<a href="${pageContext.request.contextPath }/${blog.id}/guestbook/delete?displayId=${guestbook.displayId}" >&nbsp;삭제</a>
				</c:if>
				</div>
			</c:if>
		</div>
		<c:if test="${loginUserProvider.loggedIn }">
		<div>
			<form action="${pageContext.request.contextPath }/${blog.id}/comment/writeAction" method="post">
				
				<input type="hidden" name="targetDisplayId" value="${guestbook.displayId }" />
				<input type="hidden" name="type" value="guestbook" />
				<input type="text" name="contents" size="90%" />
				<input type="submit" value="댓글작성" />
			</form>
		</div>
		</c:if>
		<div class="blogGuestBookReply">
			<c:forEach items="${guestbook.commentList }" var="comment">
			<div>
				${comment.writer.loginId } | <spring:eval expression="comment.dateCreated"/>  <br />
				${comment.contents } 
				<div class="guestBookright">
				<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId  }">
				<a href="${pageContext.request.contextPath }/${blog.id }/comment/modify?displayId=${comment.displayId}&targetId=${comment.target.id}&type=guestbook" >수정 | </a>
				</c:if>
				<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId || loginUserProvider.loginUser.loginId == blog.owner }">
				<a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?displayId=${comment.displayId}&targetId=${comment.target.id}&type=guestbook" >&nbsp;삭제</a>
				</c:if>
				</div>
			</div>
			</c:forEach>
		</div>
		</div>
		</c:forEach>
	</div>
</div>
<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />