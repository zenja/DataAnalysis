<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>DataGobble Analysis System</title>
	<link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/index.js"></script>
</head>

<body>
	<section id="page-wrapper">
		
		<header>
		<h1>
		<img id="data-gobble-logo-img" alt="logo" src="<%=request.getContextPath()%>/images/data-gobble-logo.png">
		DataGobble Analysis System
		</h1>
		</header>
		
		<ul>
			<li><a href="<%=request.getContextPath()%>/tools/statistics.jsp" target="_blank">Statistics</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/linear-programming.jsp" target="_blank">Linear Programming</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/simple-regression.jsp" target="_blank">Simple Regression</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/kmeans-clustering.jsp" target="_blank">K-Means Clustering</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/neural-network.jsp" target="_blank">Neural Network</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/time-series-visualization.jsp" target="_blank">Time Series Visualization</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/baum-welch-hmm.jsp" target="_blank">Baum Welch Hidden Markov Model</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/random-number-generate.jsp" target="_blank">Random Number Generation</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/distribution.jsp" target="_blank">Distribution</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/value-server.jsp" target="_blank">Value Server</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/solve-linear-system.jsp" target="_blank">Solving Linear System</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/matrix-operation.jsp" target="_blank">Matrix Operation</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/complex-number-operation.jsp" target="_blank">Complex Number Operation</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/chi-square-test.jsp" target="_blank">Chi-square Test</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/one-way-anova-test.jsp" target="_blank">One-way Anova Test</a></li>
			<li><a href="<%=request.getContextPath()%>/tools/two-sample-t-test.jsp" target="_blank">Two Sample t-test</a></li>
		</ul>
		
	</section>
</body>
</html>
