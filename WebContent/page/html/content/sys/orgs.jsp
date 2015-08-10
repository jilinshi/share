<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>吉林市信息共享平台</title>
<link rel="stylesheet" href="<%=basePath%>assets/plugins/style.css" rel="stylesheet" type="text/css" />
<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="space-8"></div>
	<div class="col-sm-12">
	<div class="alert alert-block alert-success">
		<i class="ace-icon fa fa-bullhorn"></i>&nbsp;&nbsp;&nbsp;&nbsp;
		①从行政区划列表中选择相应的行政区划；②再从组织机构列表选择相应的组织机构；③新增组织机构;
	</div>
	</div>
	<div class="col-sm-3">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">行政区划列表</h4>
				<div class="widget-toolbar">
					<i class="ace-icon fa fa-arrow-right"></i>
				</div>
			</div>
			<div class="widget-body" style="border: none;">
				<div class="widget-toolbox" id="widget-toolbox-1">
					<div class="btn-toolbar">
						<div class="btn-group">
							<button class="btn btn-sm btn-success btn-white btn-round" id="ExpandAll">
								<i class="ace-icon fa fa-plus green"></i>展开
							</button>
						</div>
						<div class="btn-group">
							<button class="btn btn-sm btn-success btn-white btn-round" id="CollapseAll">
								<i class="ace-icon fa fa-minus green"></i>合并
							</button>
						</div>
					</div>
				</div>
				<div class="widget-main padding-8" style=" overflow: hidden; border: none;" id='jqxTree'>
					<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 300px;">
						<div class="zTreeDemoBackground left">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-3">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">组织机构列表</h4>
				<div class="widget-toolbar">
					<i class="ace-icon fa fa-arrow-right"></i>
				</div>
			</div>
			<div class="widget-body" style="border: none;">
				<div class="widget-toolbox" id="widget-toolbox-1">
					<div class="btn-toolbar">
						<div class="btn-group">
							<button class="btn btn-sm btn-success btn-white btn-round" id="ExpandAll_org">
								<i class="ace-icon fa fa-plus green"></i>展开
							</button>
						</div>
						<div class="btn-group">
							<button class="btn btn-sm btn-success btn-white btn-round" id="CollapseAll_org">
								<i class="ace-icon fa fa-minus green"></i>合并
							</button>
						</div>
					</div>
				</div>
				<div class="widget-main padding-8" style=" overflow: hidden; border: none;" id='jqxTree_org'>
					<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 300px;">
						<div class="zTreeDemoBackground left">
							<ul id="treeDemo_org" class="ztree"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-6">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">添加组织机构</h4>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-8"  style="height: 360px;">
					<form class="form-horizontal" role="form" id="myForm">
						<div class="space-4"></div>
						<br/>
						<!-- #section:elements.form -->
						<div class="form-group">
							<label class="col-sm-4 control-label no-padding-right" for="form-field-1"> 所属辖区组织机构名称 </label>

							<div class="col-sm-8">
								<input type="text" id="orgNameParent" placeholder="请从机构列表中选择..." class="col-xs-10 col-sm-10" disabled/>
								<input type="hidden" id="orgNameParent_hidden" name="organizationDTO.orgNameParent" placeholder="请从机构列表中选择..." class="col-xs-10 col-sm-10" />
							</div>
						</div>
						<br/>
						<div class="form-group">
							<label class="col-sm-4 control-label no-padding-right" for="form-field-1"> 所属辖组织区机构编号 </label>

							<div class="col-sm-8">
								<input type="text" id="orgId" placeholder="所属辖区机构编号" class="col-xs-10 col-sm-10" disabled/>
								<input type="hidden" id="orgId_hidden" name="organizationDTO.orgId" placeholder="所属辖区机构编号" class="col-xs-10 col-sm-10"/>
								<input type="hidden" id="preId_hidden" name="organizationDTO.parentId" placeholder="所属辖区机构编号" class="col-xs-10 col-sm-10"/>
							</div>
						</div>
						<br/>
						<div class="form-group">
							<label class="col-sm-4 control-label no-padding-right" for="form-field-1"> 新增组织机构名称 </label>

							<div class="col-sm-8">
								<input type="text" id="orgName" name="organizationDTO.orgName" placeholder="请输入机构名称..." class="col-xs-10 col-sm-10" />
							</div>
						</div>
						<br/>
						<div class="clearfix form-actions" >
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button" id="sub">
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
						<!-- /section:elements.form -->
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
	</script>
</div>

<script type="text/javascript">
var scripts = [null,"<%=basePath %>assets/ztree/js/jquery.ztree.core-3.5.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js","<%=basePath%>assets/js/publicSetup.js", null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			var setting = {
				 data: {
					simpleData: {
						enable: true
					}
				 },
				 callback: {
						onClick: zTreeOnClick
				}
			};
			var zNodes =[
			];
			$.ajax({ 
		            type: "post", 
		            url: "<%=basePath%>page/html/content/sys/findDistrictList1.action",
					dataType : "json",
					data: {parentid:'-1'},
					success : function(data) {
						var zNodes = data.districts;
						$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown);
					}
				});
		});
		
		
	});
	function zTreeOnClick(event, treeId, treeNode){
		 orgInit(treeNode.id);
	};
	function orgInit(pId){
		var setting = {
			 data: {
				simpleData: {
					enable: true
				}
			 },
			 callback: {
					onClick: zTreeOnClick_org
			 }
		};
		var zNodes =[
		];
		$.ajax({ 
	            type: "post", 
	            url: "<%=basePath%>page/html/content/sys/findOrgList1.action",
				dataType : "json",
				data: {parentid:pId},
				success : function(data) {
					var zNodes = data.orgs;
					$.fn.zTree.init($("#treeDemo_org"), setting, zNodes);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
		});
	};
	function zTreeOnClick_org(event, treeId, treeNode){
		 $("#orgName").val("");
		 $("#orgNameParent").val(treeNode.name);
		 $("#orgNameParent_hidden").val(treeNode.name);
		 $("#orgId").val(treeNode.id);
		 $("#orgId_hidden").val(treeNode.id);
		 $("#preId_hidden").val(treeNode.genId);
	};
	// Expand All
    $('#ExpandAll').click(function () {
    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    	treeObj.expandAll(true);

    });

    // Collapse All
    $('#CollapseAll').click(function () {
    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    	treeObj.expandAll(false);
    });
    
 // Expand All Org
    $('#ExpandAll_org').click(function () {
    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo_org");
    	treeObj.expandAll(true);

    });

    // Collapse All Org
    $('#CollapseAll_org').click(function () {
    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo_org");
    	treeObj.expandAll(false);
    });
    //保存
    $("#sub").click(function (){
    	var orgNameParent = $("#orgNameParent").val();
    	var orgId = $("#orgId").val();
    	var orgName = $("#orgName").val();
    	var flag = true;
    	if(orgNameParent==""){
    		alert("所属辖区组织机构名称不能为空，请从组织机构列表中选择！");
    		flag = false;
    		return flag;
    	}
    	if(orgName==""){
    		alert("请输入新增组织机构名称！");
    		flag = false;
    		return flag;
    	}
    	if(flag){
	    	 $.ajax({ 
		            type: "post", 
		            url: "<%=basePath%>page/html/content/sys/saveOrg.action",
					dataType : "json",
					async : false, //必须同步等返回值
					data : $("#myForm").serialize(),
					success : function(data) {
						var msg = data.msg;
						alert(msg);
						var id = $("#orgId").val();
						orgInit(id);
					}
	 		 });
    	}
    });
    function returnLogin(){
    	window.open('<%=basePath%>login.jsp', '吉林市社会救助局信息共享平台');
    	window.open('', '_self');
    };
</script>