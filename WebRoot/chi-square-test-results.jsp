<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Chi-square Test</title>
</head>

<body>

<h1>Chi-square Test</h1>

<h2>Input</h2>

<p>
Observed values:<br>
${ observedStr }
</p>

<p>
Expected values:<br>
${ expectedStr }
</p>

<h2>Result</h2>

<p>
p-value: ${ pValue }
</p>

</body>
</html>
