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
<h1>Error...</h1>

<h2>Matrix Dimension Mismatch!</h2>

</body>
</html>
