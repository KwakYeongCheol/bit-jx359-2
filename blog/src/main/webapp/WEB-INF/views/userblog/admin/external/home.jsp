<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<div class="group">
	<div class="groupTitle theme-a">
		외부 컨텐츠 관리
	</div>
	<div class="groupContents">
		<table class="table">
			<thead>
				<tr>
					<th style="width:200px;">카테고리</th>
					<th style="width:250px;">제목</th>
					<th>외부 컨텐츠</th>
					<th style="width:60px;text-align:center;">스크랩 Version</th>
					<th style="width:60px;text-align:center;">현재 Version</th>
					<th>변경내역</th>
					<th style="text-align:center;">업데이트</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${scrapList }" var="scrap">
				<tr>
					<td>
						<a href="${pageContext.request.contextPath }/${scrap.post.blogId}/category/${scrap.post.categoryDisplayId}">${scrap.post.categoryTitle }</a>
					</td>
					<td><a href="${pageContext.request.contextPath }/${scrap.post.blogId}/${scrap.post.displayId}">${scrap.post.title }</a>
					<td>
						<a href="${pageContext.request.contextPath }/${scrap.target.post.blogId}">${scrap.target.post.blogTitle }</a> > <br />
						<a href="${pageContext.request.contextPath }/${scrap.target.post.blogId}/${scrap.target.post.displayId}">${scrap.target.post.title }</a>
					</td>
					<td style="text-align:center;">
						<a href="${pageContext.request.contextPath }/${scrap.target.post.blogId}/${scrap.target.post.displayId}/revision/${scrap.target.postRevisionId}">
							${scrap.target.postRevisionId }
						</a>
					</td>
					<td style="text-align:center;">
						<c:if test="${scrap.target.post.isPublic && scrap.target.post.canScrap }">
						<a href="${pageContext.request.contextPath }/${scrap.target.post.blogId}/${scrap.target.post.displayId}/revision/${scrap.target.post.currentRevision.displayId}">
							${scrap.target.post.currentRevision.displayId }
						</a>
						</c:if>
					</td>
					<td style="text-align:center;">
						<c:if test="${scrap.target.post.isPublic && scrap.target.post.canScrap }">
							<c:if test="${scrap.target.post.currentRevision.displayId > scrap.target.postRevisionId }">
							<a onclick="window.open('${pageContext.request.contextPath}/${scrap.target.post.blogId }/${scrap.target.post.displayId }/compare/${scrap.target.postRevisionId }/${scrap.target.post.currentRevision.displayId }'); return false;" 
							href="${pageContext.request.contextPath}/${scrap.target.post.blogId }/${scrap.target.post.displayId }/compare/${scrap.target.postRevisionId }/${scrap.target.post.currentRevision.displayId }">비교</a>
							</c:if>
						</c:if>
						<c:if test="${!scrap.target.post.isPublic}">
						비공개
						</c:if>
					</td>
					<td style="text-align:center;">
						<c:if test="${scrap.target.post.isPublic && scrap.target.post.canScrap}">
							<c:if test="${scrap.target.post.currentRevision.displayId > scrap.target.postRevisionId }">
							<form action="${pageContext.request.contextPath }/${blog.id}/admin/post/updateScrap" method="post">
								<input type="hidden" name="postDisplayId" value="${scrap.post.displayId }" />
								<input type="hidden" name="targetBlogId" value="${scrap.target.post.blogId }" />
								<input type="hidden" name="targetPostDisplayId" value="${scrap.target.post.displayId }" />
								<input type="hidden" name="targetRevisionId" value="${scrap.target.postRevisionId }" />
								<input type="submit" value="업데이트" />
							</form>
							</c:if>
						</c:if>
					</td>					
				</tr>
				</c:forEach>
			</tbody>
		</table>

		<jsp:include page="/WEB-INF/views/common/page.jsp" />
	</div>

</div>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />


























