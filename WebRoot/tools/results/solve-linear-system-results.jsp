<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>Solving Linear System - Results</title>
</head>

<body>

<h1>Solving Linear System</h1>

<h2>Problem Description:</h2>
<p>
${ problemDescription }
</p>

<h2>Results:</h2>
<p>
${ resultDescription }
</p>

</body>
</html>
