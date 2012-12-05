<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />


<div class="group">
	<div class="groupTitle theme-a">
		게시글 변경 내역 목록
	</div>
	<div class="groupContents">
		<table class="table">
			<thead>
				<tr>
					<th>
					<select class="categoryFilter" style="font-size:20px;">
						<option value="label">카테고리</option>
						<c:forEach items="${categoryList }" var="category">
						<option value="${category.displayId }">${category.title }</option>
						</c:forEach>
					</select>
					</th>
					<th style="min-width:300px;">제목</th>
					<th>변경 내역 목록 상세 보기</th>
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
					</td>
					<td style="text-align:center;">
						<a href="${pageContext.request.contextPath }/${blog.id}/admin/post/revision/${post.displayId}">목록보기</a>
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
		<jsp:include page="/WEB-INF/views/common/page.jsp" />
	</div>

</div>

<script>
	$(".categoryFilter").bind("change", function(){
		var value = $(this).val();
		if(value == "label")		return;
		
		window.location.replace("${pageContext.request.contextPath}/${blog.id}/admin/post/category/" + value);
	});

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




























