<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
	<base href="<%=basePath%>">

	<title>Interactive Linear Programming</title>
	<link href="<%=request.getContextPath()%>/css/interactive-linear-programming.css" rel="stylesheet" type="text/css" />

</head>

<body>

<h2>Interactive Linear Programming Calculator</h2>

<form action="linearProgramming.action" method="post">
	<input type="hidden" name="inputType" value="MULTI_FIELDS">
	<label>Objective Function:</label>
	<input type="text" name="objectiveFunction" value="x1 + x2">
	<br>
	<label>Min or Max:</label>
	<select name="minOrMax" id="min_or_max">
		<option value="min">Min</option>
		<option value="max">Max</option>
	</select>
	<br>
	<label>Subject to:</label>
	<input type="text" name="constraints[0]" value="x1 >= 1">
	<br>
	<label>and:</label>
	<input type="text" name="constraints[1]" value="x2 >= 1">
	<br>
	<label>and:</label>
	<input type="text" name="constraints[2]" value="x1 + x2 >= 2">
	<br>
	<label>and:</label>
	<input type="text" name="constraints[3]" value="int x1">
	<br>
	<input type="submit" value="Submit">
</form>

</body>

</html>
