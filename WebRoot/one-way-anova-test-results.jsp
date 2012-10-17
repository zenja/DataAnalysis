<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>One-Way Anova Test</title>
</head>

<body>

<h1>One-Way Anova Test</h1>

<h2>Input</h2>

<s:iterator value="oneWayAnovaTestData" var="line" status="rowstatus1">
	<p>
	Class ${ rowstatus1.index }: <br>
	<s:iterator value="line" var="num" status="rowstatus2">
		${ num }, 
	</s:iterator>
	</p>
</s:iterator>

<h2>Result</h2>

<p>
p-value: ${ pValue } <br>
f-value: ${ fStatistic }
</p>

</body>
</html>
