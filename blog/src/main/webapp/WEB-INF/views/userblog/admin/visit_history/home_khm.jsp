<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/userblog/admin/common/header.jsp" />


<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
    var day = ${visit };
    var d = ${day };
    
    var today = new Date();
    var Month = today.getMonth()+1+"";
    
    google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  
        var data = google.visualization.arrayToDataTable([
          ['Month', '방문량'],
          ['1일',  day[0]],
          ['2일',  day[1]],
          ['3일',  day[2]],
          ['4일',  day[3]],
          ['5일',  day[4]],
          ['6일',  day[5]],
          ['7일',  day[6]],
          ['8일',  day[7]],
          ['9일',  day[8]],
          ['10일',  day[9]],
          ['11일',  day[10]],
          ['12일',  day[11]],
          ['13일',  day[12]],
          ['14일',  day[13]],
          ['15일',  day[14]],
          ['16일',  day[15]],
          ['17일',  day[16]],
          ['18일',  day[17]],
          ['19일',  day[18]],
          ['20일',  day[19]],
          ['21일',  day[20]],
          ['22일',  day[21]],
          ['23일',  day[22]],
          ['24일',  day[23]],
          ['25일',  day[24]],
          ['26일',  day[25]],
          ['27일',  day[26]],
          ['28일',  day[27]],
          ['29일',  day[28]],
          ['30일',  day[29]],
          ['31일',  day[30]]
        ]);
        
        var options = {
          title: Month + "월 방문자 통계",
          hAxis: {title: 'Month',  titleTextStyle: {color: 'red'}}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>

<div id="chart_div" style="width: 610px; height: 450px;"></div>


<%-- <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/visit_history.css">
<div class="vGraph">
<fieldset>
	<legend><h1>방문자 통계</h1></legend>
	<ul>
		<li><span class="gTerm">일간 방문량</span><span class="gBar" style="height: 1px"><span>1</span></span></li>
		<li><span class="gTerm">주간 방문량</span><span class="gBar" style="height: 3px"><span>3</span></span></li>
		<li><span class="gTerm">월간 방문량</span><span class="gBar" style="height: 10px"><span>10</span></span></li>
		<li><span class="gTerm">TOTAL</span><span class="gBar" style="height: 14px"><span>14</span></span></li>
	</ul>
	</fieldset>
</div>
<div>
<c:forEach items="${map }" var="map">
	${map }
</c:forEach>

</div> --%>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />