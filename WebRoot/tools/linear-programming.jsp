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
				Test data: <a href="<%=request.getContextPath()%>/download/test-data/lpformat-example.txt" target="_blank">Download Test Data</a><br>
				<br>
				Suppose we want to solve a problem like this: <br>
				Get the minimum value of x1 + x2, such that: <br>
				<br>
				x1 &gt;= 1; <br>
				x2 &gt;= 1; <br>
				x1 + x2 &gt;= 2; <br>
				<br>
				The description for this problem is:  <br>
				<br>
				min: x1 + x2; <br>
				x1 &gt;= 1; <br>
				x2 &gt;= 1; <br>
				x1 + x2 &gt;= 2; <br>
				int x1; <br>
				<br>
				We upload the file and then can get the results: x1 = 1, x2 = 2
			</p>
			<h2>Example</h2>
			<p>
				A calculator company produces a scientific calculator and a graphing calculator. 
				Long-term projections indicate an expected demand of at least 100 scientific and 80 
				graphing calculators each day. Because of limitations on production capacity, 
				no more than 200 scientific and 170 graphing calculators can be made daily. 
				To satisfy a shipping contract, a total of at least 200 calculators much be shipped each day. <br>
				If each scientific calculator sold results in a $2 loss, 
				but each graphing calculator produces a $5 profit, 
				how many of each type should be made daily to maximize net profits? <br>
				The question asks for the optimal number of calculators, so my variables will stand for that:<br>
				<br>
				x: number of scientific calculators produced<br>
				y: number of graphing calculators produced<br>
			</p>
			<p>
				<font face="Arial" size="2">Since they can't produce negative numbers of calculators, I have 
		        the two constraints, </font><font face="Times New Roman" size="3"><i>x</i> <u>&gt;</u> 0</font><font
		        face="Arial" size="2"> and </font><font face="Times New Roman" size="3"><i>y</i>&nbsp;<u>&gt;</u>&nbsp;0</font><font
		        face="Arial" size="2">. But in this case, I can ignore these constraints, because I already have 
		        that </font><font face="Times New Roman" size="3"><i>x</i> <u>&gt;</u> 100</font><font face="Arial"
		        size="2"> and </font><font face="Times New Roman" size="3"><i>y</i>&nbsp;<u>&gt;</u>&nbsp;80</font><font
		        face="Arial" size="2">. The exercise also gives maximums: </font><font face="Times New Roman"
		        size="3"><i>x</i> <u>&lt;</u> 200</font><font face="Arial" size="2"> and </font><font face="Times New Roman"
		        size="3"><i>y</i> <u>&lt;</u> 170</font><font face="Arial" size="2">. The minimum shipping requirement 
		        gives me </font><font face="Times New Roman" size="3"><i>x</i> + <i>y</i> <u>&gt;</u> 200</font><font
		        face="Arial" size="2">; in other words, </font><font face="Times New Roman" size="3"><i>y</i> 
		        <u>&gt;</u> –<i>x</i> + 200</font><font face="Arial" size="2">. The revenue relation will be 
		        my optimization equation: </font><font face="Times New Roman" size="3"><i>R</i> = –2<i>x</i> 
		        + 5<i>y</i></font><font face="Arial" size="2">. So the entire system is:</font>
			</p>
			<p>
				<font face="Times New Roman" size="3"><i>R</i> = –2<i>x</i> + 5<i>y</i>,</font><font face="Arial"
				size="2"> subject to:</font><font face="Times New Roman" size="5"> </font><font face="Arial"
				size="2"><br></font><font face="Times New Roman" size="3">100 <u>&lt;</u> <i>x</i> <u>&lt;</u> 
				200</font><font face="Times New Roman" size="5">&nbsp;</font><font face="Times New Roman" size="3"><br>80 
				<u>&lt;</u> &nbsp;<i>y</i>&nbsp;<u>&lt;</u>&nbsp;170</font><font face="Times New Roman" size="5">&nbsp;</font><font
				face="Times New Roman" size="3"><br><i>y</i> <u>&gt;</u> –<i>x</i> + 200</font><font face="Times New Roman"
				size="5">&nbsp;</font><br clear="right">
			</p>
			<p>
				The input file describing this problem is: <br>
				<br>
				max: -2x + 5y;<br>
				x &lt;= 200;<br>
				x &gt;= 100;<br>
				y &lt;= 170;<br>
				y &gt;= 80;<br>
				y &gt;= -x + 200;<br>
				<br>
				You can download the file 
				<a href="<%=request.getContextPath()%>/download/test-data/examples/linear-programming.txt" target="_blank">here</a>.
			</p>
			<p>
				After uploading the input file, 
				the system will tell that the maximum value of R = 650 at (x, y) = (100, 170).
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
