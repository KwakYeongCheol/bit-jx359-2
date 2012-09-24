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
				<c:if test="${loginUserProvider.loginId == guestbook.writer }">
					<a href="${pageContext.request.contextPath }/${blog.id }/guestbook/modify?id=${guestbook.id}">수정</a>
				</c:if>
				<c:if test="${loginUserProvider.loginId == blog.owner || loginUserProvider.loginId == guestbook.writer }">
					<a href="${pageContext.request.contextPath }/${blog.id}/guestbook/delete?id=${guestbook.id}">삭제</a>
				</c:if>
			</c:if>
		</div>
		
		</c:forEach>
	</div>
</div>
<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />