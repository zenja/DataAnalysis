<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>
			Wuxi Project - <decorator:title default="Welcome" />
		</title>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/decorator_default.css"
			media="screen" />
			
		<decorator:head />
		
	</head>

	<decorator:usePage id="usePage" />

	<body onload="<%=usePage.getProperty("body.onload")%>"
      onunload="<%=usePage.getProperty("body.onunload")%>">
		<div id="main_container">
			<div class="center_content">
				<div id="header">
					<div id="logo">
						<a href="index.html"><img src="images/logo.gif" alt=""
								title="" border="0" />
						</a>
					</div>

					<div id="menu">
						<ul>
							<li>
								<a class="current" href="index.html" title="">home</a>
							</li>
							<li>
								<a href="services.html" title="">about cantya</a>
							</li>
							<li>
								<a href="services.html" title="">development</a>
							</li>
							<li>
								<a href="services.html" title="">solutions</a>
							</li>
							<li>
								<a href="services.html" title="">testimonials</a>
							</li>
							<li>
								<a href="contact.html" title="">contact us</a>
							</li>
						</ul>
					</div>


				</div>


				<div class="main_content">
					<div class="main_content_top"></div>



					<decorator:body /> <%-- 被装饰页面插入在这里 --%>



					<div class="clear"></div>
				</div>
				<!--end of main content-->


				<div id="footer">
					<div class="copyright">
						<a href="http://software.nju.edu.cn">By SWI_NJU</a>
					</div>
					<div class="footer_links">
						<a href="#">About us</a>
						<a href="#">Privacy policy</a>
						<a href="#">Contact us </a>

					</div>


				</div>
			</div>
		</div>
	</body>
</html>
