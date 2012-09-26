<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'time-series.jsp' starting page</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/dygraph-combined.js"></script>

</head>

<body>

<div id="graphdiv"></div>
<script type="text/javascript">
	new Dygraph(document.getElementById("graphdiv"),
		[
			[1,10,100],
			[2,20,80],
			[3,50,60],
		],
		{
			labels: [ "x", "A", "B" ]
		});
</script>

</body>
</html>
