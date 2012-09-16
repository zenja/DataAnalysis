<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<title>手风琴效果 </title> 
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/multi-menu.css" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/multi-menu.js"></script> 
<script type="text/javascript"> 
//加载效果
window.onload = function(){
	ex = new AccControl("accordion_panel1");
};
</script> 
</head> 
<body> 
<div id="accordion_panel1" class="panel"> 
	<h3 class="gray-top">Top Downloads</h3>	
	<div> 
		<ol> 
			<li>iTunes 7.6.2</li> 
			<li>QuickTime 7.5</li> 
			<li>Safari 3.1.1</li> 
			<li>Messenger for Mac</li> 
			<li>Mozilla Firefox</li> 
			<li>Deluxe PocMon Mac</li> 
			<li>Countdown Maker</li> 
			<li>Smilebox</li> 
			<li>Oscillating Rhythms</li> 
		</ol> 
	</div> 
	<h3 class="gray">Virtual Panda</h3> 
	<div>	
		<ol> 
			<li>iTunes 7.6.2</li> 
			<li>QuickTime 7.5</li> 
			<li>Safari 3.1.1</li> 
			<li>Messenger for Mac</li> 
			<li>Mozilla Firefox</li> 
			<li>Deluxe PocMon Mac</li> 
			<li>Countdown Maker</li> 
			<li>Smilebox</li> 
			<li>Oscillating Rhythms</li> 
		</ol> 
	</div> 
	<h3 class="gray">Google News</h3> 
	<div>	
		<ol> 
			<li>iTunes 7.6.2</li> 
			<li>QuickTime 7.5</li> 
			<li>Safari 3.1.1</li> 
			<li>Messenger for Mac</li> 
			<li>Mozilla Firefox</li> 
			<li>Deluxe PocMon Mac</li> 
			<li>Countdown Maker</li> 
			<li>Smilebox</li> 
			<li>Oscillating Rhythms</li> 
		</ol> 
	</div> 
	<h3 class="deep">Monkey Mirror</h3> 
	<div class="panel-bottom">	
		<ol> 
			<li>iTunes 7.6.2</li> 
			<li>QuickTime 7.5</li> 
			<li>Safari 3.1.1</li> 
			<li>Messenger for Mac</li> 
			<li>Mozilla Firefox</li> 
			<li>Deluxe PocMon Mac</li> 
			<li>Countdown Maker</li> 
			<li>Smilebox</li> 
			<li>Oscillating Rhythms</li> 
		</ol> 
	</div> 
	<h3 class="deep-bottom">&nbsp;</h3> 
</div> 
 
</body> 
</html> 