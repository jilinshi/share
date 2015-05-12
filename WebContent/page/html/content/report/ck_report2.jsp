<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>吉林市社会救助局信息共享平台-核对报告历史数据查询</title>
<link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/chosen.css" />
<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12">
	  <div class="widget-box">
			<div class="widget-body">
			<div class="widget-main">
				<form class="form-inline" id="searchform">
					<label for="form-field-select">机构</label>
					<select style="width:200px;" class="chosen-select" id="form-field-select" name="memberDTO.onNo" data-placeholder="请选择 . . .">
					</select>
					<label>户主姓名</label>
					<input type="text" class="input-large" placeholder="户主姓名" name="memberDTO.membername"/>
					<label>户主身份证号码</label>
					<input type="text" class="input-large" placeholder="户主身份证号码" name="memberDTO.paperid"/>
					<button type="button" class="btn btn-info btn-sm" onclick="javascript:onClik();">
						<i class="ace-icon fa fa-search bigger-110"></i>查询
					</button>
					<a href="<%=basePath%>downloadExcel.action?fileName=社保" class="btn btn btn-sm">
						<i class="ace-icon fa fa-file-excel-o bigger-110"></i>导出Excel
					</a>
				</form>
			</div>
		</div>
		</div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
		<div id="dialog-confirm" class="hide">
			 <table id="grid-table1"></table>
		</div>
		<script type="text/javascript">
			var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
		</script>
	</div>
</div>
<script src="<%=basePath%>assets/js/jquery-ui.js"></script>
<script src="<%=basePath%>assets/js/chosen.jquery.js"></script>
<script type="text/javascript">
	var scripts = [null,"<%=basePath%>assets/js/date-time/bootstrap-datepicker.js","<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js", null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			//机构下拉列表初始化
			chosen_Init();
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
					setTimeout(function() {
						$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
					}, 0);
				}
		    })
			
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
					postData:{'masterid':$(grid_selector).getCell(rowId,'masteridno')},
					url:"<%=basePath%>page/html/content/printreport/queryReportsByMaid.action",
					colNames: ['文档名称','文档类型','生成时间','操作'],
					colModel: [
						{ name: 'filename'},
						{ name: 'contentType'},
						{ name: 'uploadDate'},
						{ name: 'id', index:'id',align:'center'}
					],
					  gridComplete : function() {
			              var ids = jQuery("#" + subgridTableId).jqGrid('getDataIDs');
			              for ( var i = 0; i < ids.length; i++) {
			                var cl = ids[i];
			                be ="<div class='hidden-sm hidden-xs btn-group'><button id='v_"+cl+"' class='btn btn-minier btn-success' onclick='view1(\""+cl+"\")'><i class='ace-icon fa 	fa-binoculars bigger-100'></i>查看报告</button>"+
							  "</div>";
			             
			                jQuery("#" + subgridTableId).jqGrid('setRowData', ids[i],
			                    {
			                      id : be
			                    });
			              }
			            }
				});
			},
			autowidth:true,
			mtype:"POST",
			url : "<%=basePath%>page/html/content/printreport/queryAttorneyrecord.action",
			datatype: "json",
			postData:formData,
			height: 450,
			colNames:['户主身份证号','户主姓名','地址','上传时间','操作'],
			colModel:[
				 {name:'masteridno',formatter:"actionFormatter"},
					{name:'mastername',formatter:"actionFormatter"},
				    {name:'col11',formatter:'actionFormatter'},
				    {name:'uploadtime',formatter:'actionFormatter'},
				    {name:'VIEW', index:'VIEW',align:'center'}
			], 
			gridComplete: function(){},
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
					styleCheckbox(table);
					updateActionIcons(table);
					updatePagerIcons(table);
					enableTooltips(table);
					checkbutton(table);
				}, 0);
			},
			caption: "核对报告生成与查看"
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
			
 			function checkbutton(table){
 				var ids = jQuery("#grid-table").jqGrid('getDataIDs');
 		        for (var i = 0; i < ids.length; i++) {
 		          var id = ids[i];
 		          var rowData = $("#grid-table").getRowData(id);
				  var viewBtn ="<div class='hidden-sm hidden-xs btn-group'><button id='v_"+rowData.masteridno+"' class='btn btn-minier btn-success' onclick='view(\""+rowData.masteridno+"\",\""+rowData.mastername+"\")'><i class='ace-icon fa fa-cloud-upload bigger-100'></i>生成核对报告</button>"+
				  "</div>"
 		          jQuery("#grid-table").jqGrid('setRowData', ids[i], { VIEW: viewBtn });
 				}
 			}
 			function styleCheckbox(table) {
 				
 			}
 			function updateActionIcons(table) {
 				
 			}
 			function enableTooltips(table) {
 				$('.navtable .ui-pg-button').tooltip({container:'body'});
 				$(table).find('.ui-pg-div').tooltip({container:'body'});
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
	
	
	function view1(id){
		window.open("<%=basePath%>page/html/content/printreport/queryOneReport.action?masterid="+id);
	}
	function view(id,idno){
		window.open("<%=basePath%>page/html/content/printreport/printcollatingreport.action?masterid="+id);
	}
	
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
				$("#form-field-select").append("<option value=' '> </option>").append(a);
				$("#form-field-select").chosen({allow_single_deselect:true});
			}
		});
	}
	
</script>