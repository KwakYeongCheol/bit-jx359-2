<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div id="blogArticles">
	스크랩 목록
	<br /><br />
	<div>
		<c:forEach items="${scrapList }" var="scrap">
			<a href="${pageContext.request.contextPath}/${scrap.targetBlogId }/${scrap.targetPostDisplayId }">${scrap.targetPostTitle }</a> &lt;---
			<a href="${pageContext.request.contextPath}/${scrap.post.blogId }/${scrap.post.displayId }">${scrap.post.title }</a>			
			<hr />
		</c:forEach>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />