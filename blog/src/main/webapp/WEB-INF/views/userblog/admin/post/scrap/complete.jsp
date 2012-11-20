<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<meta charset="utf-8" />
	<title>Editor</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/editor.css" />
	<script src="${pageContext.request.contextPath }/resources/js/jquery-1.8.2.js"></script>
</head>
<body>
	<div class="editor">
		<div class="editor_group" style="margin-bottom:10px;">
			<div class="editor_title" style="background:#bbb; font-size:15px; height: 25px;padding: 10 0 0 10px;">
				생성된 스크랩 정보
			</div>
			<div class="editor_editor">
				<div class="editor_row">
					<div class="scrap">
					${scrap.targetPostContents }
					<br />
					</div>
				</div>
			</div>
		</div>
		<div class="editor_group" style="margin-bottom:10px;">
			<div class="editor_title" style="background:#bbb; font-size:15px; height: 25px;padding: 10 0 0 10px;">
				내 블로그에 포스팅하기
			</div>
			<div class="editor_editor">
				<div class="editor_row">
					<input id="0" class="btn post_choice" type="button" value="새 글" />
					<c:forEach items="${postList }" var="post">
					<input id="${post.displayId }" class="btn post_choice" type="button" value="${post.title }" />
					</c:forEach>
				</div>
			</div>
		</div>
		<div style="text-align:center;">
			<input class="btn cancel" type="button" value="취소하기" />
			<input class="btn next" type="button" value="다음으로" />
		</div>
	</div>	
<script type="text/javascript">
$(document).ready(function(){
	$(".post_choice").bind('click', function(){
		if($(this).hasClass("post_choice_active")){
			$(this).removeClass("post_choice_active");
			return;
		}
	
		$.each($(".post_choice"), function(){
			if($(this).hasClass("post_choice_active"))
				$(this).removeClass("post_choice_active");
		});
		
		$(this).addClass("post_choice_active");
	});
	
	$(".next").bind('click', function(){
		var choice;
		$.each($(".post_choice"), function(){
			if($(this).hasClass("post_choice_active")){
				choice = $(this);
			}
		});
		
		if(choice != null){
			var id = choice.attr("id");
			window.location.replace("${pageContext.request.contextPath}/${blog.id}/admin/post/addScrap?postDisplayId=" + id);
		}else{
			alert('스크랩을 포함할 글을 선택하여 주십시오.');
		}		
	});
	
	$(".cancel").bind('click', function(){
		window.close();
	});
	
	$(".editor_title").bind('click', function(){
		if($(this).hasClass("fold")){
			$(this).removeClass("fold");
			$(this).next().slideDown(100);
		}else{
			$(this).addClass("fold");
			$(this).next().slideUp(100);
		}
	});
});
</script>
</body>
</html>