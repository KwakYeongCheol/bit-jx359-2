<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />

<div id="blogArticles">
	<div style="margin: 20px">
		<form:form modelAttribute="visitor" action="${pageContext.request.contextPath }/${blog.id}/visitor/wirteAction" method="post">
			<p>
				<form:input path="contents"/>
				<input type="submit" value="방명록 작성">			
			</p>
		</form:form>
	</div>
	
	<div class="blogArticle">
		<c:forEach items="${visitorList }" var="visitor">
		<div style="margin: 5px; ">
			<a href="${pageContext.request.contextPath }/${visitor.writer}">${visitor.writer }</a> | 
			${visitor.dateCreated } <br />
			${visitor.contents } <br />
			
			<c:if test="${loginUser != null }">
				<c:if test="${loginUser.loginId == visitor.writer }">
					<a href="${pageContext.request.contextPath }/${blog.id }/visitor/modify?id=${visitor.id}">수정</a>
				</c:if>
				<c:if test="${loginUser.loginId == blog.owner || loginUser.loginId == visitor.writer }">
					<a href="${pageContext.request.contextPath }/${blog.id}/visitor/delete?id=${visitor.id}">삭제</a>
				</c:if>
			</c:if>
		</div>
		
		</c:forEach>
	</div>
</div>
<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />