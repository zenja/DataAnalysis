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
			<form action="complexNumberOperation" method="post">
				<fieldset>
					<legend>Complex Number Operation</legend>
					<input type="hidden" name="inputType" value="PLAIN_TEXT">
					<label>Complex 1:</label>
					<input type="text" name="cnStringOne" value="1.0+1.0i"><br>
					<section id="complex-two">
						<label>Complex 2:</label>
						<input type="text" name="cnStringTwo" value="1.0+1.0i"><br>
					</section>
					<label>Precision:</label>
					<input type="text" name="precision" value="2"><br>
					<label>Operation:</label>
					<select name="operationType" id="complex-operation-select">
						<option value="MULTIPLY">multiply</option>
						<option value="ADD">add</option>
						<option value="SUBTRACT">subtract</option>
						<option value="DIVIDE">divide</option>
						<option value="SIN">sin</option>
						<option value="COS">cos</option>
						<option value="TAN">tan</option>
						<option value="ASIN">inverse sine</option>
						<option value="ACOS">inverse cosine</option>
						<option value="ATAN">inverse tangent</option>
						<option value="SINH">hyperbolic sine</option>
						<option value="COSH">hyperbolic cosine</option>
						<option value="TANH">hyperbolic tangent</option>
						<option value="LOG">log</option>
						<option value="SQRT">sqrt</option>
						<option value="EXP">exp</option>
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
