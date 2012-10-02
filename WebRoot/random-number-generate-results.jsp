<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>Random Number Generation</title>

</head>

<body>

<h1>Random Number Generation</h1>

<h2>Distribution type: ${distTypeStr}</h2>
<p>${numSample} samples:</p>
<s:iterator value="randomSequence" var="num">
	${ num } <br> 
</s:iterator>

</body>

</html>
