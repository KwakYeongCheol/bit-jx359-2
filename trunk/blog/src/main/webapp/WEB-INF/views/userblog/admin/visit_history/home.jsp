<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />

<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/visit_history.css">
<div class="vGraph">
<fieldset>
	<legend><h1>방문자 통계</h1></legend>
	
	<ul>
		<li><span class="gTerm">SUN</span><span class="gBar" style="height: 1px"><span>1</span></span></li>
		<li><span class="gTerm">MON</span><span class="gBar" style="height: 2px"><span>2</span></span></li>
		<li><span class="gTerm">TUE</span><span class="gBar" style="height: 1px"><span>1</span></span></li>
		<li><span class="gTerm">WED</span><span class="gBar" style="height: 3px"><span>3</span></span></li>
		<li><span class="gTerm">THU</span><span class="gBar" style="height: 4px"><span>4</span></span></li>
		<li><span class="gTerm">FRI</span><span class="gBar" style="height: 1px"><span>1</span></span></li>
		<li><span class="gTerm">SAT</span><span class="gBar" style="height: 2px"><span>2</span></span></li>
		<li><span class="gTerm">TOTAL</span><span class="gBar" style="height: 14px"><span>14</span></span></li>
	</ul>
	</fieldset>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />