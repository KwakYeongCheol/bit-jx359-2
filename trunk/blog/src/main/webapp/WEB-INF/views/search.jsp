<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="common/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/search.css">

<div class="contentsWrap">
	<div class="searchBar">
		<form action="${pageContext.request.contextPath }/search" method="get">
		<input type="text" class="inputSearch" name="query" value="${query }" autofocus="autofocus" /><input class="submitSearch" type="submit" value="검색" />
	</form>
	</div>
	<hr />
	<div class="searchWrap">
		<div class="searchList">
			<c:if test="${search.postList.isEmpty() }">
			<dl class="search">
				<span style="font-size:15px; font-weight:bold;">${query }</span>에 대한 검색 결과가 없습니다.
			</dl>
			</c:if>
			<c:forEach items="${search.postList }" var="post">
			<dl class="search">
				<dt>
					<a href="${pageContext.request.contextPath }/${post.blogId}/${post.displayId}">
						<c:if test="${post.title.length() <= 30 }">${post.title }</c:if>
						<c:if test="${post.title.length() > 30 }">${post.title.substring(0, 30) } ...</c:if>
					</a>
				</dt>
				<dd class="searchInfo">
					<a href="${pageContext.request.contextPath }/${post.blogId}">| 
						<c:if test="${post.blogTitle.length() <= 12 }">${post.blogTitle }</c:if>
						<c:if test="${post.blogTitle.length() > 12 }">${post.blogTitle.substring(0, 12) } ...</c:if>
						<spring:eval expression="post.dateCreated" />
					</a>
				</dd>
				<dd class="desc">
					<a href="${pageContext.request.contextPath }/${post.blogId}/${post.displayId}">
						<c:if test="${post.contentsWithoutTag.length() <= 150 }">${post.contentsWithoutTag }</c:if>
						<c:if test="${post.contentsWithoutTag.length() > 150 }">${post.contentsWithoutTag.substring(0, 150) } ...</c:if>
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
				<li><a href="${pageURI }?query=${query }&pageNumber=${(page.numPageGroup-2)*page.pageGroupSize+1 }">[이전]</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li><a href="${pageURI }?query=${query }&pageNumber=${i}">[
					<font color="#000000" /> <c:if test="${page.currentPage == i}">
						<font color="#bbbbbb" />
					</c:if> ${i} </font>]
				</a></li>
			</c:forEach>
		
			<c:if test="${page.numPageGroup < page.pageGroupCount}">
				<li><a href="${pageURI }?query=${query }&pageNumber=${page.numPageGroup*page.pageGroupSize+1}">[다음]</a></li>
			</c:if>
			</ul>
		</div>
		</c:if>
	</div>
</div>

<jsp:include page="common/footer.jsp" />