<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String _title = "房产数据查询";
	String _pagetype="PpHouseproperty";
%>
<title>核对结果导出</title>
<link rel="stylesheet" href=".<%=basePath%>assets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap-multiselect.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/select2.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/chosen.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/daterangepicker.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap-datetimepicker.css" />

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
				时间：
				<input type="text" name="begintime" id="begintime" class="form-control" readonly="readonly"/>
				至
				<input type="text" name ="endtime" id="endtime" class="form-control"  readonly="readonly"/>&nbsp;
				<label for="form-field-select">所属：</label>
					<select  class="chosen-select" id="form-field-select" name="onno" data-placeholder="请选择 . . .">
					</select>&nbsp;
					来源：<select id="ds" name="ds" class="chosen-select" data-placeholder="选择...">
						<option value="">全部</option>
						<option value="1">城市</option>
						<option value="2">农村</option>
						</select>&nbsp;
					<label for="attorney">授权书：</label>
					<select id="attorney" name="attorney" class="select2" data-placeholder="选择...">
						<option value="">全部</option>
						<option value="1" selected="selected">有</option>
						<option value="0">无</option>
						</select>
					&nbsp;
				<button type="button" class="btn btn-info btn-sm"  onclick="javascript:onClik();">
						<i class="ace-icon fa fa-search bigger-110"></i>查询
					</button>
					<br><br>
					<a href="<%=basePath%>downloadExcel.action?fileName=pphousepropertyxls1" class="btn btn btn-sm">
						<i class="ace-icon fa fa-file-excel-o bigger-90"></i>按Excel导出核对数据
					</a>
					&nbsp;
					<a href="<%=basePath%>downloadCSV.action?fileName=pphousepropertycsv1" class="btn btn btn-sm">
						<i class="ace-icon fa fa-file-excel-o bigger-90"></i>按CSV导出核对数据
					</a>
					&nbsp;
					<a href="<%=basePath%>downloadExcel.action?fileName=pphouseproperty" class="btn btn btn-sm">
						<i class="ace-icon fa fa-file-excel-o bigger-90"></i>按Excel导出核对结果
					</a>
					&nbsp;
					<a href="<%=basePath%>downloadCSV.action?fileName=pphousepropertycsv" class="btn btn btn-sm">
						<i class="ace-icon fa fa-file-excel-o bigger-90"></i>按CSV导出核对结果
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
var scripts = [null,"<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js","<%=basePath%>assets/js/date-time/bootstrap-datepicker.js","<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js","<%=basePath%>assets/js/publicSetup.js", null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		chosen_Init();
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
			colNames: ['','家庭编号','姓名','身份证号（18位）','区','街道','社区','归档日期','受理业务','房产证号','所有人','所有人身份证号','坐落','面积','备注','办理日期','类型'],
			colModel:[
				{name:'piId',index:'piId', width:80, fixed:true, sortable:false, resize:false,hidden:true},
				{ name: 'col1'},
						{ name: 'pname'},
						{ name: 'idno'},
						{ name: 'o1'},
						{ name: 'o2' },
						{ name: 'o3'},
						{ name: 'ggtime'},
						{ name: 'slyw',},
						{ name: 'fczh' },
						{ name: 'bname'},
						{ name: 'bidno' },
						{ name: 'zuoluo'},
						{ name: 'mianji' },
						{ name: 'remark' },
						{ name: 'bltime' },
						{ name: 'subject'}
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
							$( "#begintime" ).datepicker({
								showOtherMonths: true,
								selectOtherMonths: false,
								format: 'yyyy-mm-dd',
								
							});
							$( "#endtime" ).datepicker({
								showOtherMonths: true,
								selectOtherMonths: false,
								format: 'yyyy-mm-dd'
							});
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
function chosen_Init(){
	$.ajax({
		type : "post",
		dataType : "json",
		url : "<%=basePath%>page/html/content/report/getOrgList.action",
		success : function(data) {
			var orgs = data.orgs;
			var len = orgs.length;
			var a = " ";
			for(var i=0; i<len; i++){
				var value = orgs[i].orgId;
				var text = orgs[i].orgName;
				a = a + "<option value='"+ value +"'>"+ text +"</option>";
			}
			$("#form-field-select").append("<option value=''></option>").append(a);
			$("#form-field-select").chosen({allow_single_deselect:true});
		}
	});
};
function returnLogin(){
	window.open('<%=basePath%>login.jsp', '吉林市社会救助局信息共享平台');
	window.open('', '_self');
};
</script>
