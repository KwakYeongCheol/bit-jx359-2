<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />


<div id="blogArticles">
	<c:if test="${post != null }">
	<div class="blogArticle">
		<jsp:include page="postTemplate.jsp" />
	</div>
	</c:if>
</div>

<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />