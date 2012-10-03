<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">

	<title>Distribution</title>
</head>

<body>

<h1>Distribution</h1>

<h2>Distribution type: ${distTypeStr}</h2>

<p>Results:</p>

<ul>
	<li>P(X = ${x}) = ${probability}</li>
	<li>P(X &lt;= ${x}) = ${cumulativeProbability}</li>
	<li>Mean: ${numericalMean}</li>
	<li>Variance: ${numericalVariance}</li>
	<li>Support lower bound: ${supportLowerBound}</li>
	<li>Support upper bound: ${supportUpperBound}</li>
</ul>

</body>
</html>
