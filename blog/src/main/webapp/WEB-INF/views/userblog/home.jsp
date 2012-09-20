<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />

<div>
	<c:forEach items="${postList }" var="post">
	<div style="margin: 5px;">
		${post.title } | ${post.dateCreated } <br />
		${post.contents }
	</div>
	
	</c:forEach>
</div>

<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />