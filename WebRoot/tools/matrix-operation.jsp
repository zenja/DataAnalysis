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
				1. Set the matrix 1 and matrix 2<br>
				2. Select a operation type.<br>
				3. Submit the form to get the results.
			</p>
			<h2>Case Study</h2>
			<p>
				For example, we want to get the results of product of two matrix: <br>
				<br>
				Matrix 1: <br>
				0 0 0 0 0 1 <br>
				0 0 0 0 1 0 <br>
				0 0 0 1 0 0 <br>
				0 0 1 0 0 0 <br>
				0 1 0 0 0 0 <br>
				1 0 0 0 0 0 <br>
				<br>
				and Matrix 2: <br>
				1 2 3 4 5 6 <br>
				7 8 9 10 11 12 <br>
				<br>
				We input the matrices and set the operation type to Multiply, submit the form 
				and get the results: <br>
				<br>
				6.0 5.0 4.0 3.0 2.0 1.0 <br>
				12.0 11.0 10.0 9.0 8.0 7.0
			</p>
			<h2>Demo Video</h2>
			<a href="http://youtu.be/ZK-iEZQGX4o" target="_blank">View demo</a>
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
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
