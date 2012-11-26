<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>${blog.title } </title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/jinbo_userblog.css">
	<script src="${pageContext.request.contextPath }/resources/js/jquery-1.8.2.js"></script>
	
	<!--  user blog theme start -->
	<style>
		.jinbo .buttonBox{			background-color: ${blog.blogTheme.backgroundColor};		}
		.jinbo .nav-title{			background-color: ${blog.blogTheme.backgroundColor};	border-radius: 4px;		}
		.jinbo .nav-title:hover{	background-color: #026987;		}
		.blogHeader{			background-color: ${blog.blogTheme.backgroundColor};		}
		footer{			background-color: ${blog.blogTheme.backgroundColor};		}
	</style>
	<!--  user blog theme end -->
</head>
<body>
	<script>
		alert('${message}');
		history.back();
	</script>
</body>
</html>
