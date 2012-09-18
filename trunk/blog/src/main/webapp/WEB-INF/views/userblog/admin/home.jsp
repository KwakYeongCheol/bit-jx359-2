<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	${blogId } 관리자 페이지
	
	<div>
		글 목록
	</div>
	<div>
		<a href="${pageContext.request.contextPath }/${loginUser.loginId }/admin/post/write">글 쓰기</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />