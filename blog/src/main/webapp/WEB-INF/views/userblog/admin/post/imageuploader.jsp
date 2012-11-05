<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<meta charset="utf-8" />
	<title>이미지 업로더</title>
	<script src="${pageContext.request.contextPath }/resources/js/jquery-1.8.2.js"></script>
</head>
<body>
<div id="blogArticles">
	<input type="file" name="image" id="file"/>	
	
	<input type="button" id="click" value="이미지 업로드" />
	
	<div id="result">
		
	</div>
</div>

<script>
	function append(url){
		$("<img>").attr("src", url).appendTo("#result");
		$("<input>").attr("type", "hidden").attr("name", "imageUrl").attr("value", url).appendTo("#result");
	}

	$("#file").bind('change', function(){
		var file = $(this)[0].files[0];
		
		var formData = new FormData();
		formData.append('image', file);
		
		$.ajax({
			url: '${pageContext.request.contextPath }/${blog.id }/admin/post/imageupload',
			type: 'POST',
			xhr: function(){
				myXhr = $.ajaxSettings.xhr();
				if(myXhr.upload){
					console.log('myXhr.upload')
				}
				return myXhr;
			},
			success: function(result){
				if(result.success == true){
					append(result.url);
				}
			},
			error: function(result){
				console.log('error');
			},
			data: formData,
			cache: false,
			contentType: false,
			processData: false
		}, 'json');
	});
	
	$("#click").bind('click', function(){
		var array = new Array();
		
		$.each($("[name='imageUrl']"), function(){
			array.push($(this).val());
		});
				
		window.opener.imageUploaderCallback(array);
	});
</script>

</body>
</html>