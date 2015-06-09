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
	<form class="form-horizontal" id="myForm" method="post" >
	<div class="col-sm-12">
	<div class="col-xs-12 col-sm-6">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">角色列表</h4>
				<div class="widget-toolbar">
				</div>
			</div>
			<div class="widget-body">
			<div class="widget-toolbox" id="widget-toolbox-1">
				<div class="btn-toolbar">
					<div class="btn-group">
					<input type="text" style="height:29px;" name="roleName" id="roleName" placeholder="请填写......" class="col-xs-15" />
					</div>
					<div class="btn-group">
						<button class="btn btn-warning btn-sm btn-round" id="create">
							<i class="ace-icon fa fa-plus-circle"></i> 新建角色
						</button>
					</div>
				</div>
			</div>
			<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 290px;">
				<ul id="tasks_role" class="item-list">
				</ul>
			</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-6">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller"> （用户组 /用户）列表</h4>
			</div>
			<div class="widget-body">
			<div class="tabbable">
			<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
				<li class="active">
					<a data-toggle="tab" id="tab1" href="#usergroup" onclick="addtab(this);">用户组</a>
				</li>
				<li>
					<a data-toggle="tab" id="tab2" href="#user" onclick="addtab(this);">&nbsp;用&nbsp;&nbsp;户&nbsp;</a>
				</li>
			</ul>
			<div class="tab-content" >
				<div id="usergroup" class="tab-pane in active" style="margin: -15px -13px;">
					<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 280px;">
						<ul id="tasks_uergroup" class="item-list">
						</ul>
					</div>
				</div>
				<div id="user" class="tab-pane" style="margin: -15px -13px;">
					<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 280px;">
						<ul id="tasks_u" class="item-list">
						</ul>
					</div>
				</div>
			</div>
			</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12">
		<div class="clearfix form-actions">
		<div class="col-md-offset-4 col-md-6">
			<button class="btn btn-info" type="button" onclick="sub();">
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
	</div>
	</form>
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
			InitRole();
			InitUserGroup();
		});
	});
	function InitRole(){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/queryRole.action",
			async : false,
			success : function(data) {
				var myData = data.rs;
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
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.roleId+" name='role_checkbox' value='"+val.roleId+"'/><span class='lbl'> "+val.rolename+" </span></label><div class='pull-right action-buttons'><span class='vbar'></span><a href='javascript:del("+val.roleId+");'  class='red'><i class='ace-icon fa fa-trash-o bigger-130'></i></a></div></li> ";
				});
				 //置于ul中
				$("#tasks_role").append(li);
			}
		});
	};
	 $('#create').click(function () {
		var roleName = $("#roleName").val();
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/addRole.action",
			async : false,
			data :{roleName:roleName},
			success : function(data) {
				var msg = data.msg;
				alert(msg);
			}
		});
	 });
	function del(id){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/delRole.action",
			async : false,
			data :{roleId:id},
			success : function(data) {
				var msg = data.msg;
				alert(msg);
				$("#tasks_role").children().filter('li').remove();
				InitRole();
			}
		});
	};
	function addtab(tab){
		var tabid = tab.id;
		if(tabid=='tab1'){
			$("#tasks_uergroup").children().filter('li').remove();
			InitUserGroup();
		}else if(tabid=='tab2'){
			$("#tasks_u").children().filter('li').remove();
			InitUser();
		}
	};
	function InitUser(){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/queryUserAll.action",
			async : false,
			success : function(data) {
				var myData = data.us;
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
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.userId+" name='user_check' value='"+val.userId+"'/><span class='lbl'> "+val.uname+" </span></label></li> ";
				});
				 //置于ul中
				$("#tasks_u").append(li);
			}
		});
	};
	function InitUserGroup(){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/queryUserGroup.action",
			async : false,
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
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.ugId+" name='user_check' value='"+val.ugId+"'/><span class='lbl'> "+val.ugName+" </span></label></li> ";
				});
				 //置于ul中
				$("#tasks_uergroup").append(li);
			}
		});
	};
	function sub(){
		//用户组
		var ugid = $("input[name='usergroup_radio']:checked").val();
		var str_u_len = $("input[name='user_check']:checkbox:checked").size();
		//用户
		var userids="";
		var i=0;
		$("input[name='user_check']:checkbox:checked").each(function(){ 
			i++;
			if(i==str_u_len){
				userids+=$(this).val();
			}else{
				userids+=$(this).val()+",";
			}
		});
		if(ugid==""){
			alert("请选择用户组！");
			return;
		}
		if(str_u_len==0){
			alert("请选择用户！");
			return;
		}
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/saveUGRelation.action",
			async : true,
			data :{ugId:ugid,userIds:userids},
			success : function(data) {
				var msg = data.msg;
				alert(msg);
			}
		});
	};
</script>