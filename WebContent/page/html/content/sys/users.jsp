<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>吉林市信息共享平台</title>
<link rel="stylesheet" href="<%=basePath %>assets/plugins/jqx/styles/jqx.base.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>assets/plugins/jqx/styles/jqx.energyblue.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>assets/plugins/style.css" rel="stylesheet" type="text/css" />
<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-sm-12">
		<div class="widget-box widget-color-blue2">
				<div class="widget-header">
					<h4 class="widget-title lighter smaller">用户列表</h4>
				</div>
				<div class="widget-body" >
					<div class="widget-main padding-8">
					
					</div>
				</div>
		</div>
	</div>
	<script type="text/javascript">
			var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
	</script>
</div>
<script type="text/javascript">
var scripts = [null,"<%=basePath %>assets/plugins/jqx/jqxcore.js","<%=basePath %>assets/plugins/jqx/jqxexpander.js","<%=basePath %>assets/plugins/jqx/jqxbuttons.js","<%=basePath %>assets/plugins/jqx/jqxscrollbar.js","<%=basePath %>assets/plugins/jqx/jqxtree.js","<%=basePath %>assets/plugins/jqx/jqxcheckbox.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js", null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			
		});
	});

</script>