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
		
		<section>
			<h2>How to use this tool?</h2>
			<p>
				1. Select a file with a series of values. 
				The values are divided into groups (each group per line). 
				In every line, use comma to separate each value. <br>
				2. Set the number of clusters. <br>
				3. Set the number of training iterations.
			</p>
			<h2>Case Study</h2>
			<p>
				Test data: <a href="<%=request.getContextPath()%>/download/test-data/testdata-kmeans.txt" target="_blank">Download Test Data</a>
				<br>
				<br>
				In the test file we have 5 groups of numbers:<br>
				28,15,22<br>
				16,15,32<br>
				32,20,44<br>
				1,2,3<br>
				3,2,1<br>
				<br>
				We set the number of clusters to 2 
				and the number of training iterations to 100. 
				Submit the form and we can see the results: <br>
				<br>
				Cluster 0:<br>
				[ 1.0, 2.0, 3.0 ]<br>
				[ 3.0, 2.0, 1.0 ]<br>
				Cluster 1:<br>
				[ 16.0, 15.0, 32.0 ]<br>
				[ 32.0, 20.0, 44.0 ]<br>
				[ 28.0, 15.0, 22.0 ]<br>
			</p>
			<h2>Example</h2>
			<p>
				Suppose we have 4 objects as your training data point and each object have 2 attributes. 
				Each attribute represents coordinate of the object. <br>
			</p>
			<table border="1">
				<tr>
					<th>Object</th>
					<th>Attribute 1 (X): weight index</th>
					<th>Attribute 2 (Y):  pH</th>
				</tr>
				<tr>
					<td>Medicine A</td>
					<td>1</td>
					<td>1</td>
				</tr>
				<tr>
					<td>Medicine B</td>
					<td>2</td>
					<td>1</td>
				</tr>
				<tr>
					<td>Medicine C</td>
					<td>4</td>
					<td>3</td>
				</tr>
				<tr>
					<td>Medicine D</td>
					<td>5</td>
					<td>4</td>
				</tr>
			</table>
			<p>
				Thus, we also know before hand that these objects belong to 
				two groups of medicine (cluster 1 and cluster 2). 
				The problem now is to determine which medicines belong to cluster 1 
				and which medicines belong to the other cluster.  
				Each medicine represents one point with two components coordinate. 
			</p>
			<p>
				The input file for this problem is like 
				<a href="<%=request.getContextPath()%>/download/test-data/examples/k-means-clustering.txt" target="_blank">this</a>: <br>
				<br>
				1,1 <br>
				2,1 <br>
				4,3 <br>
				5,4 <br>
				<br>
				Set the number of clusters to 2 and number of iterations to 100. 
				After uploading the file to the server, we can get the results: <br>
				<br>
				Cluster 0: <br>
				[ 1.0, 1.0, ] <br>
				[ 2.0, 1.0, ] <br>
				Cluster 1: <br>
				[ 4.0, 3.0, ] <br>
				[ 5.0, 4.0, ] <br>
				<br>
				From the results we can see that Medicine A and B belong to the same group, 
				and Medicine C and D belong to another group.
			</p>
			<h2>Demo Video</h2>
			<a href="http://www.youtube.com/watch?v=e9EIVc_FRKg&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
		</section>
		
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
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
