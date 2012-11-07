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
				1. Select a distribution type; <br>
				2. Set the parameters of the distribution; <br>
				3. Set the value of x which is the value you want to check the probability of; <br>
				4. Submit the form to see the results.
			</p>
			<h2>Case Study</h2>
			<p>
				For example, we choose exponential distribution as the distribution type 
				and set the mean value to 100. Suppose we want to check the probability P(X=95) and 
				cumulative probability P(X&lt;=95), then we set the value of x to 95 and submit the form. 
				The <a href="http://210.75.252.103:8888/DataAnalysis/distribution?inputType=PLAIN_TEXT&distributionType=EXPONENTIAL&exponentialMean=100&x=95" target="_blank">results</a> 
				shows that P(X=95) is 0.0 (because it is a point and the value space is continuous) 
				and P(X&lt;=95) is 0.6132589765454988.
			</p>
			<h2>Demo Video</h2>
			<a href="http://www.youtube.com/watch?v=miZGB8Y1xDw&feature=share&list=ULmiZGB8Y1xDw" target="_blank">View demo</a>
		</section>
		
		<section class="form-section">
			<form action="distribution" method="post">
				<fieldset>
					<legend>Distribution</legend>
					
					<input type="hidden" name="inputType" value="PLAIN_TEXT">
					
					<label>Distribution Type:</label>
					<select id="dist-dist-type" name="distributionType">
						<option value="select">-- select a distribution type --</option>
						<option value="EXPONENTIAL">Exponential Distribution</option>
						<option value="BETA">Beta Distribution</option>
						<option value="NORMAL">Normal Distribution</option>
						<option value="GAMMA">Gamma Distribution</option>
						<option value="WEIBULL">Weibull Distribution</option>
					</select><br>
					
					<label>Value of x: </label>
					<input type="text" name="x">
					
					<!-- for exponential distribution -->
					<section id="dist-exponential-dist">
						<label>Mean Value:</label>
						<input type="text" name="exponentialMean">
					</section>
					
					<!-- for beta distribution -->
					<section id="dist-beta-dist">
						<label>Alpha: </label>
						<input type="text" name="betaAlpha"><br>
						<label>Beta: </label>
						<input type="text" name="betaBeta">
					</section>
					
					<!-- for normal distribution -->
					<section id="dist-normal-dist">
						<label>Mean Value: </label>
						<input type="text" name="normalMean"><br>
						<label>Standard Deviation: </label>
						<input type="text" name="normalSd">
					</section>
					
					<!-- for gamma distribution -->
					<section id="dist-gamma-dist">
						<label>Alpha: </label>
						<input type="text" name="gammaAlpha"><br>
						<label>Beta: </label>
						<input type="text" name="gammaBeta">
					</section>
					
					<!-- for weibull distribution -->
					<section id="dist-weibull-dist">
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
