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
		
		<section class="form-section">
			<form action="kMeans.action" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>K-Means Clustering</legend>
					<input type="hidden" name="inputType" value="FILE">
					<label>Input File:</label>
					<input type="file" name="file">
					<br />
					<label>Number of clusters:</label>
					<input type="text" name="numClusters" value="2">
					<br>
					<label>Number of iterations:</label>
					<input type="text" name="numIterations" value="100">
					<br>
					<input type="submit" value="upload">
					<p>
						<a href="<%=request.getContextPath()%>/download/test-data/testdata-kmeans.txt" target="_blank">Download Test Data</a>
						<br>
						<a href="http://www.youtube.com/watch?v=e9EIVc_FRKg&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
					</p>
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>