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

	<title>baum-welch-hmm results</title>

</head>

<body>

<h1>Generated Sequences</h1>
<p>
[
<s:iterator value="generatedSequence" var="arr1">
	[
	<s:iterator value="arr1" var="arr2">
		[
		<s:iterator value="arr2" var="num">
			${ num }, 
		</s:iterator>
		], 
	</s:iterator>
	], 
</s:iterator>
]
</p>

</body>
</html>
