<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.invalidate();
	System.out.println("session 超时");
	//response.sendRedirect(basePath+"login.jsp");
%>
<div class="page-content-area" data-ajax-content="true">
						<!-- ajax content goes here 页面加载这里-->
					</div>
<script type="text/javascript">
	var scripts = [null, null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		window.location.href="<%=basePath%>login.jsp";
	});
</script>