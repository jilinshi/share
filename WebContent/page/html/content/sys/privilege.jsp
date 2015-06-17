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
<link rel="stylesheet" href="<%=basePath%>assets/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
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
					<div class="btn-group">
					权限名称:
					<input type="text" style="height:29px;" name="priviname" id="priviname" placeholder="请填写......" class="input-medium" />
					权限编码:
					<input type="text" style="height:29px;" name="privicode" id="privicode" placeholder="请填写......" class="input-medium" />
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
				<!-- <li>
					<a data-toggle="tab" id="tab3" href="#function" onclick="addtab(this);">功能</a>
				</li>
				<li>
					<a data-toggle="tab" id="tab4" href="#file" onclick="addtab(this);">文件</a>
				</li> -->
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
						<div class="zTreeDemoBackground left">
							<ul id="treeDemo_menus" class="ztree"></ul>
						</div>
					</div>
				</div>
				<!-- <div id="function" class="tab-pane" style="margin: -15px -13px;">
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
				</div> -->
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
var scripts = [null,"<%=basePath %>assets/ztree/js/jquery.ztree.core-3.5.js","<%=basePath %>assets/ztree/js/jquery.ztree.excheck-3.5.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js", null ]
	var roleids="";
	var menuids="";
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			var oid = $("#oid").val();
			InitPrivilege();
			InitRole(roleids);
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
					li += " <li class='"+css+"'><label class='inline'><input type='radio' class='ace' id="+val.privilegeId+" name='privilege_radio' value='"+val.privilegeId+"'  onclick='privilege_check(this)'/><span class='lbl'> "+val.priviname+" </span></label><div class='pull-right action-buttons'><span class='vbar'></span><a href='javascript:del("+val.privilegeId+");'  class='red'><i class='ace-icon fa fa-trash-o bigger-130'></i></a></div></li> ";
				});
				 //置于ul中
				$("#tasks_privilege").append(li);
			}
		});
	};
	 $('#create').click(function () {
		var priviname = $("#priviname").val();
		var privicode = $("#privicode").val();
		if(priviname==""){
			alert("请填写权限名称！");
			return;
		}
		if(privicode==""){
			alert("请填写权限编码！");
			return;
		}
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/addPrivilege.action",
			async : false,
			data :{priviname:priviname,privicode:privicode},
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
			url : "<%=basePath%>page/html/content/sys/delPrivilege.action",
			async : false,
			data :{privilegeId:id},
			success : function(data) {
				var msg = data.msg;
				alert(msg);
				$("#tasks_privilege").children().filter('li').remove();
				InitPrivilege();
			}
		});
	};
	var tabid="tab1";
	function addtab(tab){
		tabid = tab.id;
		$("#tasks_privilege").children().filter('li').remove();
		$("#tasks_role").children().filter('li').remove();
		$("#tasks_menus").children().filter('li').remove();
		/* $("#tasks_function").children().filter('li').remove();
		$("#tasks_file").children().filter('li').remove(); */
		if(tabid=='tab1'){
			InitRole(roleids);
			InitPrivilege();
		}else if(tabid=='tab2'){
			InitMenus(menuids);
			InitPrivilege();
		}/* else if(tabid=='tab3'){
			InitFunction();
		}else if(tabid=='tab4'){
			InitFile();
		} */
	};
	
	function privilege_check(e){
		var privilegeid = e.id;
		if(tabid=='tab1'){
			$.ajax({
				type : "post",
				dataType : "json",
				url : "<%=basePath%>page/html/content/sys/queryRpChecked.action",
				async : false,
				data :{privilegeId:privilegeid},
				success : function(data) {
					var rps = data.rps;
					var ids = "";
					for(var i=0;i<rps.length;i++){
						if(i==rps.length-1){
							ids+=rps[i].roleId;
						}else{
							ids+=rps[i].roleId+"-";
						}
					}
					$("#tasks_role").children().filter('li').remove();
					InitRole(ids);
				}
			});
		}else if(tabid=='tab2'){
			$.ajax({
				type : "post",
				dataType : "json",
				url : "<%=basePath%>page/html/content/sys/queryMpChecked.action",
				async : false,
				data :{privilegeId:privilegeid},
				success : function(data) {
					var mps = data.mps;
					var ids = "";
					for(var i=0;i<mps.length;i++){
						if(i==mps.length-1){
							ids+=mps[i].menuId;
						}else{
							ids+=mps[i].menuId+"-";
						}
					}
					InitMenus(ids);
				}
			});
		}
	};
	
	function InitRole(roleids){
		var ids = roleids.split('-');
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
					var checked="";
					for(var i=0; i<ids.length; i++){
						if(ids[i]==val.roleId){
							checked='checked';
						}
					}
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' "+ checked +" class='ace' id="+val.roleId+" name='role_check' value='"+val.roleId+"'/><span class='lbl'> "+val.rolename+" </span></label></li> ";
				});
				 //置于ul中
				$("#tasks_role").append(li);
			}
		});
	};
	function InitMenus(menuids){	
		var ids = menuids.split('-');
		var setting = {
			 check: {
				enable: true
			 },
			 data: {
				simpleData: {
					enable: true
				}
			 },
			 callback: {
					//onClick: zTreeOnClick_org
			 }
		};
		var zNodes =[
		];
		$.ajax({ 
	            type: "post", 
	            url: "<%=basePath%>page/html/content/sys/queryMenus.action",
				dataType : "json",
				success : function(data) {
					var zNodes = data.rs;
					$.fn.zTree.init($("#treeDemo_menus"), setting, zNodes);
					var treeObj = $.fn.zTree.getZTreeObj("treeDemo_menus");
					for (var i=0; i<zNodes.length;  i++) {
						for(var j=0; j<ids.length; j++){
							if(ids[j]==zNodes[i].id){
								var node = treeObj.getNodeByTId(zNodes[i].id);
								treeObj.checkNode(node, true, false);

							}
						}
					}

					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
		});
	};
	<%-- function InitFunction(){
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
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.functionId+" name='function_check' value='"+val.functionId+"'/><span class='lbl'> "+val.funcname+" </span></label></li> ";
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
					li += " <li class='"+css+"'><label class='inline'><input type='checkbox' class='ace' id="+val.fileId+" name='file_check' value='"+val.fileId+"'/><span class='lbl'> "+val.filename+" </span></label></li> ";
				});
				 //置于ul中
				$("#tasks_file").append(li);
			}
		});
	}; --%>

	function sub(){
		
		var privilegeid = $("input[name='privilege_radio']:checked").val();
		//角色数
		var str_role_len = $("input[name='role_check']:checkbox:checked").size();
		
/* 		//功能数
		var str_function_len = $("input[name='function_check']:checkbox:checked").size();
		//文件数
		var str_file_len = $("input[name='file_check']:checkbox:checked").size(); */
		
		//角色
		var roleids="";
		var ri=0;
		$("input[name='role_check']:checkbox:checked").each(function(){ 
			ri++;
			if(ri==str_role_len){
				roleids+=$(this).val();
			}else{
				roleids+=$(this).val()+",";
			}
		});
		//菜单
		var menuids="";
		/* //功能
		var functionids="";
		var fi=0;
		$("input[name='function_check']:checkbox:checked").each(function(){ 
			fi++;
			if(fi==str_function_len){
				functionids+=$(this).val();
			}else{
				functionids+=$(this).val()+",";
			}
		});
		//文件
		var fileids="";
		var fii=0;
		$("input[name='file_check']:checkbox:checked").each(function(){ 
			fii++;
			if(fii==str_file_len){
				fileids+=$(this).val();
			}else{
				fileids+=$(this).val()+",";
			}
		}); */
		if(privilegeid==""){
			alert("请选择权限！");
			return;
		}
		if(tabid=='tab1'){
			//角色
			if(str_role_len==0){
				alert("请选择角色！");
				return;
			}
		}else if(tabid=='tab2'){
			//菜单数
			var zTree = $.fn.zTree.getZTreeObj("treeDemo_menus");
			var str_menus_len = zTree.getCheckedNodes(true).length;
			alert(str_menus_len);
			var nodes = zTree.getCheckedNodes(true);
			for(var i=0;i<nodes.length;i++){
				if(i==str_menus_len-1){
					menuids+=nodes[i].id;
				}else{
					menuids+=nodes[i].id + ",";
				}
	         }
			/* //菜单
			if(str_menus_len==0){
				alert("请选择菜单！");
				return;
			} */
		}/* else if(tabid=='tab3'){
			//功能
			if(str_function_len==0){
				alert("请选择功能！");
				return;
			}
			
		}else if(tabid=='tab4'){
			//文件
			if(str_file_len==0){
				alert("请选择文件！");
				return;
			}
		}  */
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/sys/savePriRelation.action",
			async : true,
			data :{privilegeId:privilegeid,roleIds:roleids,menuIds:menuids},
			success : function(data) {
				var msg = data.msg;
				alert(msg);
			}
		});
	};
</script>