<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />

<div id="blogArticles">
	<div style="margin: 20px">
		<form:form modelAttribute="guestbook" action="${pageContext.request.contextPath }/${blog.id}/guestbook/wirteAction" method="post">
			<p>
				<form:input path="contents"/>
				<input type="submit" value="방명록 작성">			
			</p>
		</form:form>
	</div>
	
	<div class="blogArticle">
		<c:forEach items="${guestbookList }" var="guestbook">
		<div style="margin: 5px; ">
			<a href="${pageContext.request.contextPath }/${guestbook.writer}">${guestbook.writer }</a> | 
			${guestbook.dateCreated } <br />
			${guestbook.contents } <br />
			
			<c:if test="${loginUserProvider.loggedIn }">
				<c:if test="${loginUserProvider.loginUser.loginId == guestbook.writer }">
					<a href="${pageContext.request.contextPath }/${blog.id }/guestbook/modify?id=${guestbook.id}">수정</a>
				</c:if>
				<c:if test="${loginUserProvider.loginUser.loginId == blog.owner || loginUserProvider.loginUser.loginId == guestbook.writer }">
					<a href="${pageContext.request.contextPath }/${blog.id}/guestbook/delete?id=${guestbook.id}">삭제</a>
				</c:if>
			</c:if>
		</div>
		
		<c:if test="${loginUserProvider.loggedIn }">
		<div>
			<form action="${pageContext.request.contextPath }/${blog.id}/comment/writeAction" method="post">
				<input type="hidden" name="targetId" value="${guestbook.id }" />
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
				<a href="${pageContext.request.contextPath }/${blog.id }/comment/modify?id=${comment.id}&targetId=${comment.targetId}&type=guestbook">수정</a>
				</c:if>
				<c:if test="${loginUserProvider.loginUser.loginId == comment.writer.loginId || loginUserProvider.loginUser.loginId == blog.owner }">
				<a href="${pageContext.request.contextPath }/${blog.id}/comment/delete?id=${comment.id}&targetId=${comment.targetId}&type=guestbook">삭제</a>
				</c:if>
			</div>
			</c:forEach>
		</div>
		
		
		</c:forEach>
	</div>
</div>
<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />