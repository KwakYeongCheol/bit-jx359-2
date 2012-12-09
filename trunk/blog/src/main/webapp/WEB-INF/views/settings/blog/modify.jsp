<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/settings/common/header.jsp"></jsp:include>

<div class="group">
	<div class="groupTitle">
		블로그 정보
	</div>

	<div class="groupContents">
	<form:form modelAttribute="blog" action="${pageContext.request.contextPath }/settings/blog/modify" method="POST">
		<div class="row">
			<form:errors cssClass="error" path="title"></form:errors>
			<div class="key">Blog Title</div>
			<div class="value">
				<form:input path="title" cssClass="input" />
			</div>
		</div>
		
		<div class="row">
			<div class="key">블로그 주소</div>
			<div class="value">
				http://sample.kr/${blog.id }
			</div>
		</div>
		
		<div class="row">
			<div class="key">
				블로그 테마 :
			</div>
			<div class="value">
				<form:radiobuttons cssStyle="width:20px;margin:0 0px 0 5px;" path="blogTheme" items="${blogThemeList }"/>
			</div>
		</div>
		
		<div class="row">
			<div class="key">
				블로그 위젯 :
			</div>
			<div class="value">
				<form:checkbox cssStyle="width:20px;margin:0 0px 0 5px;" path="blogWidget.visitCount"/> 방문자 통계 
				<form:checkbox cssStyle="width:20px;margin:0 0px 0 5px;" path="blogWidget.contents"/>컨텐츠 통계 
				<form:checkbox cssStyle="width:20px;margin:0 0px 0 5px;" path="blogWidget.tag"/>태그 목록 
			</div>
		</div>
		
		<div class="row">
			<div class="submit">
				<input type="submit" value="블로그 수정" />
			</div>
		</div>
	</form:form>
	</div>
</div>
	
<jsp:include page="/WEB-INF/views/settings/common/footer.jsp"></jsp:include>