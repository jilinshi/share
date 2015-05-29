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
<link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />
<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-sm-3">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller"><i class="ace-icon fa fa-list"></i>组织机构列表</h4>
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
					<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 340px;">
						<div class="zTreeDemoBackground left">
							<ul id="treeDemo_org" class="ztree"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-9">
		<div class="widget-container-col">
			<!-- #section:custom/widget-box -->
			<div class="widget-box widget-color-orange">
				<div class="widget-header">
					<h5 class="widget-title"><i class="ace-icon fa fa-user"></i>用户列表<span id="orgname"></span></h5>
				</div>
				<div class="widget-body">
				<div class="widget-toolbox" id="widget-toolbox-1">
					<div class="btn-toolbar">
						<div class="btn-group">
							<button class="btn btn-sm btn-info btn-round" id="create">
								<i class="ace-icon fa fa-plus"></i> 新建用户
							</button>
						</div>
					</div>
				</div>
				<div class="widget-main no-padding"  style="height: 365px;">
					<table id="grid-table"></table>
					<div id="grid-pager"></div>
				</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
	</script>
</div>
<script src="<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js"></script>
<script type="text/javascript">
var scripts = [null,"<%=basePath %>assets/ztree/js/jquery.ztree.core-3.5.js","<%=basePath %>assets/ztree/js/jquery.ztree.excheck-3.5.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js", null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			// 加载组织机构树
			orgInit("2202");
			// 加载用户列表
			InitGride("-1");
		});
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
			var orgname=document .getElementById ("orgname");
			var orgid = treeNode.id;
			orgname.innerHTML="__"+treeNode.name+" ( "+orgid+" ) ";
			var querydata = {"orgId":orgid};
			// 加载用户表格
			jQuery("#grid-table").setGridParam({postData : querydata,page : 1}).trigger("reloadGrid");
		};
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
	    
	    function InitGride(orgid){
	    	var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			//resize on sidebar collapse/expand
			var parent_column = $(grid_selector).closest('[class*="col-"]');
			$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
				if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
					//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
					setTimeout(function() {
						$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
					}, 0);
				}
		    })
			
			jQuery(grid_selector).jqGrid({
				url : "<%=basePath%>page/html/content/sys/queryusers.action",
				mtype : "POST", 
				datatype : "json",
				postData : {"orgId":orgid},
				height : 268,
				colNames : ['','姓名','账户','密码','手机','身份份证号码','创建时间','操作'],
				colModel : [
				    {name:'userId',hidden:true},
					{name:'uname'},
					{name:'uaccount'},
					{name:'upwds'},
					{name:'mobilephone'},
					{name:'idno'},
					{name:'utime'},
					{name:'VIEW', index:'VIEW',align:'center'}
				], 
				rownumbers: true,
				autowidth : true,
				viewrecords : true,
				rowNum : 10,
				rowList : [10,20,30],
				pager : pager_selector,
				altRows : true,
				loadComplete : function() {
					var table = this;
					setTimeout(function(){
						checkbutton(table);
					}, 0);
				}
		
			});
			$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
			
 			 $.extend($.fn.fmatter, {
		        actionFormatter: function(cellvalue, options, rowObject) {
		        	var retVal = cellvalue;
		        	if(cellvalue==null){
		        		 retVal = " ";
		       		}
		        	return retVal;
		        }
		    } );
			
 			function checkbutton(table){
 				var ids = jQuery("#grid-table").jqGrid('getDataIDs');
 		        for (var i = 0; i < ids.length; i++) {
 		          var id = ids[i];
 		          var rowData = $("#grid-table").getRowData(id);
				  var viewBtn ="<div class='hidden-sm hidden-xs btn-group'><button id='v_"+rowData.col1+"' class='btn btn-minier btn-success' onclick='view(\""+rowData.col1+"\",\""+rowData.idno+"\")'><i class='ace-icon fa fa-edit bigger-100'></i>编辑</button></div>"
 		          jQuery("#grid-table").jqGrid('setRowData', ids[i], { VIEW: viewBtn });
 				}
 			}
	    };
	    $('#create').click(function () {
	    	alert("11");
	    });
	});

</script>