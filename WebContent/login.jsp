<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String token=request.getParameter("token");
response.sendRedirect(basePath+"login/login.jsp?token="+token);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base>
<title>吉林市社会救助局信息共享平台</title>
<link rel="shortcut icon" href="/favicon.ico">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="images/style.css" />
</head>
<body>
	<form id="login-form"
		action="${pageContext.request.contextPath}/login.action" method="post">
		<input type="hidden" name="token"
			value="<%=request.getParameter("token")%>" />
		<fieldset>
			<legend>登陆</legend>
			<label for="login">用户名:</label> <input type="text" id="username"
				name="username" />
			<div class="clear"></div>
			<label for="password">密 码:</label> <input type="password"
				id="password" name="password" />
			<div class="clear"></div>
			<label for="remember_me" style="padding: 0;">记住我?</label> <input
				type="checkbox" id="remember_me"
				style="position: relative; top: 0px; margin: 0;" name="remember_me" />
			<div class="clear"></div>
			<br /> <input type="submit" style="margin: -20px 0 0 287px;"
				class="button" name="commit" value="登录" />
		</fieldset>
	</form>
	<p align="center">
		<strong>&copy; 吉林明达软件有限责任公司</strong>
	</p>
</body>
</html>
