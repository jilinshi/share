<%@page import="com.share.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserDTO user =(UserDTO) session.getAttribute("user");
	Long oid=user.getSysOrganization().getOrgId();
%>
<title>吉林市信息共享平台</title>
<link rel="stylesheet" href="<%=basePath%>assets/plugins/myscroll.css" rel="stylesheet" type="text/css" />
<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-sm-6">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">用户组列表</h4>
				<div class="widget-toolbar">
					<i class="ace-icon fa fa-arrow-right"></i>
				</div>
			</div>
			<div class="widget-body">
			<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 290px;">
				<ul id="tasks_ug" class="item-list">
				</ul>
			</div>
			</div>
		</div>
	</div>
	<div class="col-sm-6">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">用户列表</h4>
			</div>
			<div class="widget-body">
			<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 290px;">
				<ul id="tasks_u" class="item-list">
				</ul>
			</div>
			</div>
		</div>
	</div>
	<div class="col-sm-12">
	<div class="clearfix form-actions">
		<div class="col-md-offset-4 col-md-12">
			<button class="btn btn-info" type="button">
				<i class="ace-icon fa fa-check bigger-110"></i>
				保存
			</button>

			&nbsp; &nbsp; &nbsp;
			<button class="btn" type="reset">
				<i class="ace-icon fa fa-undo bigger-110"></i>
				重置
			</button>
		</div>
	</div>
	</div>
	<script type="text/javascript">
		var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
	</script>
	<input type="hidden" name="oid" id="oid" value="<%=oid%>"/>
</div>
<script type="text/javascript">
var scripts = [null,"<%=basePath %>assets/ztree/js/jquery.ztree.core-3.5.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js", null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			var oid = $("#oid").val();
			InitUserGroup();
			InitUser(oid);
		});
	});
	function InitUserGroup(){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/queryUserGroup.action",
			async : true,
			success : function(data) {
				var myData = data.ugs;
				var li = "";
				var i = 0;
				var css = "";
				$.each(myData, function(key, val) {
					i++;
					if(i%2==1){
						css="item-orange";
					}else{
						css="item-green"
					}
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.ugId+"/><span class='lbl'> "+val.ugName+" </span></label><div class='pull-right action-buttons'><span class='vbar'></span><a href='javascript:del("+val.ugId+");'  class='red'><i class='ace-icon fa fa-trash-o bigger-130'></i></a></div></li> ";
				});
				 //置于ul中
				$("#tasks_ug").append(li);
			}
		});
	};
	function del(id){
		alert(id);
	};
	function InitUser(orgid){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/queryUser.action",
			async : true,
			data :{orgId:orgid},
			success : function(data) {
				var myData = data.ugs;
				var li = "";
				var i = 0;
				var css = "";
				$.each(myData, function(key, val) {
					i++;
					if(i%2==1){
						css="item-orange";
					}else{
						css="item-green"
					}
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.userId+"/><span class='lbl'> "+val.uname+" </span></label></li> ";
				});
				 //置于ul中
				$("#tasks_u").append(li);
			}
		});
	};
</script>