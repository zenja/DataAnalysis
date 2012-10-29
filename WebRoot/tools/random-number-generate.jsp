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
				1. Select a distribution type;
				2. Set the parameters of the chosen distribution type;
				3. Set the number of samples;
				4. Submit the form to get the generated values.
			</p>
			<h2>Case Study</h2>
			<p>
				Using this tool we can get a sample of random values 
				which follow a certain kind of distribution. <br>
				<br>
				For example, we want to generate 50 random values which follow the 
				exponential distribution with the mean value 100. 
				Select the distribution type of Exponential Distribution, set the mean value to 100 
				and the number of samples to 50. Submit the form and we can get the 
				<a href="http://210.75.252.103:8888/DataAnalysis/randomNumberGenerate?inputType=PLAIN_TEXT&distributionType=EXPONENTIAL&numSample=50&exponentialMean=100" target="_blank">results</a>.   
			</p>
			<h2>Demo Video</h2>
			<a href="http://www.youtube.com/watch?v=cowkfBbSnRI&feature=share&list=ULcowkfBbSnRI" target="_blank">View demo</a>
		</section>
		
		<section class="form-section">
			<form action="randomNumberGenerate" method="post">
				<fieldset>
					<legend>Random Number Generation</legend>
					
					<input type="hidden" name="inputType" value="PLAIN_TEXT">
					
					<label>Distribution Type:</label>
					<select id="rng-dist-type" name="distributionType">
						<option value="select">-- select a distribution type --</option>
						<option value="EXPONENTIAL">Exponential Distribution</option>
						<option value="BETA">Beta Distribution</option>
						<option value="NORMAL">Normal Distribution</option>
						<option value="GAMMA">Gamma Distribution</option>
						<option value="WEIBULL">Weibull Distribution</option>
					</select><br>
					
					<label>Number of samples:</label>
					<input type="text" name="numSample" value="500" ><br>
					
					<!-- for exponential distribution -->
					<section id="rng-exponential-dist">
						<label>Mean Value:</label>
						<input type="text" name="exponentialMean">
					</section>
					
					<!-- for beta distribution -->
					<section id="rng-beta-dist">
						<label>Alpha: </label>
						<input type="text" name="betaAlpha"><br>
						<label>Beta: </label>
						<input type="text" name="betaBeta">
					</section>
					
					<!-- for normal distribution -->
					<section id="rng-normal-dist">
						<label>Mean Value: </label>
						<input type="text" name="normalMean"><br>
						<label>Standard Deviation: </label>
						<input type="text" name="normalSd">
					</section>
					
					<!-- for gamma distribution -->
					<section id="rng-gamma-dist">
						<label>Alpha: </label>
						<input type="text" name="gammaAlpha"><br>
						<label>Beta: </label>
						<input type="text" name="gammaBeta">
					</section>
					
					<!-- for weibull distribution -->
					<section id="rng-weibull-dist">
						<label>Alpha: </label>
						<input type="text" name="wbAlpha"><br>
						<label>Beta: </label>
						<input type="text" name="wbBeta">
					</section>
					
					<input type="submit" value="submit">
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
