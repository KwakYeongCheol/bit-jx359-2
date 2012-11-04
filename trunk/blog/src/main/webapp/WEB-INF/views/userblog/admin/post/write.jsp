<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/ckeditor/ckeditor.js"></script>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />
</head>
<body>
<div id="blogArticles">	
	<form:form modelAttribute="post" action="${pageContext.request.contextPath }/${blog.id }/admin/post/writeAction" method="POST">
		<p>
			<form:label path="category.displayId">카테고리</form:label>
			<form:select path="category.displayId">
				<c:forEach items="${categoryList }" var="category">
					<form:option value="${category.displayId }">${category.title }</form:option>
				</c:forEach>
			</form:select>
		</p>
		<p>
			<form:errors cssClass="errors" path="title"></form:errors>
			<form:label cssClass="label" path="title">Title</form:label>
			<form:input cssClass="input" path="title"/>
		</p>
		<p>
			<form:errors cssClass="errors" path="contents"></form:errors>
			<form:label cssClass="label" path="contents">Contents</form:label>
			<textarea id="editor" name="contents">
			<c:if test="${scrap != null }">
				<p>
					@@${scrap.scrappedBlog.id }/${scrap.scrappedPostId }/${scrap.scrappedPostRevisionId }##
				</p>
			</c:if>
			</textarea>
		</p>
		<div>
			<h3>설정 정보</h3>
			<div>
				<form:radiobutton path="postMetadata.isPublic" value="true" />공개 
				<form:radiobutton path="postMetadata.isPublic" value="false" />비공개
			</div>
			<div>
				<form:checkbox path="postMetadata.canComment" />댓글허용 <br />
				<form:checkbox path="postMetadata.canTrackback" />트랙백 허용<br />
				<form:checkbox path="postMetadata.canScrap" />스크랩 허용<br />
			</div>
			<div>
			</div>
			<div>
			</div>
			<div>
				<label>트랙백 보내기</label>
				<input type="text" name="trackbackURL" placeholder="http://"/>
			</div>
			<div>
				Tag : <input type="text" id="inputTag" />
				<input type="button" id="btnInputTag" value="추가" />
				<div id="tagBox">
				<c:forEach items="${post.postTagList }" var="tag">
				<span style="padding:5px;">
					${tag.value }
					<input type="hidden" name="tagList" value="${tag.value }" />
				</span>
				</c:forEach>
				</div>
			</div>
		</div>
		<p>
			<input type="submit" value="글쓰기" />
		</p>
		

		
	</form:form>
</div>
<script type="text/javascript">
	CKEDITOR.replace('editor',{
		enterMode:'2',
	    shiftEnterMode:'3',
		width:'100%',
	    height:'150',
	    toolbar:[
	    ['Bold','Italic','-','NumberedList','BulletedList','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','Styles','Format','Font','FontSize']],
	    skin:'office2003'
	});
	
	$("#btnInputTag").bind('click', function(){
		var tag = $("#inputTag").val();
		if(tag.length == 0)	return;
		
		$span = $("<span>").attr("style", "padding:5px;").html(tag).appendTo($("#tagBox"));
		$("<input>").attr("type", "hidden").attr("name", "postTagList").attr("value", tag).appendTo($span);
		
		$("#inputTag").val("");
	});
</script>
</body>
</html>
<jsp:include page="/WEB-INF/views/userblog/admin/common/footer.jsp" />