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
				1. Set the values of classes, one class per line. 
				In each line, separate different values of a class by commas. <br>
				2. Submit the form to get the results.
			</p>
			<h2>Case Study</h2>
			<p>
				For example, we want to conduct a One-Way Analysis of Variance (ANOVA) 
				to evaluate the null hypothesis that 
				the means of a collection of univariate datasets are the same. 
				The sample classes are: <br>
				<br>
				93.0, 103.0, 95.0, 101.0, 91.0, 105.0, 96.0, 94.0, 101.0 <br>
				99.0, 92.0, 102.0, 100.0, 102.0, 89.0 <br>
				110.0, 115.0, 111.0, 117.0, 128.0, 117.0 <br>
				<br>
				We set the corresponding values and submit the form, 
				then we get p-value: 6.9594458853572405E-6 and
				f-value: 24.67361709460643
			</p>
			<h2>Example</h2>
			<p>
				Analysis of Variance (ANOVA) is a hypothesis-testing technique used to test the equality of two
				or more population (or treatment) means by examining the variances of samples that are taken.
				ANOVA allows one to determine whether the differences between the samples are simply due to
				random error (sampling errors) or whether there are systematic treatment effects that causes the
				mean in one group to differ from the mean in another.
			</p>
			<p>
				Consider this example:
				Suppose the National Transportation Safety Board (NTSB) wants to examine the safety of
				compact cars, midsize cars, and full-size cars. It collects a sample of three for each of the
				treatments (cars types). Using the hypothetical data provided below, test whether the mean
				pressure applied to the driver’s head during a crash test is equal for each types of car. Use α =
				5%.
				<br>
				<img alt="example" src="<%=request.getContextPath()%>/images/examples/one-way-anova-test-example.png">
			</p>
			<p>
				The input data for this problem are: <br>
				<br>
				643, 655, 702<br>
				469, 427, 525<br>
				484, 456, 402<br>
				<br>
			</p>
			<p>
				The corresponding results are: <br>
				<img alt="results" src="<%=request.getContextPath()%>/images/examples/one-way-anova-test-example-results.png">
			</p>
		</section>
		
		<section class="form-section">
			<form action="oneWayAnovaTest" method="post">
				<fieldset>
					<legend>One-way Anova Test</legend>
					<input type="hidden" name="inputType" value="PLAIN_TEXT">
					
					<label>Classes: (one line per class)</label>
<textarea rows="8" cols="80" name="oneWayAnovaTestDataStr">
93.0, 103.0, 95.0, 101.0, 91.0, 105.0, 96.0, 94.0, 101.0
99.0, 92.0, 102.0, 100.0, 102.0, 89.0
110.0, 115.0, 111.0, 117.0, 128.0, 117.0
</textarea><br>
					
					<input type="submit" value="submit">
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
