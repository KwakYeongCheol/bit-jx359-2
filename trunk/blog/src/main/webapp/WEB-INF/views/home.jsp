<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div class="contentsWrap">
	<div class="newPostWrap">
		<div class="newPostTitle">
			<h3>최신 글</h3>
			<ul class="newPostMenu">
				<li><a href="${pageContext.request.contextPath }/">전체</a></li>
				<li><a href="${pageContext.request.contextPath }/recent/it">IT</a></li>
				<li><a href="${pageContext.request.contextPath }/recent/tour">여행</a></li>
			</ul>
		</div>
		<div class="newPostList">
			<c:forEach items="${postList }" var="post">
			<dl class="newPost">
				<dt>
					<a href="${pageContext.request.contextPath }/${post.blogId}/${post.displayId}">
						<c:if test="${post.title.length() <= 12 }">${post.title }</c:if>
						<c:if test="${post.title.length() > 12 }">${post.title.substring(0, 12) } ...</c:if>
					</a>
				</dt>
				<dd class="blogInfo">
					<a href="${pageContext.request.contextPath }/${post.blogId}">| 
						<c:if test="${post.blogTitle.length() <= 10 }">${post.blogTitle }</c:if>
						<c:if test="${post.blogTitle.length() > 10 }">${post.blogTitle.substring(0, 10) } ...</c:if>
						<spring:eval expression="post.dateCreated" />
					</a>
				</dd>
				<dd class="desc">
					<a href="${pageContext.request.contextPath }/${post.blogId}/${post.displayId}">
						<c:if test="${post.contents.length() <= 130 }">${post.contents }</c:if>
						<c:if test="${post.contents.length() > 130 }">${post.contents.substring(0, 130) } ...</c:if>
					</a>
				</dd>
				<dd class="tag">
					<c:forEach items="${post.postTagList }" var="tag">
					<a href="#">${tag.value }</a>
					</c:forEach>
				</dd>
			</dl>
			</c:forEach>
		</div>
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
				<li><a href="${pageURI }?pageNumber=${(page.numPageGroup-2)*page.pageGroupSize+1 }">[이전]</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li><a href="${pageURI }?pageNumber=${i}">[
					<font color="#000000" /> <c:if test="${page.currentPage == i}">
						<font color="#bbbbbb" />
					</c:if> ${i} </font>]
				</a></li>
			</c:forEach>
		
			<c:if test="${page.numPageGroup < page.pageGroupCount}">
				<li><a href="${pageURI }?pageNumber=${page.numPageGroup*page.pageGroupSize+1}">[다음]</a></li>
			</c:if>
			</ul>
		</div>
		</c:if>
	</div>
</div>
	
<jsp:include page="/WEB-INF/views/common/footer.jsp" />