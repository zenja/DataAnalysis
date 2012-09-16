<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">

	<title>Linear Programming Results</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<h2>Problem Description: </h2>
	<p>${ problemDescription }</p>
	
	<h2>Results: </h2>
	<p> At point: (
	<s:iterator value="resultPoint" var="number" status="rowstatus">
  		${ number }, 
	</s:iterator>
	) 
	we can get the target value: ${ resultValue }
	<p>
</body>
</html>
