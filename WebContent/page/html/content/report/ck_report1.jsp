<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>核对报告数据查询</title>
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
			<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" id="myForm" method="post" enctype="multipart/form-data" action="<%=basePath%>page/html/content/report/upload.action" target="tempiframe">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 委托单位 </label>
		
						<div class="col-sm-9">
							<input type="text" name="1" id="form-field-1" placeholder="委托单位" class="col-xs-10 col-sm" />
						</div>
					</div>
					<div class="hr hr-12 hr-double"></div>
						<div align="left" style="display: block" id="dfile1"></div>
				</form>
			</div>
			</div>
		</div>
		<iframe id="tempiframe" name="tempiframe" frameborder="0" width="0" height="0" src="about:blank" style="position:absolute; z-index:-1; visibility: hidden;"></iframe>
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
				url : "<%=basePath%>page/html/content/printreport/queryAttorneyrecord.action",
				mtype : "POST", 
				datatype : "json",
				postData : formData,
				height : 321,
				colNames : ['户主身份证号','户主姓名','地址','上传时间','操作'],
				colModel : [
				    {name:'masteridno',formatter:"actionFormatter"},
					{name:'mastername',formatter:"actionFormatter"},
				    {name:'col11',formatter:'actionFormatter'},
				    {name:'uploadtime',formatter:'actionFormatter'},
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
						updatePagerIcons(table);
						checkbutton(table);
					}, 0);
				}, 
				caption : "居民数据信息显示"
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
				  var viewBtn ="<div class='hidden-sm hidden-xs btn-group'><button id='v_"+rowData.masteridno+"' class='btn btn-minier btn-success' onclick='view(\""+rowData.masteridno+"\",\""+rowData.mastername+"\")'><i class='ace-icon fa fa-cloud-upload bigger-100'></i>上传委托书</button></div>"
 		          jQuery("#grid-table").jqGrid('setRowData', ids[i], { VIEW: viewBtn });
 				}
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
	
	function view(id,idno){
		<%-- //window.location.href ="<%=basePath%>page/html/content/printreport/printcollatingreport.action"; --%>
		<%--  --%>
		window.open("<%=basePath%>page/html/content/printreport/printcollatingreport.action?masterid="+id);
	<%-- 	 var dialog = $("#dialog-confirm").removeClass('hide').dialog({
			autoOpen: false,//如果设置为true，则默认页面加载完毕后，就自动弹出对话框；相反则处理hidden状态。 
		    bgiframe: true, //解决ie6中遮罩层盖不住select的问题  
			hide:true,
			resizable: true,
			width: '600',
			modal: true,
			title: "上传委托书",
			buttons: [ 
						{
							text: "关闭",
							"class" : "btn btn-minier",
							click: function() {
								document.getElementById("myForm").reset();
								$( this ).dialog( "close" );
							} 
						},
						{
							text: "保存",
							"class" : "btn btn-primary btn-minier",
							click: function() {
								var params=$("#myForm").serialize();
								alert(params);
								document.getElementById("myForm").submit();
							} 
						}
					]
		}); 
		 
		$.ajax({
				type : "post",
				dataType : "json",
				url : "<%=basePath%>page/html/content/report/getPInfo.action",
				data: {familyno:id,masterid:idno},
				async : false,
				success : function(data) {
					var list = data.memberDTOs;
					var count =list.length;
					var wtno = data.wtno;
					var temp = "";
					temp = temp 
						 + '<div class="form-group"><label class="col-sm-3 control-label no-padding-right">委托书: </label>'
	 				     +' <div class="col-sm-9"><input name="afils" type="file" id="WT'+wtno+'" /></div></div>';
	 				for(var i=0; i<count; i++){
	 				    var relmaster = list[i].relmaster;
	 				    var paperid = list[i].paperid;
	 					temp = temp 
	 				    + '<div class="form-group"><label class="col-sm-3 control-label no-padding-right">身份证件正面('+relmaster+'): </label>'
	 				    +' <div class="col-sm-4"><input name="afils" type="file" id="a-'+paperid+'" /></div></div>'
	 				    + '<div class="form-group"><label class="col-sm-3 control-label no-padding-right">身份证件反面('+relmaster+'): </label>'
	 				    +' <div class="col-sm-4"><input name="afils" type="file" id="b-'+paperid+'" /></div></div>';
	 				}
					var dfile1 = document.getElementById("dfile1");
					dfile1.innerHTML=temp;
					dialog.dialog("open");
				}
		}); --%>
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