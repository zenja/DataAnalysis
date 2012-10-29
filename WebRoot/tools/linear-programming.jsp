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
				1. Upload a file describing the problem to be solved.
				The description style is shown <a href="http://lpsolve.sourceforge.net/5.5/lp-format.htm" target="_blank">here</a>.
				 <br>
				2. Submit the form to see the results.
			</p>
			<h2>Case Study</h2>
			<p>
				Test data: <a href="<%=request.getContextPath()%>/download/test-data/lpformat-example.txt" target="_blank">Download Test Data</a>
				<br>
				Suppose we want to solve a problem like this: <br>
				Get the minimum value of x1 + x2, such that: <br>
				x1 &gt;= 1; <br>
				x2 &gt;= 1; <br>
				x1 + x2 &gt;= 2; <br>
				The description for this problem is:  <br>
				min: x1 + x2; <br>
				x1 &gt;= 1; <br>
				x2 &gt;= 1; <br>
				x1 + x2 &gt;= 2; <br>
				int x1; <br>
				We upload the file and then can get the results: x1 = 1, x2 = 2
			</p>
			<h2>Demo Video</h2>
			<a href="http://www.youtube.com/watch?v=b6FctQ-_M5E&feature=share&list=PLrlNXTbG3w3cj63Gyfq0IeZCgo5sGpuXe" target="_blank">View demo</a>
		</section>
		
		<section class="form-section">
			<form action="linearProgramming.action" method="post" enctype="multipart/form-data">
				<fieldset>
					<legend>Linear Programming</legend>
					<input type="hidden" name="inputType" value="FILE">
					<label>Input File:</label>
					<input type="file" name="file">
					<br />
					<input type="submit" value="upload">
					<p>
						<a href="<%=request.getContextPath()%>/tools/interactive-linear-programming.jsp">Interactive Calculator</a>
					</p>
				</fieldset>
			</form>
		</section>
		
	</section>
</body>
</html>
