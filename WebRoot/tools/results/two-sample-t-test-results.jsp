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
	<title>Two Sample t-test</title>
</head>

<body>

<h1>Two Sample t-test</h1>

<h2>Input</h2>

<p>
Sample 1:<br>
${ tTestSampleOneStr }
</p>

<p>
Sample 2:<br>
${ tTestSampleTwoStr }
</p>

<h2>Result</h2>

<p>
p-value: ${ pValue } <br>
t-statistic: ${ tStatistic }
</p>

</body>
</html>
