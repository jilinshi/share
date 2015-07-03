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
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>吉林市信息共享平台</title>
<link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/chosen.css" />
<link rel="stylesheet" href="<%=basePath%>assets/plugins/myscroll.css" rel="stylesheet" type="text/css" />
<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-sm-12">
	<div class="widget-box widget-color-orange">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller"> 菜单列表</h4>
			</div>
			<div class="widget-body" style="margin:10px; height:450px;">
			<div class="widget-toolbox" id="widget-toolbox-1" style="margin:-10px -10px 10px -10px;">
				<div class="btn-toolbar">
					<div class="btn-group">
						<button class="btn btn-sm btn-success btn-white btn-round" id="ExpandAll_list">
							<i class="ace-icon fa fa-plus green"></i>全部展开
						</button>
					</div>
					<div class="btn-group">
						<button class="btn btn-sm btn-success btn-white btn-round" id="CollapseAll_list">
							<i class="ace-icon fa fa-minus green"></i>全部合并
						</button>
					</div>
					<div class="btn-group">
						<button class="btn btn-sm btn-info btn-success btn-round" id="create_menu">
							<i class="ace-icon fa fa-plus-circle white"></i>新建菜单
						</button>
					</div>
					<div class="btn-group">
						<button class="btn btn-sm btn-info btn-success btn-round" id="reload_menu">
							<i class="ace-icon fa fa-undo white"></i>刷新菜单
						</button>
					</div>
				</div>
			</div>
		<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 400px;">
		<div class="dd dd-draghandle" id="mydrag">
			<ol class="dd-list" id="menulist">
				<!-- <li class="dd-item dd2-item" data-id="13">
					<div class="dd-handle dd2-handle">
						<i class="normal-icon ace-icon fa fa-comments blue bigger-130"></i>

						<i class="drag-icon ace-icon fa fa-arrows bigger-125"></i>
					</div>
					<div class="dd2-content">Click on an icon to start dragging
					<div class='pull-right action-buttons'><a class='blue' href='#'><i class='ace-icon fa fa-pencil bigger-130'></i></a><a class='red' href='#'><i class='ace-icon fa fa-trash-o bigger-130'></i></a></div>
					</div>
				</li> -->
			</ol>
		</div>
		</div>
		</div>
		</div>
	</div>
	<div id="dialog-confirm" class="hide">
		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" id="myForm_menu" method="post" action="<%=basePath%>page/html/content/sys/saveMenu.action" target="tempiframe" >
					<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="select_pmid"> 父菜单名称 </label>
						<div class="col-sm-9">
							<select style="width:275px;" id="select_pmid" name="menuDTO.pmId" data-placeholder="请选择 . . ." >
							</select>
						</div>
					</div>
					<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="menuname"> 菜单名称 </label>
						<div class="col-sm-9">
							<input type="text" name="menuDTO.menuname" id="menuname" placeholder="请填写......" class="col-xs-10 col-sm" />
						</div>
					</div>
					<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="menucode"> 菜单编码 </label>
						<div class="col-sm-9">
							<input type="text" id="menucode" placeholder="" class="col-xs-10 col-sm" disabled/>
							<input type="hidden" name="menuDTO.menucode" id="menucode_hidden" placeholder="请填写......" class="col-xs-10 col-sm" />
							<input type="hidden" name="menuDTO.menuId" id="menuId_hidden" placeholder="请填写......" class="col-xs-10 col-sm" />
						</div>
					</div>
					<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="menuurl"> 菜单连接 </label>
						<div class="col-sm-9">
							<input type="text" name="menuDTO.menuurl" id="menuurl" placeholder="请填写全路径......" class="col-xs-10 col-sm" />
						</div>
					</div>
					<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="ico"> 图标 </label>
						<div class="col-sm-9">
							<input type="text" name="menuDTO.ico" id="ico" placeholder="请填写......" class="col-xs-10 col-sm" />
						</div>
					</div>
					<div class="form-group">
					<label class="col-sm-3 control-label no-padding-right" for="remark"> 备注 </label>
						<div class="col-sm-9">
							<input type="text" name="menuDTO.remark" id="remark" placeholder="请填写......" class="col-xs-10 col-sm" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
	</script>
</div>
<iframe id="tempiframe" name="tempiframe" width="0" height="0" style="overflow: hidden;"></iframe>
<script src="<%=basePath%>assets/js/jquery-ui.js"></script>
<script src="<%=basePath%>assets/js/chosen.jquery.js"></script>
<script src="<%=basePath%>assets/js/jquery.nestable.js"></script>
<script type="text/javascript">
var scripts = [null, null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			InitList();
			$('.dd').nestable();
		});
	});
// Expand All list
$('#ExpandAll_list').click(function () {
	$('.dd').nestable('expandAll');
});

// Collapse All list
$('#CollapseAll_list').click(function () {
	$('.dd').nestable('collapseAll');
});

function chosen_Init(){
	$.ajax({
		type : "post",
		dataType : "json",
		async:  false,
		url : "<%=basePath%>page/html/content/sys/queryMenus.action",
		success : function(data) {
			var myData = data.rs;
			var len = myData.length;
			var a = " ";
			for(var i=0; i<len; i++){
				var value = myData[i].id;
				var text = myData[i].name+"__"+myData[i].genId+"";
				a = a + "<option value='"+ value +"'>"+ text +"</option>";
			}
			
			$("#select_pmid").append("<option value='0'></option><option value='-1'>根目录</option>").append(a);
			//$("#select_pmid").chosen({allow_single_deselect:true});
			
		}
	});
};

var dialog = $("#dialog-confirm").removeClass('hide').dialog({
	autoOpen: false,//如果设置为true，则默认页面加载完毕后，就自动弹出对话框；相反则处理hidden状态。 
    bgiframe: true, //解决ie6中遮罩层盖不住select的问题  
	hide:true,
	resizable: true,
	width: '480',
	modal: true,
	title: "编辑菜单",
	buttons: [ 
				{
					text: "关闭",
					"class" : "btn btn-minier",
					click: function() {
						$("#select_pmid").chosen("destroy"); 
						document.getElementById("myForm_menu").reset();
						$( this ).dialog( "close" );
					} 
				},
				{
					text: "保存",
					"class" : "btn btn-primary btn-minier",
					click: function() {
						var pmId=$("#select_pmid").children('option:selected').val();
						var menuname = $("#menuname").val();
						var menuurl = $("#menuurl").val();
						var ico = $("#ico").val();
						var remark = $("#remark").val();
						if(pmId=="0"){
							alert("请选择父菜单");
							return false;
						}
						if(menuname==""){
							alert("请填写菜单名称！");
							return false;
						}
						if(menuurl==""){
							alert("请填写菜单连接（页面全路径）！");
							return false;
						}
						document.getElementById("myForm_menu").submit();
						dialog.dialog("close");
						// parent.location.reload();
					} 
				}
			]
});

$('#create_menu').click(function (){
	dialog.dialog("open");
	chosen_Init();
	document.getElementById("myForm_menu").reset();
	$("#menuId_hidden").val(0);
	$('#menuurl').attr("readonly",false);
	$("#select_pmid option[value='0']").attr("selected",true);
	$("#select_pmid").chosen();
});

$('#reload_menu').click(function (){
	$("#menulist").children().filter('li').remove();
	InitList();
});

function menu_edit(id){
	 $.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : "<%=basePath%>page/html/content/sys/querymenubyid.action",
		data : {menuId:id},
		success : function(data) {
			var m = data.sm;
			//$("#select_pmid option[value='"+m.pmId+"']").attr("selected","selected");
			//$("#select_pmid").val(m.pmId);
			$("#menuId_hidden").val(m.menuId);
			$("#menuname").val(m.menuname);
			$("#menuurl").val(m.menuurl);
			$("#ico").val(m.ico);
			$("#remark").val(m.remark);
			$("#menucode_hidden").val(m.menucode);
			$("#menucode").val(m.menucode);
			dialog.dialog("open");
			chosen_Init();
			if(m.menuurl=='#'){
				$('#menuurl').attr("readonly","readonly");				
			}else{
				$('#menuurl').attr("readonly",false);	
			}
			$("#select_pmid option[value='"+m.pmId+"']").attr("selected","selected");
			$("#select_pmid").chosen();
		}
	});
	
};

function InitList(){
	$.ajax({
		type : "post",
		dataType : "json",
		url : "<%=basePath%>page/html/content/sys/queryMenus.action",
		async : false,
		success : function(data) {
			var myData = data.rs;
			var li = "";
			$.each(myData, function(key, val) {
				var v =  val.genId;
				if(v.length==4){
					li += " <li class='dd-item dd2-item' data-id='"+val.genId+"'><div class='dd-handle dd2-handle dd-colored'><i class='"+val.ico+" bigger-130'></i><i class='drag-icon ace-icon fa fa-arrows bigger-125'></i></div><div class='dd2-content btn-info'>"+val.name+
					"<div class='pull-right action-buttons'><a class='white' href='javascript:menu_edit("+val.id+");'><i class='ace-icon fa fa-pencil bigger-130'></i></a><a class='white' href='javascript:menu_del("+val.id+");'><i class='ace-icon fa fa-trash-o bigger-130'></i></a></div>"+
					"</div><ol class='dd-list' id='ol_0-"+val.id+"'></ol></li>";
				}
			});
			 //置于ol中
			$("#menulist").append(li);
			 //设置二级菜单
			$.each(myData, function(key, val) {
				var v =  val.genId;
				if(v.length==8){
					var cli = " <li class='dd-item dd2-item' data-id='"+val.genId+"'><div class='dd-handle dd2-handle'><i class='"+val.ico+" bigger-130'></i><i class='drag-icon ace-icon fa fa-arrows bigger-125'></i></div><div class='dd2-content'>"+val.name+
					"<div class='pull-right action-buttons'><a class='blue' href='javascript:menu_edit("+val.id+");'><i class='ace-icon fa fa-pencil bigger-130'></i></a><a class='red' href='javascript:menu_del("+val.id+");'><i class='ace-icon fa fa-trash-o bigger-130'></i></a></div>"+
					"</div><ol class='dd-list' id='ol_1-"+val.id+"'></ol></li> ";
					$("#ol_0-"+val.pId).append(cli);
				}
				
			}); 
			//设置三级菜单
			$.each(myData, function(key, val) {
				var v =  val.genId;
				if(v.length==12){
					var cli = " <li class='dd-item dd2-item' data-id='"+val.genId+"'><div class='dd-handle dd2-handle'><i class='"+val.ico+" bigger-130'></i><i class='drag-icon ace-icon fa fa-arrows bigger-125'></i></div><div class='dd2-content'>"+val.name+
					"<div class='pull-right action-buttons'><a class='blue' href='javascript:menu_edit("+val.id+");'><i class='ace-icon fa fa-pencil bigger-130'></i></a><a class='red' href='javascript:menu_del("+val.id+");'><i class='ace-icon fa fa-trash-o bigger-130'></i></a></div>"+
					"</div></li> ";
					$("#ol_1-"+val.pId).append(cli);
				}
				
			}); 
		}
	});
};

function menu_del(id){
	$.ajax({
		type : "post",
		dataType : "json",
		url : "<%=basePath%>page/html/content/sys/querymenubypmid.action",
		data : {menuId:id},
		success : function(data) {
			var ms = data.ms;
			var len = ms.length;
			if(len==0){
				$.ajax({
					type : "post",
					dataType : "json",
					url : "<%=basePath%>page/html/content/sys/delmenu.action",
					data : {menuId:id},
					success : function(data) {
						var msg = data.msg;
						alert(msg);
						$("#menulist").children().filter('li').remove();
						InitList();
					}
				});
			}else{
				alert("有子菜单，无法删除，请先删除所有子菜单！");
			}
		}
	});
	
	
};


$("#select_pmid").on('change', function(evt, params) {
	var value=$("#select_pmid").children('option:selected').val();
	//计算菜单code
	$.ajax({
		type : "post",
		dataType : "json",
		url : "<%=basePath%>page/html/content/sys/queryMenuCode.action",
		data : {menuId:value},
		success : function(data) {
			var code = data.code;
			$('#menucode').val(code);
			$('#menucode_hidden').val(code);
			if(value==-1){
				$('#menuurl').val('#');
				$('#menuurl').attr("readonly","readonly");
			}else{
				$('#menuurl').val('');
				$('#menuurl').attr("readonly",false);
			}
		}
	});
	
});

	
</script>
	