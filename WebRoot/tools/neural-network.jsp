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
				1. Upload a file describing the structure of the 
				neural network you are going to build 
				and the training data. 
				The file is a XML file.
				It has two main parts: &lt;TrainingDataList&gt; and &lt;NetworkLayerList&gt;.
				The &lt;TrainingDataList&gt; part describes the training data of the network. 
				The &lt;NetworkLayerList&gt; part describes the structure of the network.
				Please refer to the test data below for the details of the file structure. <br>
				2. Submit the form to see the results.
			</p>
			<h2>Case Study</h2>
			<p>
				Test data: <a href="<%=request.getContextPath()%>/download/test-data/neural-network-xor.xml" target="_blank">Download Test Data</a>
				<br><br>
				The test data above builds a simple neural network 
				that can understand and perform <a href="http://en.wikipedia.org/wiki/XOR" target="_blank">XOR</a> operation.
				In the &lt;TrainingDataList&gt; part, 
				we list the training inputs and the expected outputs (ideal values) of the XOR operation. 
				In the &lt;NetworkLayerList&gt; part, we make three neural layers 
				(one input layer with 2 neural units, one hidden layer with 4 neural units for processing, 
				and one output layer with 1 neural units), 
				and that is enough 
				for solving the XOR problem. <br>
				<br>
				We upload the file and then see the results: <br>
				Input = 0.0, 0.0; Actual value = 0.9463331018695771; Ideal value = 1.0<br>
				Input = 1.0, 0.0; Actual value = 0.018333600444332496; Ideal value = 0.0<br>
				Input = 0.0, 1.0; Actual value = 0.02036504957675241; Ideal value = 0.0<br>
				Input = 1.0, 1.0; Actual value = 0.011732788320812834; Ideal value = 0.0<br>
				<br>
				We can see that the neural network we created works fine.
			</p>
			<h2>Example</h2>
			<p>
				We can use neural network to predict the sales of a company. 
				For example, we have the sales data for 10 days like this: 
			</p>
			<table>
				<tr>
					<td>Day 1</td>
					<td>Day 2</td>
					<td>Day 3</td>
					<td>Day 4</td>
					<td>Day 5</td>
					<td>Day 6</td>
					<td>Day 7</td>
					<td>Day 8</td>
					<td>Day 9</td>
					<td>Day 10</td>
				</tr>
				<tr>
					<td>1517.66</td>
					<td>1636.19</td>
					<td>1588.49</td>
					<td>1437.20</td>
					<td>1497.57</td>
					<td>1520.59</td>
					<td>1580.21</td>
					<td>1641.35</td>
					<td>1360.85</td>
					<td>1464.26</td>
				</tr>
			</table>
			<p>
				If we want to make a prediction model for the sales data, 
				we can make a neural network like 
				<a href="" target="_blank">this</a>. <br>
				And the training results is like this: <br>
				<br>
			</p>
			<h2>Demo Video</h2>
			<a href="http://www.youtube.com/watch?v=hXf7bTR-s5Q&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
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
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
