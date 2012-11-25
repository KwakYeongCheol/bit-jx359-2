<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="userblogSelect" class="">
	<ul class="page">
		<li class="showAllCategory">모아보기</li><hr>
		<li class="pageHome"><a title="Back to top" href="#top">위로</a></li><hr>
		<c:if test="${loginUserProvider.loggedIn }">
			<c:if test="${loginUserProvider.loginUser.loginId == blog.owner }">
				<li class="writeBtn">글쓰기</li>
			</c:if>
		</c:if>	
	</ul>
</div>

<section id="categoryBox">
	<div class="category category-all">
		<div class="cateogryTitle">${allCategory.title }</div>
		<div class="post">
			<c:forEach items="${allCategory.postList }" var="post">
				<div class="postTitle">${post.title }</div>
			</c:forEach>
		</div>
	</div>
	<c:forEach items="${categoryList }" var="category">
	<c:set var="page" value="${page }"></c:set>
		<div class="category">
			<div class="cateogryTitle">${category.title }</div>
			<div class="post">
				<c:forEach items="${category.postList }" var="post">
					<div class="postBox">
						<input type="hidden" name="postDisplayId"
							value="${post.displayId }" class="postDisplayId" />
						<div class="postTitle">${post.title }  
						<div class="postDate"><spring:eval expression="post.dateCreated" /></div></div>
						<div class="postContents"></div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
</section>

<section id="categoryActiveBox">
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
			<div class="cateogryTitle">${pageCategory.title }</div>
			<div class="post">
				<c:forEach items="${pageCategory.postList }" var="post">
					<div class="postBox">
						<input type="hidden" name="postDisplayId" value="${post.displayId }" class="postDisplayId" />
						<div class="postTitle">${post.title }
						<div class="postDate"><spring:eval expression="post.dateCreated" /></div></div>
						<div class="postContents"></div>
					</div>
				</c:forEach>
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
	</div>
</section>
<script>
function categoryClick(){
	$("#categoryBox").css("width", "22%");
	$("#categoryBox .post").css("display", "none");
	$("#categoryActiveBox").show();
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
		} else {
			$(this).hide();
			$("#categoryBox .post").css("display", "none");
		}
	});

	if($(this).hasClass("category-all")){
		$("#categoryActiveBox .category-all").show();
		$("#categoryBox .category .postDate").hide();
		$("#categoryBox .post").css("display", "none");
	}else{
		$(this).unbind('click')
			.removeClass("category")
			.addClass("category-active")
			.appendTo("#categoryActiveBox")
			$("#categoryActiveBox .post").css("display", "inherit");
		$.each($(this).children(), function(){
			if($(this).hasClass("post")){
				$(this).find(".postDate").show();
				$(this).find(".postTitle").unbind('click');
				$(this).find(".postTitle").bind('click', addContents);
				
			}
			$("#categoryBox .category .postDate").hide();
			$("#categoryBox .post").css("display", "none");
		});
	}
};

	function addContents() {
		var displayId = $(this).prev().attr("value");
		var $title = $(this);
		var $postContents = $(this).next();
		$.ajax({
			url : '${pageContext.request.contextPath }/${blog.id }/userblog/post?postDisplayId='+ displayId,
			type : 'GET',
			success : function(result) {
				if (result != null) {
						$postContents.append(result);
						$postContents.show();
						$postContents.slideDown(); 
				}
			},
			error : function(result) {
				console.log('error');
			}
		}, 'json');

		
		$(this).unbind('click');
		$(this).bind('click', function() {
			$(this).next().toggle();
		});
	}

function openEditor(){
	var postBackground = $("<div>").addClass("postBackground").appendTo($("body"));
	var postEditor = $("<div>").addClass("postEditor").appendTo($("body"));
	var editorBox = $("<div>").addClass("editorBox").appendTo(postEditor);
	$.ajax({
		url : '${pageContext.request.contextPath }/${blog.id }/admin/post/write',
		type : 'GET',
		success : function(result){
			if(result != null){
				editorBox.append(result);
			}
		},
		error : function(result){
			console.log('error');
		}
	}, 'json');
	
	$(".postBackground").bind('click', function(){
		$(this).remove();
		$(".postEditor").remove();
	});
}

$(document).ready(function(){
	/* 카테고리 event start  */
	if($(".cateogry-active"))
	$(".category").bind('click', categoryClick);
	
	/* 카테고리 event end  */
	$("#categoryActiveBox .category-active .postTitle").bind('click', addContents);
	$("#categoryBox .category .postDate").hide();
	$(".showAllCategory").click(function(){
		$("#categoryActiveBox").hide(); 
		$("#categoryBox").css("width", "66%");
		$("#categoryBox .post").css("display", "inherit");
	});
	
	$(".writeBtn").bind('click', function(){
		openEditor();
	});
	
}); 

</script>
