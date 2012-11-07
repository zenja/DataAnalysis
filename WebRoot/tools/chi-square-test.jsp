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
				For example, we want to compute a chi-square 
				statistic measuring the agreement between the observed values: <br>
				10, 9, 11<br>
				and the expected values: <br>
				10.1, 9.8, 10.3 <br>
				We set the corresponding values and submit the form, 
				then we get the p-value: 0.9449271775169028
			</p>
			<h2>Example</h2>
			<p>
				A chi-square goodness of fit test allows us to test 
				whether the observed proportions for a categorical variable 
				differ from hypothesized proportions. 
				For example, let's suppose that we believe that 
				the general population consists of 10% Hispanic, 
				10% Asian, 10% African American and 70% White folks. 
				We want to test whether the observed proportions 
				from our sample differ significantly from these hypothesized proportions. 
			</p>
			<p>
				<img alt="race" src="<%=request.getContextPath()%>/images/examples/chi-square-test-example-race.gif">
			</p>
			<p>
				The results are: <br>
				<img alt="results" src="<%=request.getContextPath()%>/images/examples/chi-square-test-example-results.png">
				<br>
				These results show that racial 
				composition in our sample does not differ 
				significantly from the hypothesized values that we supplied
			</p>
		</section>
		
		<section class="form-section">
			<form action="chiSquareTest" method="post">
				<fieldset>
					<legend>Chi-square Test</legend>
					<input type="hidden" name="inputType" value="PLAIN_TEXT">
					
					<label>Observed values: </label>
					<textarea rows="5" cols="80" name="observedStr">10, 9, 11</textarea><br>
					
					<label>Expected values: </label>
					<textarea rows="5" cols="80" name="expectedStr">10.1, 9.8, 10.3</textarea><br>
					
					<input type="submit" value="submit">
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
