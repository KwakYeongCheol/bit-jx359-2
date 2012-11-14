<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="categoryBox">
	<input type="button" class="showAllCateogory" value="모아보기" />
	<div class="clear"></div>
	<div class="category category-all">
		<div class="cateogryTitle">모든 글</div>
		<div class="post">
			<c:forEach items="${postList }" var="post">
				<div class="postTitle">${post.title }</div>
			</c:forEach>
		</div>
	</div>
	<c:forEach items="${categoryList }" var="category">
		<div class="category">
			<div class="cateogryTitle">${category.title }</div>
			<div class="post">
				<c:forEach items="${category.postList }" var="post">
					<div class="postBox">
						<input type="hidden" name="postDisplayId"
							value="${post.displayId }" class="postDisplayId" />
						<div class="postTitle">${post.title }</div>
						<div class="postContents"></div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
</section>

<section id="categoryActiveBox">
	<input type="button" class="writeBtn" value="글쓰기" />
	<div class="search_categoryBox">
		<form action="#" method="get">
			<input type="text" name="search" placeholder="search"
				class="input_box" /> <input type="submit" value="Search"
				class="btn" />
		</form>
	</div>
	<div class="clear"></div>
	<!--  -->
	<div class="extensionPost">
		<c:set var="page" value="${page }"></c:set>
		<div class="category-active category-all">
			<div class="cateogryTitle">모든 글</div>
			<div class="post">
				<c:forEach items="${postList }" var="post">
					<div class="postBox">
						<input type="hidden" name="postDisplayId" value="${post.displayId }" class="postDisplayId" />
						<div class="postTitle">${post.title }</div>
						<div class="postContents"></div>
					</div>
				</c:forEach>
			</div>
		</div>
		<c:if test="${page.count > 0}">
			<c:set var="pageCount"
				value="${page.count / page.pageSize + ( page.count % page.pageSize == 0 ? 0 : 1)}" />
			<c:set var="startPage"
				value="${page.pageGroupSize*(page.numPageGroup-1)+1}" />
			<c:set var="endPage" value="${startPage + page.pageGroupSize-1}" />
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>
			<c:if test="${page.numPageGroup > 1}">
				<a
					href="${pageContext.request.contextPath }/${blog.id }?pageNum=${(page.numPageGroup-2)*page.pageGroupSize+1 }">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<a
					href="${pageContext.request.contextPath }/${blog.id }?pageNum=${i}">[
					<font color="#000000" /> <c:if test="${page.currentPage == i}">
						<font color="#bbbbbb" />
					</c:if> ${i} </font>]
				</a>
			</c:forEach>

			<c:if test="${page.numPageGroup < page.pageGroupCount}">
				<a
					href="${pageContext.request.contextPath }/${blog.id }?pageNum=${page.numPageGroup*page.pageGroupSize+1}">[다음]</a>
			</c:if>
		</c:if>
	</div>
</section>
<script>

function categoryClick(){
	console.log("category click");
	var active = $(".category-active");
	$.each(active, function(){
		if(!$(this).hasClass("category-all")){
			$(this).unbind('click')
				.removeClass("category-active")
				.addClass("category")
				.bind('click', categoryClick)
				.appendTo("#categoryBox");
			$.each($(this).find(".postContents"), function(){
				$(this).empty();
			});
		}else{
			$(this).hide();
		}
	});
	
	if($(this).hasClass("category-all")){
		$("#categoryActiveBox .category-all").show();
	}else{
		$(this).unbind('click')
			.removeClass("category")
			.addClass("category-active")
			.appendTo("#categoryActiveBox");

		$.each($(this).children(), function(){
			if($(this).hasClass("post")){
				$(this).find(".postTitle").unbind('click');
				$(this).find(".postTitle").bind('click', addContents);
			}
		});
	}
}

function addContents(){
		console.log("addContents()");
		var displayId = $(this).prev().attr("value");
		var $title = $(this);
		var $contents = $(this).next();
		$.ajax({
			url : '${pageContext.request.contextPath }/${blog.id }/userblog/post?postDisplayId='+ displayId,
			type : 'GET',
			success : function(result) {
				if (result != null) {
						$contents.append(result);
						$contents.hide();
						$contents.toggle();
				}
			},
			error : function(result) {
				console.log('error');
			}
		}, 'json');
		$(this).unbind('click');
		$(this).bind('click',function(){
			$(this).next().toggle();
		});
}


$(document).ready(function(){
	/* 카테고리 event start  */
	
	//$(".category-active").bind('click', categoryActiveClick);
	$(".category").bind('click', categoryClick);
	
	/* 카테고리 event end  */
	$("#categoryActiveBox .category-active .postTitle").bind('click', addContents);
	
}); 
	
	
</script>
