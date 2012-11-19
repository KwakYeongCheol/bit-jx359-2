<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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