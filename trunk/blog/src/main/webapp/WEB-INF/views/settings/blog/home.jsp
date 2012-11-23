<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/settings/common/header.jsp"></jsp:include>

<div class="group">
	<div class="groupTitle">
		블로그 목록
	</div>
	<div class="groupContents">
		<c:if test="${loginUserProvider.blogList.isEmpty() }">
		<div style="text-align: center;">
			블로그가 존재하지 않습니다.
		</div>
		</c:if>
		<c:forEach items="${loginUserProvider.blogList }" var="blog">
		<div class="row">
			<div class="key">
				<a href="${pageContext.request.contextPath }/${blog.id }">${blog.title }</a> :
			</div>
			<div class="value">
				<a href="${pageContext.request.contextPath }/${blog.id }/admin">관리자 페이지로</a> |
				<a href="${pageContext.request.contextPath }/settings/blog/modify?id=${blog.id}">정보 수정</a>
			</div>
		</div>
		</c:forEach>
	</div>
</div>
		
<jsp:include page="/WEB-INF/views/settings/common/footer.jsp"></jsp:include>