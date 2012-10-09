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
						<a href="http://www.youtube.com/watch?v=JfhhZrBnD_4&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
		</section>
		
		<section class="form-section">
			<form action="linearProgramming.action" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>Linear Programming</legend>
					<input type="hidden" name="inputType" value="FILE">
					<label>Input File:</label>
					<input type="file" name="file">
					<br />
					<input type="submit" value="upload">
					<p>
						<a href="<%=request.getContextPath()%>/download/test-data/lpformat-example.txt" target="_blank">Download Test Data</a>
						<br>
						<a href="<%=request.getContextPath()%>/interactive-linear-programming.jsp">Interactive Calculator</a>
						<br>
						<a href="http://www.youtube.com/watch?v=b6FctQ-_M5E&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
		</section>
		
		<section class="form-section">
			<form action="simpleRegression.action" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>Simple Regression</legend>
					<input type="hidden" name="inputType" value="FILE">
					<label>Input File:</label>
					<input type="file" name="file">
					<br />
					<input type="submit" value="upload">
					<p>
						<a href="<%=request.getContextPath()%>/download/test-data/testdata-simple-regression.txt" target="_blank">Download Test Data</a>
						<br>
						<a href="http://www.youtube.com/watch?v=c6Jy_RzY6qo&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
		</section>
		
		<section class="form-section">
			<form action="kMeans.action" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>K-Means Clustering</legend>
					<input type="hidden" name="inputType" value="FILE">
					<label>Input File:</label>
					<input type="file" name="file">
					<br />
					<label>Number of clusters:</label>
					<input type="text" name="numClusters" value="2">
					<br>
					<label>Number of iterations:</label>
					<input type="text" name="numIterations" value="100">
					<br>
					<input type="submit" value="upload">
					<p>
						<a href="<%=request.getContextPath()%>/download/test-data/testdata-kmeans.txt" target="_blank">Download Test Data</a>
						<br>
						<a href="http://www.youtube.com/watch?v=e9EIVc_FRKg&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
		</section>
		
		<section class="form-section">
			<form action="neuralNetwork"  method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>Neural Network</legend>
					<input type="hidden" name="inputType" value="FILE">
					<label>Input File:</label>
					<input type="file" name="file">
					<br />
					<input type="submit" value="upload">
					<p>
						<a href="<%=request.getContextPath()%>/download/test-data/neural-network-xor.xml" target="_blank">Download Test Data</a>
						<br>
						<a href="http://www.youtube.com/watch?v=hXf7bTR-s5Q&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
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
					<p>
						<a href="<%=request.getContextPath()%>/download/test-data/time-series-data-example.xml" target="_blank">Download Test Data</a>
						<br>
						<a href="http://www.youtube.com/watch?v=hFVXxH5sql4&feature=share&list=ULhFVXxH5sql4" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
		</section>
		
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
					
					<p>
						<a href="http://www.youtube.com/watch?v=cowkfBbSnRI&feature=share&list=ULcowkfBbSnRI" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
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
					
					<p>
						<a href="http://www.youtube.com/watch?v=miZGB8Y1xDw&feature=share&list=ULmiZGB8Y1xDw" target="_blank">View demo</a>
					</p>
					
				</fieldset>
			</form>
		</section>
		
		<section class="form-section">
			<form action="valueServer" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>Value Server</legend>
					<p>This tool estimates a probability density function based on data from the file, 
					and then generate a sequence of numbers based on that density function</p>
					<input type="hidden" name="inputType" value="FILE">
					<label>Input File:</label>
					<input type="file" name="file"><br>
					<label>Number of samples:</label>
					<input type="text" name="numSample" value="500" ><br>
					<input type="submit" value="upload">
					<p>
						<a href="<%=request.getContextPath()%>/download/test-data/testdata-value-server.txt" target="_blank">Download Test Data</a>
						<br>
						<a href="http://youtu.be/33UCu-BiL-w" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
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
					<p>
						<a href="<%=request.getContextPath()%>/download/test-data/testdata-solve-linear-system.txt" target="_blank">Download Test Data</a>
						<br>
						<a href="#" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
		</section>
		
		<section class="form-section">
			<form action="matrixOperation" method="post">
				<fieldset>
					<legend>Matrix Operation</legend>
					<input type="hidden" name="inputType" value="PLAIN_TEXT">
					<label>Matrix 1:</label>
<textarea rows="8" cols="16" name="matrixStringOne">
0 0 0 0 0 1
0 0 0 0 1 0
0 0 0 1 0 0
0 0 1 0 0 0
0 1 0 0 0 0
1 0 0 0 0 0
</textarea>
					<br>
					<label>Matrix 2:</label>
<textarea rows="8" cols="16" name="matrixStringTwo">
1 2 3 4 5 6
7 8 9 10 11 12
</textarea>
					<br>
					<label>Operation:</label>
					<select name="matrixOperationType" value="MULTIPLY">
						<option value="MULTIPLY">Multiply</option>
						<option value="ADD">Add</option>
						<option value="SUBTRACT">Subtract</option>
					</select>
					<br>
					<input type="submit" value="submit">
					<p>
						<a href="#" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
