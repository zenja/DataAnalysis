<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Results of simple regression analysis</title>
	
	<!-- Google Charts API -->
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		google.load("visualization", "1", {
			packages : [ "corechart" ]
		});
		google.setOnLoadCallback(drawChart);
		function drawChart() {
			var data = google.visualization.arrayToDataTable([
				[ 'X', 'Y' ], 
				<s:iterator value="inputList" var="point" status="rowstatus">
			  		[ ${point.x}, ${point.y} ],
				</s:iterator>
			]);
		
			var options = {
				title : 'Simple Regression Analysis',
				hAxis : {
					title : 'X',
					minValue : ${minX},
					maxValue : ${maxX}
				},
				vAxis : {
					title : 'Y',
					minValue : ${minY},
					maxValue : ${maxY}
				},
				legend : 'none'
			};
		
			var chart = new google.visualization.ScatterChart(document.getElementById('chart-div'));
			chart.draw(data, options);
		}
	</script>
</head>

<body>

<h2>Simple Regression Analysis</h2>
<h3>Input Data:</h3>
<div id="chart-div" style="width: 900px; height: 500px;"></div>
<div id="indexes">
<p>
The fitting equation is: y = ${slope}x + (${intercept})
<br>
</p>
<table border=1 >
	<tr>
		<th>Index</th>
		<th>Value</th>
		<th>Description</th>
	</tr>
	<tr>
		<td>#observations</td>
		<td>${numObservation}</td>
		<td>number of observations</td>
	</tr>
	<tr>
		<td>Standard Error</td>
		<td>${stdErr}</td>
		<td>standard error of the slope estimate</td>
	</tr>
	<tr>
		<td>MSE</td>
		<td>${meanSquareError}</td>
		<td>the sum of squared errors divided by the degrees of freedom, usually abbreviated MSE</td>
	</tr>
	<tr>
		<td>R</td>
		<td>${r}</td>
		<td>Pearson's product moment correlation coefficient</td>
	</tr>
	<tr>
		<td>Regression Sum Squares</td>
		<td>${regressionSumSquares}</td>
		<td>the sum of squared deviations of the predicted y values about their mean (which equals the mean of y)</td>
	</tr>
	<tr>
		<td>R-square</td>
		<td>${rSquare}</td>
		<td>the coefficient of determination</td>
	</tr>
	<tr>
		<td>Significance</td>
		<td>${significance}</td>
		<td>the significance level of the slope (equiv) correlation</td>
	</tr>
	<tr>
		<td>Slope Confidence Interval</td>
		<td>${slopeConfidenceInterval}</td>
		<td>the half-width of a 95% confidence interval for the slope estimate</td>
	</tr>
	<tr>
		<td>Sum Cross Products</td>
		<td>${sumOfCrossProducts}</td>
		<td>the sum of cross products, xi*yi</td>
	</tr>
	<tr>
		<td>Sum Squared Errors</td>
		<td>${sumSquaredErrors}</td>
		<td>the sum of squared errors (SSE) associated with the regression model</td>
	</tr>
	<tr>
		<td>Totle Sum Squares</td>
		<td>${totalSumSquares}</td>
		<td>the sum of squared deviations of the y values about their mean</td>
	</tr>
	<tr>
		<td>X Sum Squares</td>
		<td>${xSumSquares}</td>
		<td>the sum of squared deviations of the x values about their mean</td>
	</tr>
</table>
</div>
	
</body>
</html>
