<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	if (session.getAttribute("user") != null) {
		session.removeAttribute("user");
	}
	session.invalidate();
%>
<script type="text/javascript">
	window.jQuery
	|| document.write("<script src='<%=basePath%>assets/js/jquery.js'>"+"<"+"/script>");
</script>
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='<%=basePath%>assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
</script>
<script type="text/javascript">
	var scripts = [null, null]
	jQuery(function($) {
		if(window.opener!=null){
			window.opener.close();
		}
		window.open('<%=basePath%>login.jsp', '吉林市社会救助局信息共享平台');
		window.open('', '_self');
	});
	<%-- $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		alert("11");
		 window.opener = null;

		window.open('<%=basePath%>login.jsp', '吉林市社会救助局信息共享平台');
		  window.open('', '_self');
		  //window.close();


	}); --%>
</script>