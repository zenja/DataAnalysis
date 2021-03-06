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
				1. Upload a text file with a series double values, one value per line; <br>
				2. Submit the form to see the results.
			</p>
			<h2>Case Study</h2>
			<p>
				Test data: <a href="<%=request.getContextPath()%>/download/test-data/testdata-statistics.txt" target="_blank">Download Test Data</a>
				<br>
				In the test data, we have several numbers. After you submit the data, you can see a list 
				and a chart for the input data, and the statistics, such as the min/max value, mean value, 
				standard deviation, etc.
			</p>
			<h2>Example</h2>
			<p>
				The median income of families of USA from 1990-2009 is (yearly income, dollars): <br>
				35,353 40,611 50,732 56,194 58,407 61,355 61,521 60,088 
				(<a href="http://www.census.gov/compendia/statab/2012/tables/12s0701.pdf" target="_blank">data source</a>)<br>
				Suppose we want to find the average income during 1990-2009, 
				so we make the input file as: <br>
				<br>
				35353<br>40611<br>50732<br>56194<br>58407<br>61355<br>61521<br>60088<br>
				<br>
				After uploading the file we can get the average income during period 1990-2009 is 53032.625 dollars/year. 
			</p>
			<h2>Demo Video</h2>
			<a href="http://www.youtube.com/watch?v=JfhhZrBnD_4&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
		</section>
		
		<section class="form-section">
			<form action="statistics.action" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>Statistics</legend>
					<label>Input File:</label>
					<input type="file" name="file" />
					<br />
					<input type="submit" value="upload" />
					<p>
						<a href="<%=request.getContextPath()%>/download/test-data/testdata-statistics.txt" target="_blank">Download Test Data</a>
						<br>
					</p>
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
