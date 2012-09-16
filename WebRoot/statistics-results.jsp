<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>Analysis Result</title>
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/highcharts/highcharts.js" type="text/javascript"></script>

			<script type="text/javascript">
$(function () {
    var chart;
    $(document).ready(function() {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'chart',
                type: 'line',
                marginRight: 130,
                marginBottom: 25
            },
            title: {
                text: 'Input Data',
                x: -20 //center
            },
            xAxis: {
                //categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                //    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: 'Number'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return this.y;
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [{
                name: 'data',
                data: 
                	[<s:iterator value="inputList" var="number" status="rowstatus">
				  		${number}, 
					</s:iterator>]
            }]
        });
    });
    
});
		</script>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<h2>Input Data:</h2>
	<p>
	<s:iterator value="inputList" var="number" status="rowstatus">
  		${number}, 
	</s:iterator>
	</p>
	
	<div id="chart" style="min-width: 400px; height: 400px; margin: 0 auto">
	</div>
	
	<h2>Statitical Results:</h2>
	<p>Min: ${min}</p>
	<p>Max: ${max}</p>
	<p>Mean value: ${mean}</p>
	<p>Median value: ${median}</p>
	<p>Standard Deviation: ${standardDeviation}</p>
	<p>Skewness: </p>
	<p>Kurtosis: </p>
</body>
</html>
