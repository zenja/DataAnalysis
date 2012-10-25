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
		
		<section class="form-section">
			<form action="baumWelchHMM" method="post">
				<fieldset>
					<legend>Baum Welch Hidden Markov Model (Experiment Version)</legend>
					<input type="hidden" name="inputType" value="PLAIN_TEXT">
					<input type="hidden" name="stateDistributionType" value="DISCRETE">
					
					<label>#States: </label>
					<input type="text" name="numStates" value="2"><br>
					
					<label>#Items: </label>
					<input type="text" name="numItems" value="2"><br>
					
					<label>Pi List: </label>
					<textarea rows="5" cols="80" name="piListStr">0.95, 0.05</textarea><br>
					
					<label>State Distribution Probabilities List: </label>
					<textarea rows="5" cols="80" name="stateDistributionProbabilitiesListStr">0.95, 0.05; 0.20, 0.80</textarea><br>
					
					<label>Transition Probability Data List: </label>
					<textarea rows="5" cols="80" name="transitionProbabilityDataListStr">0, 1, 0.05; 0, 0, 0.95; 1, 0, 0.10; 1, 1, 0.90</textarea><br>
					
					<label>Observation Count: </label>
					<input type="text" name="observationCount" value="200"><br>
					
					<label>Observation Length: </label>
					<input type="text" name="observationLength" value="100"><br>
					
					<input type="submit" value="submit">
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
