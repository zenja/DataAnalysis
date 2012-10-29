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
				1. Set the observed values, separated by commas. <br>
				2. Set the expected values, separated by commas. <br>
				3. Submit the form to get the results.
			</p>
			<h2>Case Study</h2>
			<p>
				For example, we want to conduct a 
				paired test evaluating the null hypothesis 
				that the mean difference between corresponding (paired) elements 
				of sample1 and sample2 is zero. <br>
				<br>
				Sample 1: 93.0, 103.0, 95.0, 101.0, 91.0, 105.0 <br>
				Sample 2: 99.0, 92.0, 102.0, 100.0, 102.0, 89.0 <br>
				<br>
				We set the corresponding values and submit the form, 
				then we get p-value: 0.8855449906064216 and 
				t-statistic: 0.15144563320384566
			</p>
			<h2>Demo Video</h2>
		</section>

		<section class="form-section">
			<form action="twoSampleTTest" method="post">
				<fieldset>
					<legend>Two Sample t-test</legend>
					<input type="hidden" name="inputType" value="PLAIN_TEXT">
					
					<label>Sample 1</label>
					<textarea rows="5" cols="80" name="tTestSampleOneStr">93.0, 103.0, 95.0, 101.0, 91.0, 105.0</textarea><br>
					
					<label>Sample 2</label>
					<textarea rows="5" cols="80" name="tTestSampleTwoStr">99.0, 92.0, 102.0, 100.0, 102.0, 89.0</textarea><br>
					
					<input type="submit" value="submit">
				</fieldset>
			</form>
		</section>

	</section>
</body>
</html>
