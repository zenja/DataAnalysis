<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>
		QR Marks The Spot - <decorator:title default="Welcome" />
	</title>
	
    <link href="<%=request.getContextPath() %>/css/decorator_default.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath() %>/css/common_message.css" rel="stylesheet" type="text/css" />
	<link rel="shortcut icon" href="<%=request.getContextPath() %>/images/favicon.ico" />
	
	<decorator:head />
	
</head>

<decorator:usePage id="usePage" />

<body onload="<%=usePage.getProperty("body.onload")%>"
      onunload="<%=usePage.getProperty("body.onunload")%>">


	<decorator:body /> <%-- 被装饰页面插入在这里 --%>

</body>
</html>