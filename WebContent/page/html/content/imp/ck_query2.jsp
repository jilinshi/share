<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String _title = "公积金数据查询";
	String _pagetype="PpFund";
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
		
		<div class="widget-box">
			<div class="widget-body">
			<div class="widget-main">
			 <form class="form-inline" role="form" id="searchform">	 
			<div class="form-group">
				<label for="imptype">选择导出类别</label>
		      	<select id="imptype" name="imptype" class="multiselect">
		      			<option value="-1">全部</option>
						<option value="1" selected="selected">新增低保</option>
						<option value="2">调整</option>
						<option value="3">全库低保 </option>
						<option value="4">低收入 </option>
						<option value="5">分类施保</option>
						<option value="6">边缘户</option>
				</select>&nbsp;
				<label for="impkind">是否关联核对数据</label>
		      	<select id="impkind" name="impkind" class="multiselect">
						<option value="1">是</option>
						<option value="0">否</option>
				</select>&nbsp;
				<button type="button" class="btn btn-info btn-sm"  onclick="javascript:onClik();">
						<i class="ace-icon fa fa-search bigger-110"></i>查询
					</button>
					<a href="<%=basePath%>downloadExcel.action?fileName=公积金数据查询" class="btn btn btn-sm">
						<i class="ace-icon fa fa-file-excel-o bigger-110"></i>导出Excel
					</a>
      		</div>
		</form>
			</div>
		</div>
		</div>
		
		
		
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
var scripts = [null,"<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js","<%=basePath%>assets/js/date-time/bootstrap-datepicker.js","<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js", null]
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
			postData:{'pagetype':'<%=_pagetype%>','imptype':'1','impkind':'1'},
			height: 450,
			 											

			colNames: ['','姓名','身份证号','公积金账号','所有人姓名','所有人身份证号','缴存基数','最后缴款日','账户余额','单位名称','状态','开户日期','区'],
			colModel:[
				{name:'piId',index:'piId', width:80, fixed:true, sortable:false, resize:false,hidden:true},
						{ name: 'pname'},
						{ name: 'idno'},
						{ name: 'gjjaccount'},
						{ name: 'bname'},
						{ name: 'bidno'},
						{ name: 'cardinal'},
						{ name: 'lasttime'},
						{ name: 'banlance'},
						{ name: 'unitname'},
						{ name: 'state'},
						{ name: 'begintime'},
						{ name: 'area'}
			], 
			gridComplete: function(){
				                //在Grid的第一列（Actions）中添加按钮E、S、C，添加增、删、查、改按钮；
				                 var ids = jQuery(grid_selector).jqGrid('getDataIDs');
				                 for(var i=0;i < ids.length;i++){
				                    var cl = ids[i];
				                   // be="aaa";
				                     //var p1= $(grid_selector).getCell(cl,'fileId');
				                     // jQuery(grid_selector).jqGrid('setRowData',ids[i],{fileId:be});
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
					updatePagerIcons(table);
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
						
						function updatePagerIcons(table) {
							var replacement = 
							{
								'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
								'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
								'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
								'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
							};
							$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
								var icon = $(this);
								var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
								
								if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
							})
						}
					});
					
function onClik(){
    var jsonuserinfo = $('#searchform').serializeObject();
    jQuery("#grid-table").setGridParam({postData : jsonuserinfo,page : 1,'pagetype':'<%=_pagetype%>'}).trigger("reloadGrid");
};

$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray(); 
   $.each(a, function() {
       if (o[this.name]) {   
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};  

</script>
