<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>吉林市社会救助局信息共享平台</title>

<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		 
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<!-- page specific plugin scripts
window.open('<%=basePath%>login.jsp', '吉林市社会救助局信息共享平台');
 -->
<script type="text/javascript">
	var scripts = [ null, null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	});
</script>
