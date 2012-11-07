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
				1. Select a text file describing the linear system to be solved.
				The file is divided into two parts which are separated by an empty line. <br>
				The file structure is like this: <br>
				<br>
				&lt;coefficients of equation 1, separated by a space&gt;<br>
				&lt;coefficients of equation 2, separated by a space&gt;<br>
				...<br>
				&lt;coefficients of equation n, separated by a space&gt;<br>
				(empty line)<br>
				&lt;the constants of all equations&gt;<br>
				<br>
				2. Set the precision of the results. <br>
				3. Submit the form the see the results.
			</p>
			<h2>Case Study</h2>
			<p>
				Test data: <a href="<%=request.getContextPath()%>/download/test-data/testdata-solve-linear-system.txt" target="_blank">Download Test Data</a>
				<br><br>
				For example, we want to solve a linear system like this: <br>
				<br>
				2x0 + 3x1 - 2x2 = 1 <br>
				-1x0 + 7x1 + 6x2 = -2 <br>
				4x0 - 3x1 - 5x2 = 1 <br>
				<br>
				Then the test data is: <br>
				<br>
				2, 3, -2<br>
				-1, 7, 6<br>
				4, -3, -5<br>
				<br>
				1, -2, 1<br>
				<br>
				Upload the file and set the precision to 1 and we can get the results: <br>
				<br>
				x0 = -0.4<br>
				x1 = 0.2<br>
				x2 = -0.6<br>
				<br>
			</p>
			<h2>Example</h2>
			<p>
				For example, you have to solve a problem like this: <br>
				<br>
				You are offered two different sales jobs. Job A offers an annual salary of $30,000
				plus a year-end bonus of 1% of your total sales. Job B offers an annual salary of
				$24,000 plus a year-end bonus of 2% of your total sales. <br>
				<br>
				a. How much would you have to sell to earn the same amount in each job? <br>
				b. You believe you can sell between $500,000 and $800,000 of merchandise per
				year. Which job should you choose? <br>
				<br>
				First find a linear system that models the situation: <br>
				<br>
				y = 30,000 + 0.01x<br>
				y = 24,000 + 0.02x<br>
				<br>
				First we convert the description to normal form: <br>
				<br>
				-0.01x0 + x1 = 30000 <br>
				-0.02x0 + x1 = 24000 <br>
				<br>
				The corresponding input file is: <br>
				<br>
				-0.01,1 <br>
				-0.02,1 <br>
				 <br>
				30000,24000 <br>
				<br>
				Feed the 
				<a href="<%=request.getContextPath()%>/download/test-data/examples/solve-linear-system.txt" target="_blank">file</a> 
				to the system and we can get the results: <br>
				<br>
				x0 = 600000.00 <br>
				x1 = 36000.00 <br>
				<br>
			</p>
			<h2>Demo Video</h2>
			<a href="http://youtu.be/WB15JB1bxz8" target="_blank">View demo</a>
		</section>
		
		<section class="form-section">
			<form action="solveLinearSystem" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>Solving Linear System</legend>
					<input type="hidden" name="inputType" value="FILE">
					<label>Input File:</label>
					<input type="file" name="file">
					<br>
					<label>Precisition:</label>
					<input type="text" name="precision" value="2">
					<br>
					<input type="submit" value="upload">
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
