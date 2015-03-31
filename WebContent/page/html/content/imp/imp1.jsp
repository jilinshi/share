<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String ftype="INSURANCE";
%>
<title>吉林市信息共享平台</title>
<link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />

<!-- ajax layout which only needs content area -->
<!--<div class="page-header"></div> -->
<!-- /.page-header -->

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		 
		<table id="grid-table"></table>

		<div id="grid-pager"></div>


		<script type="text/javascript">
			var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
		</script>

		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
</div><!-- /.row -->

<!-- page specific plugin scripts -->
<script type="text/javascript">
var changeGridOptions;
var _basepath='<%=basePath%>';
var _ftype='<%=ftype%>';
	var scripts = [null,"<%=basePath%>assets/js/date-time/bootstrap-datepicker.js","<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js","<%=basePath%>page/html/content/imp/myextra.js", null];
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	  //inline scripts related to this page
		 var grid_data = 
	[ 
		{id:"1",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
		{id:"2",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
		{id:"3",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
		{id:"4",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"},
		{id:"5",name:"Laser Printer",note:"note2",stock:"Yes",ship:"FedEx",sdate:"2007-12-03"},
		{id:"6",name:"Play Station",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
		{id:"7",name:"Mobile Telephone",note:"note",stock:"Yes",ship:"ARAMEX",sdate:"2007-12-03"},
		{id:"8",name:"Server",note:"note2",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
		{id:"9",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
		{id:"10",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
		{id:"11",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
		{id:"12",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
		{id:"13",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"},
		{id:"14",name:"Laser Printer",note:"note2",stock:"Yes",ship:"FedEx",sdate:"2007-12-03"},
		{id:"15",name:"Play Station",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
		{id:"16",name:"Mobile Telephone",note:"note",stock:"Yes",ship:"ARAMEX",sdate:"2007-12-03"},
		{id:"17",name:"Server",note:"note2",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
		{id:"18",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
		{id:"19",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
		{id:"20",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
		{id:"21",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
		{id:"22",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
		{id:"23",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"}
	];
	
	var subgrid_data = 
	[
	 {id:"1", name:"sub grid item 1", qty: 11},
	 {id:"2", name:"sub grid item 2", qty: 3},
	 {id:"3", name:"sub grid item 3", qty: 12},
	 {id:"4", name:"sub grid item 4", qty: 5},
	 {id:"5", name:"sub grid item 5", qty: 2},
	 {id:"6", name:"sub grid item 6", qty: 9},
	 {id:"7", name:"sub grid item 7", qty: 3},
	 {id:"8", name:"sub grid item 8", qty: 8}
	];
	
	jQuery(function($) {
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		
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
		
		//if your grid is inside another element, for example a tab pane, you should use its parent's width:
		/**
		$(window).on('resize.jqGrid', function () {
			var parent_width = $(grid_selector).closest('.tab-pane').width();
			$(grid_selector).jqGrid( 'setGridWidth', parent_width );
		})
		//and also set width when tab pane becomes visible
		$('#myTab a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		  if($(e.target).attr('href') == '#mygrid') {
			var parent_width = $(grid_selector).closest('.tab-pane').width();
			$(grid_selector).jqGrid( 'setGridWidth', parent_width );
		  }
		})
		*/
		
		
	
	
	
		jQuery(grid_selector).jqGrid({
			//direction: "rtl",
	
			//subgrid options
			subGrid : true,
			//subGridModel: [{ name : ['No','Item Name','Qty'], width : [55,200,80] }],
			//datatype: "xml",
			subGridOptions : {
				plusicon : "ace-icon fa fa-plus center bigger-110 blue",
				minusicon  : "ace-icon fa fa-minus center bigger-110 blue",
				openicon : "ace-icon fa fa-chevron-right center orange"
			},
			//for this example we are using local data
			
 
			subGridRowExpanded: function (subgridDivId, rowId) {
				var subgridTableId = subgridDivId + "_t";
				$("#" + subgridDivId).html("<table id='" + subgridTableId + "'></table>");
				$("#" + subgridTableId).jqGrid({
					autowidth:true,
					datatype: 'json',
					mtype:"POST",
					postData:{'fileDTO.realpath':$(grid_selector).getCell(rowId,'realpath'),'fileDTO.ftype':'<%=ftype%>'},
					url:"<%=basePath%>page/html/content/imp/queryFiletoGrid.action",
					colNames: ['家庭编号','低保成员姓名','低保身份证号码','区','街道','社区','社保姓名','社保身份证号码','工作单位','退休时间','退休金'],
					colModel: [
						{ name: 'fno'},
						{ name: 'pname'},
						{ name: 'idno' , width:160},
						{ name: 'oo1', width:50},
						{ name: 'oo2', width:50},
						{ name: 'oo3' , width:50},
						{ name: 'sbname'},
						{ name: 'sbidno', width:160},
						{ name: 'comp' },
						{ name: 'txtime'},
						{ name: 'txmoney' }
					]
				});
			},
			
			autowidth:true,
			mtype:"POST",
			url:"<%=basePath%>page/html/content/imp/queryFiles.action",
			datatype: "json",
			postData:{'fileDTO.ftype':'<%=ftype%>'},
			height: 450,
			colNames:['','文档名称','文档路径','处理状态', '上传时间', '上传用户','最后操作时间'],
			colModel:[
				{name:'fileId',index:'fileId', width:80, fixed:true, sortable:false, resize:false},
				//{name:'fileid',index:'fileid', width:60, sorttype:"int", editable: true},
				//{name:'sdate',index:'sdate',width:90, editable:true, sorttype:"date",unformat: pickDate},
				//{name:'filename',index:'filename', width:150,editable: true,editoptions:{size:"20",maxlength:"30"}},
				//{name:'stock',index:'stock', width:70, editable: true,edittype:"checkbox",editoptions: {value:"Yes:No"},unformat: aceSwitch},
				//{name:'ship',index:'ship', width:90, editable: true,edittype:"select",editoptions:{value:"FE:FedEx;IN:InTime;TN:TNT;AR:ARAMEX"}},
				//{name:'fileid',index:'fileid', width:150, sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"10"}} 
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
				                    //alert($(grid_selector).getCell(cl,'fileId'));
				                   var p1= $(grid_selector).getCell(cl,'fileId');
				                   var p2= $(grid_selector).getCell(cl,'realpath');
				                   var p3= '<%=ftype%>';
				                       be = "<input   type='button' value='导入' class=\"btn btn-sm btn-danger btn-white btn-round\"  onclick='impexcel("+ p1 +",\""+_basepath+"\",\""+_ftype+"\")'  />"+""+
				                       "<input   type='button' value='删除' class=\"btn btn-sm btn-danger btn-white btn-round\"  onclick='delexcel("+ p1 +",\""+_basepath+"\",\""+_ftype+"\")'  />";
				                      jQuery(grid_selector).jqGrid('setRowData',ids[i],{fileId:be});
				                }
				            },
			viewrecords : true,
			rowNum:15,
			rowList:[10,15,20],
			pager : pager_selector,
			altRows: true,
			//toppager: true,
			rownumbers: true,
			multiselect: false,
			//multikey: "ctrlKey",
	        multiboxonly: true,
	
			loadComplete : function() {
				var table = this;
				setTimeout(function(){
					styleCheckbox(table);
					updateActionIcons(table);
					updatePagerIcons(table);
					enableTooltips(table);
				}, 0);
			},
	
			//editurl: "/dummy.html",//nothing is saved
			caption: "社保数据导入"
	
			//,autowidth: true,
	
	
			/**
			,
			grouping:true, 
			groupingView : { 
				 groupField : ['name'],
				 groupDataSorted : true,
				 plusicon : 'fa fa-chevron-down bigger-110',
				 minusicon : 'fa fa-chevron-up bigger-110'
			},
			caption: "Grouping"
			*/
	
		});
		$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
		
		
	
		//enable search/filter toolbar
		//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
		//jQuery(grid_selector).filterToolbar({});
	
	
		//switch element when editing inline
		function aceSwitch( cellvalue, options, cell ) {
			setTimeout(function(){
				$(cell) .find('input[type=checkbox]')
					.addClass('ace ace-switch ace-switch-5')
					.after('<span class="lbl"></span>');
			}, 0);
		}
		//enable datepicker
		function pickDate( cellvalue, options, cell ) {
			setTimeout(function(){
				$(cell) .find('input[type=text]')
						.datepicker({format:'yyyy-mm-dd' , autoclose:true}); 
			}, 0);
		}
	 
	 
		
		 
	
	
		//it causes some flicker when reloading or navigating grid
		//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
		//or go back to default browser checkbox styles for the grid
		function styleCheckbox(table) {
		/**
			$(table).find('input:checkbox').addClass('ace')
			.wrap('<label />')
			.after('<span class="lbl align-top" />')
	
	
			$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
			.find('input.cbox[type=checkbox]').addClass('ace')
			.wrap('<label />').after('<span class="lbl align-top" />');
		*/
		}
		
	
		//unlike navButtons icons, action icons in rows seem to be hard-coded
		//you can change them like this in here if you want
		function updateActionIcons(table) {
			/**
			var replacement = 
			{
				'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue',
				'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red',
				'ui-icon-disk' : 'ace-icon fa fa-check green',
				'ui-icon-cancel' : 'ace-icon fa fa-times red'
			};
			$(table).find('.ui-pg-div span.ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			})
			*/
		}		
		//replace icons with FontAwesome icons like above
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
	
		function enableTooltips(table) {
			$('.navtable .ui-pg-button').tooltip({container:'body'});
			$(table).find('.ui-pg-div').tooltip({container:'body'});
		}
	
		//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
	
		$(document).one('ajaxloadstart.page', function(e) {
			$(grid_selector).jqGrid('GridUnload');
			$('.ui-jqdialog').remove();
		});
	});
	});
</script>
