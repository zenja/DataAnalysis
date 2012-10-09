<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'matrix-operation-results.jsp' starting page</title>
</head>

<body>
<h1>Matrix Operation</h1>

<h2>Input</h2>

<p>
Matrix 1:<br>
${ matrixOneStr }
</p>

<p>
Matrix 2:<br>
${ matrixTwoStr }
</p>

<p>Operation: ${ operationString }</p>

<h2>Result</h2>

<p>
${ resultMatrixStr }
</p>

</body>
</html>
