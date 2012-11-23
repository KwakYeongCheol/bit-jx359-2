<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/settings/common/header.jsp"></jsp:include>

<div class="group">
	<div class="groupTitle">
		개인정보
	</div>
	<div class="groupContents">
		<div class="row">
			<div class="key">
				이름 :
			</div>
			<div class="value">
				${loginUserProvider.loginUser.name }
			</div>
		</div>
		<div class="row">
			<div class="key">
				생년월일 : 
			</div>
			<div class="value">
				${loginUserProvider.loginUser.birthday }
			</div>
		</div>
		<div class="row">
			<div class="key">
				성별 :
			</div>
			<div class="value">
				${loginUserProvider.loginUser.sex }
			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/settings/common/footer.jsp"></jsp:include>
