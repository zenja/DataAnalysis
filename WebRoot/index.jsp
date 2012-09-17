<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Data Analysis System (Prototype)</title>

</head>

<body>
	<h1>Data Analysis System Prototype:</h1>
	
	<h5>Input Data File:</h5>
	<form action="statistics.action" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend>Statistics</legend>
			<label>Input File:</label>
			<input type="file" name="file" />
			<br />
			<input type="submit" value="upload" />
			<p><a href="<%=request.getContextPath()%>/download/test-data/testdata-statistics.txt" target="_blank">Download Test Data</a></p>
		</fieldset>
	</form>
	
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
			</p>
		</fieldset>
	</form>
	
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
			</p>
		</fieldset>
	</form>
	
</body>
</html>
