<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/common/header.jsp" />

<div>
	<jsp:include page="post/postListTemplate.jsp" />
</div>

<jsp:include page="/WEB-INF/views/userblog/common/footer.jsp" />