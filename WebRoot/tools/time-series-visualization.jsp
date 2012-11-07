<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>DataGobble Analysis System</title>
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/index.js"></script>
</head>

<body>
	<section id="page-wrapper">
		
		<header>
		<h1>
		<img id="data-gobble-logo-img" alt="logo" src="<%=request.getContextPath()%>/images/data-gobble-logo.png">
		DataGobble Analysis System
		</h1>
		</header>
		
		<section>
			<h2>How to use this tool?</h2>
			<p>
				1. Upload a file listing the time series data. 
				We use a XML file to describing the data. 
				The time series are divided into collections (&lt;TSCollection&gt;). 
				Each time series has a description, frequency and start year. 
				The main data of a time series are just a list of values. 
				Note that all time series in the same collection should have the same length.
				<br>
				2. Submit the form to get the charts visualizing the time series. 
			</p>
			<h2>Case Study</h2>
			<p>
				Test data: <a href="<%=request.getContextPath()%>/download/test-data/time-series-data-example.xml" target="_blank">Download Test Data</a>
				<br><br>
				The test data above list many collections of time series. 
				Upload the file and you can see the visulization of the data. 
				Different collections are shown in different charts. 
			</p>
			<h2>Example</h2>
			<p>
				The income statistics of January and February of a company are listed in the series file 
				<a href="<%=request.getContextPath()%>/download/test-data/examples/time-series-visulization.xml" target="_blank">here</a>
			</p>
			<p>
				Using this tool, we can view the chart of the data: <br>
				<img alt="example" src="<%=request.getContextPath()%>/images/examples/time-series-visulization.png">				
			</p>
			<h2>Demo Video</h2>
			<a href="http://www.youtube.com/watch?v=hFVXxH5sql4&feature=share&list=ULhFVXxH5sql4" target="_blank">View demo</a>
		</section>
		
		<section class="form-section">
			<form action="timeSeriesVisualization" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>Time Series Visualization</legend>
					<input type="hidden" name="inputType" value="FILE">
					<label>Input File:</label>
					<input type="file" name="file">
					<br />
					<input type="submit" value="upload">
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
