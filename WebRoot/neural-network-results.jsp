<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>Neural Network Results</title>
</head>

<body>
<h2>Neural Network Results</h2>

<h3>Name: ${ neuralNetworkName }</h3>

<h3>Training Log:</h3>
<p>${ trainingLog }</p>

<h3>Training Result:</h3>
<p>${ trainingResult }</p>

</body>
</html>
