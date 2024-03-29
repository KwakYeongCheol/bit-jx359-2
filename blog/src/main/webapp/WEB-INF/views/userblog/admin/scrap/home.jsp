<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div class="group">
	<div class="groupTitle">
		글 목록
	</div>
	<div class="groupContents">
		<table class="table">
			<thead>
				<tr>
					<th>
						내 글
					</th>
					<th>
						내 글을 스크랩 한 글
					</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${scrapList }" var="scrap">
				<tr>
					<td>
						<a href="${pageContext.request.contextPath}/${scrap.targetBlogId }/${scrap.targetPostDisplayId }">${scrap.targetPostTitle }</a>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/${scrap.post.blogId}">${scrap.post.blogTitle }</a> &gt;
						<a href="${pageContext.request.contextPath}/${scrap.post.blogId }/${scrap.post.displayId }">${scrap.post.title }</a>
					</td>			
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />