<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>test</title>
	
	<!-- Google Charts API -->
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		google.load("visualization", "1", {packages:["corechart"]});
		google.setOnLoadCallback(drawChart);
		function drawChart() {
			var data = google.visualization.arrayToDataTable([
				['Year', 'Sales', 'Expenses', 'Extra'],
				[0,  1000,      400, 9000],
				[1,  1170,      460, 8000],
				[2,  660,       1120,7500],
				[3,  1030,      540, null]
			]);

			var options = {
				title: 'Company Performance'
			};

			var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
			chart.draw(data, options);
		}
	</script>
</head>

<body>

<div id="chart_div"></div>

</body>
</html>
