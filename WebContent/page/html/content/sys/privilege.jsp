<%@page import="com.share.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserDTO user =(UserDTO) session.getAttribute("user");
	Long oid=user.getOrgId();
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
				<h4 class="widget-title lighter smaller">权限列表</h4>
				<div class="widget-toolbar">
				</div>
			</div>
			<div class="widget-body">
			<div class="widget-toolbox" id="widget-toolbox-1">
				<div class="btn-toolbar">
					<div class="btn-group">权限名称：
					<input type="text" style="height:29px;" name="roleName" id="roleName" placeholder="请填写......" class="input-xlarge" />
					</div>
					<div class="btn-group" >
						<button class="btn btn-warning btn-sm btn-round" id="create">
							<i class="ace-icon fa fa-plus-circle"></i> 新建权限
						</button>
					</div>
				</div>
			</div>
			<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 290px;">
				<ul id="tasks_privilege" class="item-list">
				</ul>
			</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-6">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller"> （角色/菜单/功能/文件）列表</h4>
			</div>
			<div class="widget-body">
			<div class="tabbable">
			<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
				<li class="active">
					<a data-toggle="tab" id="tab1" href="#role" onclick="addtab(this);">角色</a>
				</li>
				<li>
					<a data-toggle="tab" id="tab2" href="#menus" onclick="addtab(this);">菜单</a>
				</li>
				<li>
					<a data-toggle="tab" id="tab3" href="#function" onclick="addtab(this);">功能</a>
				</li>
				<li>
					<a data-toggle="tab" id="tab4" href="#file" onclick="addtab(this);">文件</a>
				</li>
			</ul>
			<div class="tab-content" >
				<div id="role" class="tab-pane in active" style="margin: -15px -13px;">
					<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 280px;">
						<ul id="tasks_role" class="item-list">
						</ul>
					</div>
				</div>
				<div id="menus" class="tab-pane" style="margin: -15px -13px;">
					<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 280px;">
						<ul id="tasks_menus" class="item-list">
						</ul>
					</div>
				</div>
				<div id="function" class="tab-pane" style="margin: -15px -13px;">
					<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 280px;">
						<ul id="tasks_function" class="item-list">
						</ul>
					</div>
				</div>
				<div id="file" class="tab-pane" style="margin: -15px -13px;">
					<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 280px;">
						<ul id="tasks_file" class="item-list">
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
			InitPrivilege();
			InitRole();
		});
	});
	function InitPrivilege(){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/queryPrivilege.action",
			async : false,
			success : function(data) {
				var myData = data.ps;
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
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.privilegeId+" name='privilege_checkbox' value='"+val.privilegeId+"'/><span class='lbl'> "+val.priviname+" </span></label><div class='pull-right action-buttons'><span class='vbar'></span><a href='javascript:del("+val.privilegeId+");'  class='red'><i class='ace-icon fa fa-trash-o bigger-130'></i></a></div></li> ";
				});
				 //置于ul中
				$("#tasks_privilege").append(li);
			}
		});
	};
	 $('#create').click(function () {
		var roleName = $("#roleName").val();
		if(roleName==""){
			alert("请填写角色名称！");
			return;
		}
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
	var tabid="tab1";
	function addtab(tab){
		tabid = tab.id;
		$("#tasks_role").children().filter('li').remove();
		$("#tasks_menus").children().filter('li').remove();
		$("#tasks_function").children().filter('li').remove();
		$("#tasks_file").children().filter('li').remove();
		if(tabid=='tab1'){
			InitRole();
		}else if(tabid=='tab2'){
			InitMenus();
		}else if(tabid=='tab3'){
			InitFunction();
		}else if(tabid=='tab4'){
			InitFile();
		}
	};
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
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.roleId+" name='user_check' value='"+val.roleId+"'/><span class='lbl'> "+val.rolename+" </span></label></li> ";
				});
				 //置于ul中
				$("#tasks_role").append(li);
			}
		});
	};
	function InitMenus(){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/queryMenus.action",
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
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.menuId+" name='user_check' value='"+val.menuId+"'/><span class='lbl'> "+val.menuname+" </span></label></li> ";
				});
				 //置于ul中
				$("#tasks_menus").append(li);
			}
		});
	};
	function InitFunction(){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/queryFunctions.action",
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
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.functionId+" name='user_check' value='"+val.functionId+"'/><span class='lbl'> "+val.funcname+" </span></label></li> ";
				});
				 //置于ul中
				$("#tasks_function").append(li);
			}
		});
	};
	function InitFile(){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/queryFiles.action",
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
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.fileId+" name='user_check' value='"+val.fileId+"'/><span class='lbl'> "+val.filename+" </span></label></li> ";
				});
				 //置于ul中
				$("#tasks_file").append(li);
			}
		});
	};
	function sub(){
		//角色数
		var str_role_len = $("input[name='role_checkbox']:checkbox:checked").size();
		//用户数
		var str_u_len = $("input[name='user_check']:checkbox:checked").size();
		//用户组数
		var str_ug_len = $("input[name='usergroup_check']:checkbox:checked").size();
		
		//alert(tabid);
		//alert(str_role_len+"--"+str_u_len+"--"+str_ug_len);

		//角色
		var roleids="";
		var ri=0;
		$("input[name='role_checkbox']:checkbox:checked").each(function(){ 
			ri++;
			if(ri==str_role_len){
				roleids+=$(this).val();
			}else{
				roleids+=$(this).val()+",";
			}
		});
		//用户
		var userids="";
		var ui=0;
		$("input[name='user_check']:checkbox:checked").each(function(){ 
			ui++;
			if(ui==str_u_len){
				userids+=$(this).val();
			}else{
				userids+=$(this).val()+",";
			}
		});
		//用户组
		var usergroudids="";
		var ugi=0;
		$("input[name='usergroup_check']:checkbox:checked").each(function(){ 
			ugi++;
			if(ugi==str_ug_len){
				usergroudids+=$(this).val();
			}else{
				usergroudids+=$(this).val()+",";
			}
		});
		
		if(str_role_len==0){
			alert("请选择角色！");
			return;
		}
		if(tabid=='tab1'){
			//用户组
			if(str_ug_len==0){
				alert("请选择用户组！");
				return;
			}
		}else if(tabid=='tab2'){
			//用户
			if(str_u_len==0){
				alert("请选择用户！");
				return;
			}
		}
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/saveRoleRelation.action",
			async : true,
			data :{roleIds:roleids,userIds:userids,usergroudIds:usergroudids},
			success : function(data) {
				var msg = data.msg;
				alert(msg);
			}
		});
	};
</script>