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
			<h2>Example</h2>
			<p>
				A paired (samples) t-test is used when you have two related observations (i.e., two observations per subject) 
				and you want to see if the means on these two normally distributed interval variables differ from one another.
			</p>
			<p>
				Consider the following study in which standing and supine systolic blood pressures were compared. 
				This study was performed on twelve subjects. 
				Their blood pressures were measured in both positions. It is therefore, a paired samples design.
			</p>
			<table width="95%" border="1" cellpadding="4" cellspacing="0"
				bordercolor="#336666">
				<tr>
					<td colspan="4">
						<div align="center">
							<strong>Blood pressures (mmHg)</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>Subject</strong>
						</div>
					</td>
					<td>
						<div align="center">
							<strong>Standing</strong>
						</div>
					</td>
					<td>
						<div align="center">
							<strong>Supine</strong>
						</div>
					</td>
					<td>
						<div align="center">
							<strong>Difference</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>1</strong>
						</div>
					</td>
					<td>
						<div align="center">
							132
						</div>
					</td>
					<td>
						<div align="center">
							136
						</div>
					</td>
					<td>
						<div align="center">
							4
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>2</strong>
						</div>
					</td>
					<td>
						<div align="center">
							146
						</div>
					</td>
					<td>
						<div align="center">
							145
						</div>
					</td>
					<td>
						<div align="center">
							1
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>3</strong>
						</div>
					</td>
					<td>
						<div align="center">
							135
						</div>
					</td>
					<td>
						<div align="center">
							140
						</div>
					</td>
					<td>
						<div align="center">
							5
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>4</strong>
						</div>
					</td>
					<td>
						<div align="center">
							141
						</div>
					</td>
					<td>
						<div align="center">
							147
						</div>
					</td>
					<td>
						<div align="center">
							6
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>5</strong>
						</div>
					</td>
					<td>
						<div align="center">
							139
						</div>
					</td>
					<td>
						<div align="center">
							142
						</div>
					</td>
					<td>
						<div align="center">
							3
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>6</strong>
						</div>
					</td>
					<td>
						<div align="center">
							162
						</div>
					</td>
					<td>
						<div align="center">
							160
						</div>
					</td>
					<td>
						<div align="center">
							-2
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>7</strong>
						</div>
					</td>
					<td>
						<div align="center">
							128
						</div>
					</td>
					<td>
						<div align="center">
							137
						</div>
					</td>
					<td>
						<div align="center">
							9
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>8</strong>
						</div>
					</td>
					<td>
						<div align="center">
							137
						</div>
					</td>
					<td>
						<div align="center">
							136
						</div>
					</td>
					<td>
						<div align="center">
							-1
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>9</strong>
						</div>
					</td>
					<td>
						<div align="center">
							145
						</div>
					</td>
					<td>
						<div align="center">
							149
						</div>
					</td>
					<td>
						<div align="center">
							4
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>10</strong>
						</div>
					</td>
					<td>
						<div align="center">
							151
						</div>
					</td>
					<td>
						<div align="center">
							158
						</div>
					</td>
					<td>
						<div align="center">
							7
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>11</strong>
						</div>
					</td>
					<td>
						<div align="center">
							131
						</div>
					</td>
					<td>
						<div align="center">
							120
						</div>
					</td>
					<td>
						<div align="center">
							-11
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>12</strong>
						</div>
					</td>
					<td>
						<div align="center">
							143
						</div>
					</td>
					<td>
						<div align="center">
							150
						</div>
					</td>
					<td>
						<div align="center">
							7
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>Mean</strong>
						</div>
					</td>
					<td>
						<div align="center">
							140.83
						</div>
					</td>
					<td>
						<div align="center">
							143.33
						</div>
					</td>
					<td>
						<div align="center">
							2.50
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<strong>SD</strong>
						</div>
					</td>
					<td>
						<div align="center">
							9.49
						</div>
					</td>
					<td>
						<div align="center">
							10.83
						</div>
					</td>
					<td>
						<div align="center">
							5.50
						</div>
					</td>
				</tr>
			</table>
			<p>
				The corresponding input data for this example are: <br>
				<br>
				Sample 1: 132, 146, 135, 141, 139, 162, 128, 137, 145, 151, 131, 143 <br>
				Sample 2: 136, 145, 140, 147, 142, 160, 137, 136, 149, 158, 120, 150 <br>
				<br>
				And the results are: <br>
				<img alt="results" src="<%=request.getContextPath()%>/images/examples/two-sample-t-test-example-results.png">
			</p>
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
