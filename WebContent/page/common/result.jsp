<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="<%=basePath%>assets/js/jquery-1.9.1.min.js"></script>
<title>结果页面</title>
<script type="text/javascript">
	jQuery(function($) {
		alert("${map['msg']}");
	});
</script>
</head>
</html>