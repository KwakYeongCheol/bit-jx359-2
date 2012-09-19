<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	${blogId } 관리자 페이지
	
	<div>
		<a href="${pageContext.request.contextPath }/${loginUser.loginId }/admin/post/write">글 쓰기</a>
	</div>
	<div>
		글 목록
		<div>
			<c:forEach items="${postList }" var="post">
			<div>
				${post.title } | ${post.dateCreated } <br />
				${post.contents }
			</div>
			
			</c:forEach>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />