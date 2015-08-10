<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>房产数据查询</title>
<link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />
<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12">
		<div class="widget-box">
			<!-- <div class="widget-header">
				<h4 class="widget-title">查询</h4>
			</div> -->
			<div class="widget-body">
			<div class="widget-main">
				<form class="form-inline" id="searchform">
					<label>姓名</label>
					<input type="text" class="input-large" placeholder="姓名" name="housepropertyDTO.pname"/>
					<label>身份证号码</label>
					<input type="text" class="input-large" placeholder="身份证号码" name="housepropertyDTO.idno"/>
					<button type="button" class="btn btn-info btn-sm" onclick="javascript:onClik();">
						<i class="ace-icon fa fa-search bigger-110"></i>查询
					</button>
					<a href="<%=basePath%>downloadExcel.action?fileName=房产" class="btn btn btn-sm">
						<i class="ace-icon fa fa-file-excel-o bigger-110"></i>导出Excel
					</a>
				</form>
			</div>
		</div>
		</div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
		<script type="text/javascript">
			var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
		</script>
	</div>
</div>
<script type="text/javascript">
	var scripts = [null,"<%=basePath%>assets/js/date-time/bootstrap-datepicker.js","<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js","<%=basePath%>assets/js/publicSetup.js", null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			var formData = $("#searchform").serializeObject();
			//resize to fit page size
			$(window).on('resize.jqGrid', function () {
				$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
		    })
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
				subGrid : true,
				subGridOptions : {
					plusicon : "ace-icon fa fa-plus center bigger-110 blue",
					minusicon  : "ace-icon fa fa-minus center bigger-110 blue",
					openicon : "ace-icon fa fa-chevron-right center orange"
				},
				subGridRowExpanded: function (subgridDivId, rowId) {
					var subgridTableId = subgridDivId + "_t";
					$("#" + subgridDivId).html("<table id='" + subgridTableId + "'></table>");
					$("#" + subgridTableId).jqGrid({
						url : "<%=basePath%>page/html/content/query/queryHouseproperty.action",
						mtype : "POST", 
						datatype : "json",
						postData : {'housepropertyDTO.hid': $(grid_selector).getCell(rowId,'FId')},
						autowidth:true,
						colNames : [/* '区','街道','社区', */'归档日期','房产证号','所有人','所有人身份证号','坐落','面积','办理日期','建立日期'],
						colModel : [
							/* {name:'oo1',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
							{name:'oo2',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
							{name:'oo3',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor}, */
							{name:'ggtime',width:'120px',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
							{name:'fczh',width:'120px',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
							{name:'bname',width:'80px',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
							{name:'bidno',width:'250px',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
							{name:'zuoluo',width:'350px',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
							{name:'mianji',width:'80px',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
							{name:'bltime',width:'120px',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
							{name:'ctime',width:'120px',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
						]
					});
				},
				url : "<%=basePath%>page/html/content/query/queryHousepropertys.action",
				mtype : "POST", 
				datatype : "json",
				postData : formData,
				height : 321,
				colNames : ['','姓名','身份证号码','户主姓名','来源','低保状态','分类施保状态','再保障状态','姓名','身份证号码'],
				colModel : [
				    {name:'FId',width:'120px',hidden:true},
					{name:'pname',width:'120px',formatter:"actionFormatter"},
				    {name:'idno',width:'250px',formatter:"actionFormatter"},
				    {name:'mastername',width:'120px',formatter:'actionFormatter'},
					{name:'ds',width:'120px',formatter:"actionFormatter"},
					{name:'col4',width:'170px',formatter:"actionFormatter"},
					{name:'col5',width:'170px',formatter:"actionFormatter"},
					{name:'col6',width:'170px',formatter:"actionFormatter"},
					{name:'ckpname',width:'120px',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
					{name:'ckidno',width:'250px',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor}
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
						styleCheckbox(table);
						updatePagerIcons(table);
						//formatetext(table);
					}, 0);
				}, 
				caption : "房产数据信息显示"
		
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
			//改变列字体颜色
			function addCellAttr_color(rowId, val, rawObject, cm, rdata) {
				return "style='color:red'";
			}
 			//改变列背景颜色
 			function addCellAttr_bgcolor(rowId, val, rawObject, cm, rdata) {
 				return "style='background-color:pink'";
 			}
			 
			function styleCheckbox(table) {
	/* 				$(table).find('input:checkbox').addClass('ace')
					.wrap('<label />')
					.after('<span class="lbl align-top" />')
					$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
					.find('input.cbox[type=checkbox]').addClass('ace')
					.wrap('<label />').after('<span class="lbl align-top" />'); */
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
	});
	//form 序列化转json
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
	
	function onClik(){
        var jsonuserinfo = $('#searchform').serializeObject();  
        jQuery("#grid-table").setGridParam({postData : jsonuserinfo,page : 1}).trigger("reloadGrid");
	};
	function returnLogin(){
		window.open('<%=basePath%>login.jsp', '吉林市社会救助局信息共享平台');
		window.open('', '_self');
	};
</script>
