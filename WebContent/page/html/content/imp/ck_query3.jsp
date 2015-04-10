<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String _title = "aaaaa";
%>
<title>核对结果导出</title>
<link rel="stylesheet" href=".<%=basePath%>assets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap-multiselect.css" />

<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<form class="form-inline" role="form">	 
			<div class="form-group">
				<label for="imptype">选择导出类别</label>
		      	<select id="imptype" class="multiselect">
		      			<option value="-1">全部</option>
						<option value="2">调整</option>
						<option value="3">全库低保 </option>
						<option value="4">低收入 </option>
						<option value="5">分类施保</option>
						<option value="6">边缘户</option>
						<option value="1">新增低保</option>
						<option value="2">调整</option>
						<option value="3">全库低保 </option>
						<option value="4">低收入 </option>
						<option value="5">分类施保</option>
						<option value="6">边缘户</option>
				</select>
				<label for="imptype">是否关联核对数据</label>
		      	<select id="imptype" class="multiselect">
						<option value="0">是</option>
						<option value="1">否</option>
				</select>
      		</div>
		</form>
		<table id="grid-table"></table>

		<div id="grid-pager"></div>

		<script type="text/javascript">
			var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
		</script>
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<!-- page specific plugin scripts -->
<script type="text/javascript">
var scripts = [null,"<%=basePath%>assets/js/date-time/bootstrap-datepicker.js","<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-en.js", null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		$(window).on('resize.jqGrid', function () {
			$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
	    })
	    
		var parent_column = $(grid_selector).closest('[class*="col-"]');
		$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
			if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
				setTimeout(function() {
					$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
				}, 0);
			}
	    })
	    
	   $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
	   
		jQuery(grid_selector).jqGrid({
			autowidth:true,
			mtype:"POST",
			url:"<%=basePath%>page/html/content/imp/queryCheckData.action",
			datatype: "json",
			postData:{'fileDTO.ftype':'RESINSURANCE'},
			height: 450,
			colNames:['','文档名称','文档路径','处理状态', '上传时间', '上传用户','最后操作时间'],
			colModel:[
				{name:'fileId',index:'fileId', width:80, fixed:true, sortable:false, resize:false},
				{ name: 'filename'},
						{ name: 'realpath' ,width:300},
						{ name: 'operstatval'},
						{ name: 'uptime' ,unformat: pickDate},
						{ name: 'uname' },
						{ name: 'opertime',unformat: pickDate}
			], 
			gridComplete: function(){
				                //在Grid的第一列（Actions）中添加按钮E、S、C，添加增、删、查、改按钮；
				                 var ids = jQuery(grid_selector).jqGrid('getDataIDs');
				                 for(var i=0;i < ids.length;i++){
				                    var cl = ids[i];
				                    be="aaa";
				        
				                      jQuery(grid_selector).jqGrid('setRowData',ids[i],{fileId:be});
				                }
				            },
			viewrecords : true,
			rowNum:15,
			rowList:[10,15,20],
			pager : pager_selector,
			altRows: true,
			rownumbers: true,
			multiselect: false,
	        multiboxonly: true,
			loadComplete : function() {
				var table = this;
				setTimeout(function(){
					 
				}, 0);
			},
			caption: "<%=_title%>"
			});
						function pickDate(cellvalue, options, cell) {
							setTimeout(function() {
								$(cell).find('input[type=text]').datepicker({
									format : 'yyyy-mm-dd',
									autoclose : true
								});
							}, 0);
						}
					});
</script>
