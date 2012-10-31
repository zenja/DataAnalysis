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
				1. Upload a file with a series of double value pairs, one pair each line. 
				In each pair, use comma to seperate the two numbers.
				2. Submit the form to see the results.
			</p>
			<h2>Case Study</h2>
			<p>
				Test data: <a href="<%=request.getContextPath()%>/download/test-data/testdata-simple-regression.txt" target="_blank">Download Test Data</a>
				<br>
				In the test data we list a series of value pairs. After we upload the file, 
				we can get a chart shown the input data, the fitting equation, 
				and a series of indexes, for example, standard value, R value, etc.
			</p>
			<h2>Example</h2>
			<p>
				Data on 20 mid-level managers in insurance industry in 2003: number of
				employees supervised and annual salary in $1,000. <br>
			</p>
			<table border="1">
					<tr>
						<td>Employees</td>
						<td>28</td>
						<td>31</td>
						<td>38</td>
						<td>38</td>
						<td>43</td>
						<td>47</td>
						<td>48</td>
					</tr>
					<tr>
						<td>Salary</td>
						<td>99.4</td>
						<td>102.4</td>
						<td>136.3</td>
						<td>127.3</td>
						<td>157.5</td>
						<td>121.5</td>
						<td>173.4</td>
					</tr>
				</table>
				<table border="1">
					<tr>
						<td>Employees</td>
						<td>49</td>
						<td>53</td>
						<td>56</td>
						<td>56</td>
						<td>56</td>
						<td>60</td>
						<td>60</td>
					</tr>
					<tr>
						<td>Salary</td>
						<td>197.8</td>
						<td>200.5</td>
						<td>176.9</td>
						<td>219.9</td>
						<td>223.4</td>
						<td>219.0</td>
						<td>224.4</td>
					</tr>
				</table>
				<table border="1">
					<tr>
						<td>Employees</td>
						<td>60</td>
						<td>60</td>
						<td>70</td>
						<td>72</td>
						<td>78</td>
						<td>81</td>
					</tr>
					<tr>
						<td>Salary</td>
						<td>247.2</td>
						<td>237.5</td>
						<td>207.3</td>
						<td>214.9</td>
						<td>242.9</td>
						<td>262.5</td>
					</tr>
				</table>
			<p>
				Questions of interest: <br>
				- Is annual compensation associated to personnel supervised? <br>
				- If a manager’s supervision responsibilities increase by 30 employees,
				can she predict what her new salary might be? <br>
			</p>
			<p>
				We build a file representing the data as 
				<a href="<%=request.getContextPath()%>/download/test-data/examples/simple-regression.txt" target="_blank">this</a>.
			</p>
			<p>
				The diagram for the input is: <br>
				<img alt="example" src="<%=request.getContextPath()%>/images/examples/simple-regression.png">
			</p>
			<p>
				After computation, the system shows that: <br>
				<br>
				The fitting equation is: y = 3.0538955178983263x + (24.07886292991075) <br>
				The coefficient of determination: R-square = 0.7816599561722873
			</p>
			<p>
				Let's answer the questions: <br>
				- Is annual compensation associated to personnel supervised? <br>
				Answer: Yes, The fitting equation is: y = 3.054x + 24.079
				and the coefficient of determination is 0.78 <br>
				<br>
				- If a manager’s supervision responsibilities increase by 30 employees,
				can she predict what her new salary might be? <br>
				Answer: according to the fitting equation, 3.054 * 30 = 91.62, 
				so the salary will increase by 91620 if the manager’s supervision responsibilities increase by 30 employees.
			</p>
			<h2>Demo Video</h2>
			<a href="http://www.youtube.com/watch?v=c6Jy_RzY6qo&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
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
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
