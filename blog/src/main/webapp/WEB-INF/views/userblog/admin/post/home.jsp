<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />


<div class="group">
	<div class="groupTitle theme-a">
		글 목록
	</div>
	<div class="groupContents">
		<table class="table">
			<thead>
				<tr>
					<th>카테고리</th>
					<th>제목</th>
					<th>설정정보</th>
					<th>버전</th>
					<th>비교</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${postList }" var="post">
				<tr>
					<td>
						<a href="${pageContext.request.contextPath }/${blog.id}/category/${post.category.displayId}">
							${post.category.title }
						</a>
					</td>
					<td>
						<a href="${pageContext.request.contextPath }/${blog.id}/${post.displayId}">
							${post.title }
						</a>
						<c:set var="currentURI" value="${pageContext.request.contextPath }/${blog.id}/admin/post" />
						<span style="margin-left:20px;">
							<a href="${pageContext.request.contextPath }/${blog.id}/admin/post/modify?displayId=${post.displayId}">수정</a> | 
							<a href="${pageContext.request.contextPath }/${blog.id}/admin/post/delete?displayId=${post.displayId}&redirectURI=${currentURI}">삭제</a>
						</span>
					</td>
					<td>
						<c:if test="${post.isTemp }">
						[임시]&nbsp;&nbsp;
						</c:if>
						<c:if test="${post.isPublic }">
						[공개]&nbsp;&nbsp;
						</c:if>
						<c:if test="${!post.isPublic }">
						[비공개]&nbsp;&nbsp;
						</c:if>
						<c:if test="${post.canTrackback }">
						[트랙백]&nbsp;&nbsp;
						</c:if>
						<c:if test="${post.canScrap }">
						[스크랩]&nbsp;&nbsp;
						</c:if>
						<c:if test="${post.canComment }">
						[댓글]&nbsp;&nbsp;
						</c:if>
					</td>
					<td>
						<input type="hidden" value="${post.displayId }" />
						<select class="revision">
							<option value="current">${post.currentRevision.displayId }</option>
							<c:forEach items="${post.postRevisionList }" var="revision">
							<option value="${revision.displayId }">${revision.displayId }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="hidden" value="${post.displayId }" />
						<select class="compare">
							<option value="current">${post.currentRevision.displayId }</option>
							<c:forEach items="${post.postRevisionList }" var="revision">
							<option value="${revision.displayId }">${revision.displayId }</option>
							</c:forEach>
						</select>
					</td>
					<td><spring:eval expression="post.dateCreated" /></td>
				</tr>
				</c:forEach>
				<c:if test="${postList.isEmpty() }">
				<tr>
					<td colspan="6" style="text-align: center;">
						게시글이 존재하지 않습니다.
					</td>
				</tr>
				</c:if>
			</tbody>
		</table>
		
		<c:if test="${page.count > 0 }">
		<div class="page">
			<ul>
				<li></li>
			</ul>
		</div>
		</c:if>
		
		<c:if test="${page.count > 0}">
		<div class="page">
			<ul>
			<c:set var="pageCount" value="${page.count / page.pageSize + ( page.count % page.pageSize == 0 ? 0 : 1)}" />
			<c:set var="startPage" value="${page.pageGroupSize*(page.numPageGroup-1)+1}" />
			<c:set var="endPage" value="${startPage + page.pageGroupSize-1}" />
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>
			<c:if test="${page.numPageGroup > 1}">
				<li><a href="${pageContext.request.contextPath }/${blog.id }/admin/post?pageNumber=${(page.numPageGroup-2)*page.pageGroupSize+1 }">[이전]</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li><a href="${pageContext.request.contextPath }/${blog.id }/admin/post?pageNumber=${i}">[
					<font color="#000000" /> <c:if test="${page.currentPage == i}">
						<font color="#bbbbbb" />
					</c:if> ${i} </font>]
				</a></li>
			</c:forEach>

			<c:if test="${page.numPageGroup < page.pageGroupCount}">
				<li><a href="${pageContext.request.contextPath }/${blog.id }/admin/post?pageNumber=${page.numPageGroup*page.pageGroupSize+1}">[다음]</a></li>
			</c:if>
			</ul>
		</div>
		</c:if>
		
	</div>

</div>

<script>
	$(".revision").bind("change", function(){
		var value = $(this).val();
		var postDisplayId = $(this).prev().val();
		
		window.open("${pageContext.request.contextPath}/${blog.id}/" + postDisplayId + "/revision/" + value);
	});
	
	$(".compare").bind("change", function(){
		var value = $(this).val();
		var postDisplayId = $(this).prev().val();
		
		window.open("${pageContext.request.contextPath}/${blog.id}/" + postDisplayId + "/compare/" + value);
	});
</script>

<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />




























