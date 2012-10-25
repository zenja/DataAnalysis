<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>Time Series Visualization</title>
	
	<!-- Google Charts API -->
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load("visualization", "1", {packages:["corechart"]});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		<s:iterator value="chartData" var="tscChartData" status="rs1">
			var data${rs1.index} = google.visualization.arrayToDataTable([
				<s:iterator value="tscChartData" var="row">
					${row}, 
				</s:iterator>
			]);
			var options${rs1.index} = {
				title: "${tsCollectionTitleList[rs1.index]}"
			};
			var chart = new google.visualization.LineChart(document.getElementById('chart_div_${rs1.index}'));
			chart.draw(data${rs1.index}, options${rs1.index});
		</s:iterator>
		
		var chart${rs1.index} = new google.visualization.LineChart(document.getElementById('chart_example'));
		chart${rs1.index}.draw(data, options);
	}
</script>
</head>

<body>

<h1 style="text-align: center;">Time Series Visualization</h1>

<s:iterator value="chartData" var="tscChartData" status="rs1">
	<div id="chart_div_${rs1.index}" style="width:850px;margin: 0 auto;"></div>
</s:iterator>

</body>
</html>
