<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Complex Number Operation</title>
</head>

<body>
<h1>Complex Number Operation</h1>

<h2>Input</h2>

<p>
Complex Number 1:<br>
${ cnOneStr }
</p>

<p>
Complex Number 2:<br>
${ cnTwoStr }
</p>

<p>Operation: ${ operationName }</p>

<h2>Result</h2>

<p>
${ resultStr }
</p>

</body>
</html>
