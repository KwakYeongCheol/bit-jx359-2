<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<div>
	<form:form id="registerForm" modelAttribute="blog" action="${pageContext.request.contextPath }/blog/create" method="POST">
		<form:errors cssClass="error" path="title"></form:errors>
		<legend>블로그 생성</legend>
		<div class="group">
			<div class="groupTitle">기본정보</div>
			<div class="groupContents">
				<div class="row">
					<div class="label">블로그 제목 : </div>
					<form:input path="title" cssClass="input" placeholder="블로그 제목" autofocus="true" />
				</div>
				<div class="row">
					<div class="label">블로그 주소 : </div>
					http://jinbo.kr/<form:input path="id" cssClass="input" placeholder="블로그 URL" />
				</div>
			</div>					
		</div>		
						
		<div class="submit">
			<input type="submit" value="블로그 생성하기" />
		</div>	
	</form:form>
</div>
	
<jsp:include page="/WEB-INF/views/common/footer.jsp" />