<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<meta charset="utf-8" />
	<title>Editor</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/adapter/redactor/redactor.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/editor.css" />
	<script src="${pageContext.request.contextPath }/resources/js/jquery-1.8.2.js"></script>
</head>
<body>
	<form:form modelAttribute="post" id="writeForm" action="${pageContext.request.contextPath }/${blog.id }/admin/post/save" method="POST">
	<form:hidden path="displayId" value="${post.displayId } "/>
	<div class="editor">
		<div class="editor_menu">
			<input class="btn" id="btnWrite" type="buttun" value="글쓰기" />
			<input class="btn" type="button" value="미리보기" />
			<input class="btn" id="btnWriteTemp" type="button" value="임시저장" />
			
			<select style="width: 45%;float:right;font-size:20px;">
				<option>### 임시 저장중인 글 ### --</option>
				<c:forEach items="${tempPostList }" var="temp">
				<option>[${temp.dateCreated }] ${temp.title }</option>
				</c:forEach>
			</select>
		</div>
		<div class="editor_group" style="margin-bottom:10px;">
			<div class="editor_title" style="background:#bbb; font-size:15px; height: 25px;padding: 10 0 0 10px;">
				설정정보
			</div>
			<div class="editor_editor">
				<div class="editor_row">
					<div class="editor_row_header">
						공개여부 : 
					</div>
					<div class="editor_row_desc">
						<form:radiobutton path="postMetadata.isPublic" value="true"/> 공개 &nbsp;&nbsp;
						<form:radiobutton path="postMetadata.isPublic" value="false"/> 비공개
					</div>
				</div>
				<div class="editor_row">
					<div class="editor_row_header">
						권한 : 
					</div>
					<div class="editor_row_desc">
						<form:checkbox path="postMetadata.canScrap" />스크랩 허용 | 
						<form:checkbox path="postMetadata.canTrackback" />트랙백 허용 | 
						<form:checkbox path="postMetadata.canComment" /> 댓글 허용
					</div>
				</div>
				<div class="editor_row">
					<div class="editor_row_header">
						트랙백 보내기 :
					</div>
					<div class="editor_row_desc">
						<input type="text" name="trackbackURL" placeholder="http://" style="width: 300px;"/>
					</div>
				</div>
				<div class="editor_row">
					<div class="editor_row_header">
						태그 : 
					</div>
					<div class="editor_row_desc">
						<input type="text" id="inputTag" /> <input type="button" id="addTag" value="태그 추가" />
						<div id="tagBox" style="padding: 5 0 0 5px;">
						<c:forEach items="${post.postTagList }" var="tag">
							<span class="tag">${tag.value }</span>
							<input type="hidden" name="postTagList" value="${tag.value }" />
						</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="editor_editor">
			<div class="editor_row">
				<form:select path="category.displayId" cssStyle="width:25%;font-size:20px;">
					<form:options items="${categoryList }" itemLabel="title" itemValue="displayId" />
				</form:select>
				<form:input path="title" cssStyle="width: 70%; height:25px;" placeholder="제목을 입력하세요." />
			</div>
			
			<form:textarea path="contents" id="redactor_content" />
		</div>
	</div>
	</form:form>
	
<script src="${pageContext.request.contextPath }/resources/adapter/redactor/redactor.js"></script>
<script src="${pageContext.request.contextPath }/resources/adapter/redactor/lang/ko.js"></script>
<script type="text/javascript">
/*
	http://imperavi.com/redactor/
*/

$(document).ready(function(){
	var buttons = [
		'html', 'formatting', '|', 
		'bold', 'italic', 'deleted', '|',
		'fontcolor', 'backcolor', '|',
		'alignment', 'horizontalrule', '|',
		'unorderedlist', 'orderedlist', 'outdent', 'indent', '|',
		'image', 'table', 'link'		
	];
	
	$("#redactor_content").redactor({
		lang: 'ko',
		minHeight: 500,
		autoresize: false,
		buttons: buttons,
		imageUpload:'${pageContext.request.contextPath}/${blog.id}/admin/post/imageupload'
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
	
	function removeMe(){
		$(this).remove();
	}
	
	$(".tag").bind("click", removeMe);
	$("#addTag").bind('click', function(){
		var text = $("#inputTag").val();
		
		if(text.length > 0){
			var hidden = $("<input>").attr("type", "hidden").attr("name", "postTagList").val(text);
			$("<span>").addClass("tag")
				.html(text)
				.bind('click', removeMe)
				.append(hidden)
				.appendTo($("#tagBox"));
		
			$("#inputTag").val('');
		}
	});
	
	$("#btnWrite").bind("click", function(){
		$("<input>").attr("type", "hidden").attr("name", "postMetadata.isTemp").attr("value", "false").appendTo("#writeForm");
		$("#writeForm").submit();
	});
	
	$("#btnWriteTemp").bind("click", function(){
		$("<input>").attr("type", "hidden").attr("name", "postMetadata.isTemp").attr("value", "true").appendTo("#writeForm");
		$("#writeForm").submit();
	});
});
</script>
</body>
</html>