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
	<title>Cluster with K-Means</title>
</head>

<body>
	<h1>K-Means Cluster Results</h1>
	<s:iterator value="clusterDataList" var="clusterData" status="rowstatus1">
		<h5>Cluster ${ rowstatus1.index }:</h5>
  		<s:iterator value="clusterData" var="clusterDataLineArray" status="rowstatus2">
  			<p>[
				<s:iterator value="clusterDataLineArray" var="item" status="rowstatus3">
  					${ item }, 
				</s:iterator>  			
  			]</p>
		</s:iterator>
	</s:iterator>
</body>
</html>
