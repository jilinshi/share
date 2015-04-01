<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>社保数据查询</title>
<link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />
<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12">
		<div class="widget-box">
			<div class="widget-header">
				<h4 class="widget-title">查询</h4>
			</div>
			<div class="widget-body">
			<div class="widget-main">
				<form class="form-inline" id="searchform">
					<label>姓名</label>
					<input type="text" class="input-large" placeholder="姓名" name="insuranceDTO.pname"/>
					<label>身份证号码</label>
					<input type="text" class="input-large" placeholder="身份证号码" name="insuranceDTO.idno"/>
					<button type="button" class="btn btn-info btn-sm">
						<i class="ace-icon fa fa-search bigger-110"></i>查询
					</button>
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
	var scripts = [null,"<%=basePath%>assets/js/date-time/bootstrap-datepicker.js","<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js", null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			var formData=$("#searchform").serialize();
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
				//url:"${pageContext.request.contextPath}/page/html/content/query/myjson.json",
				url : "<%=basePath%>page/html/content/query/queryInit.action?"+formData,
				mtype : "POST", 
				datatype : "json",
				//ajaxGridOptions: { contentType: 'application/json; charset=utf-8' },
				height : 250,
				colNames : ['ID' ,'家庭编号','姓名','身份证号码','区','街道','社区','社保姓名','社保身份证号码','工作单位','退休金','退休时间','科目','备注' ],
				colModel : [
					{name:'inId',index:'inId'},
					{name:'fno',index:'fno',hidden:true,formatter:"actionFormatter"},
					{name:'pname',index:'pname',formatter:"actionFormatter"},
					{name:'idno',index:'idno',formatter:"actionFormatter"},
					{name:'oo1',index:'oo1',formatter:"actionFormatter"},
					{name:'oo2',index:'oo2',formatter:"actionFormatter"},
					{name:'oo3',index:'oo3',formatter:"actionFormatter"},
					{name:'sbname',index:'sbname',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
					{name:'sbidno',index:'sbidno',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
					{name:'comp',index:'comp',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
					{name:'txmoney',index:'txmoney',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
					{name:'txtime',index:'txtime',formatter:'date',formatoptions: {newformat:'Y-m-d'},cellattr: addCellAttr_bgcolor},
					{name:'subject',index:'subject',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor},
					{name:'remark',index:'remark',formatter:"actionFormatter",cellattr: addCellAttr_bgcolor}
				], 
				rownumbers: true,
				autowidth : true,
				viewrecords : true,
				rowNum : 10,
				rowList : [10,20,30],
				pager : pager_selector,
				altRows : true,
				//toppager: true,
				//multiselect: true,
				//multikey: "ctrlKey",
		        //multiboxonly: true,
		
				 loadComplete : function() {
					var table = this;
					setTimeout(function(){
						styleCheckbox(table);
						updatePagerIcons(table);
						//formatetext(table);
					}, 0);
				}, 
				caption : "数据信息显示"
		
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
</script>
