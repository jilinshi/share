<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String ftype = "REPORT";
%>
<title>核对报告数据查询</title>
<link rel="stylesheet" href="<%=basePath%>assets/css/jquery-ui.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/datepicker.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/ui.jqgrid.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/chosen.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/dropzone.css" />
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
					<label>姓名</label>
					<input type="text" class="input-large" placeholder="姓名" name="memberDTO.membername"/>
					<label>身份证号码</label>
					<input type="text" class="input-large" placeholder="身份证号码" name="memberDTO.paperid"/>
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
				<form class="form-horizontal" id="attorney">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 委托单位 </label>
		
						<div class="col-sm-9">
							<input type="text" id="form-field-1" placeholder="委托单位" class="col-xs-10 col-sm" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 委托内容  </label>
		
						<div class="col-sm-9">
							<input type="text" id="form-field-2" placeholder="委托内容" class="col-xs-10 col-sm" />
						</div>
					</div>
				</form>
				<div class="space-6"></div>
				<div>
					<form method="post"
						action="${pageContext.request.contextPath}/page/html/content/report/upload.action"
						class="dropzone" id="dropzone">
						<div class="fallback">
							<input name="single" type="file" multiple="" />
						</div>
					</form>
				</div>
			</div>
			</div>
		</div>
		<script type="text/javascript">
			var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
		</script>
	</div>
</div>
<script src="<%=basePath%>assets/js/jquery-ui.js"></script>
<script src="<%=basePath%>assets/js/chosen.jquery.js"></script>
<script type="text/javascript">
	var scripts = [null,"<%=basePath%>assets/js/date-time/bootstrap-datepicker.js","<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js","<%=basePath%>assets/js/dropzone.js", null]
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
				url : "<%=basePath%>page/html/content/report/queryPersonalInfo.action",
				mtype : "POST", 
				datatype : "json",
				postData : formData,
				height : 321,
				colNames : ['家庭编号','姓名','身份证号码','户主姓名','操作'],
				colModel : [
				    {name:'col1',formatter:"actionFormatter"},
					{name:'pname',formatter:"actionFormatter"},
				    {name:'idno',formatter:"actionFormatter"},
				    {name:'mastername',formatter:'actionFormatter'},
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
				  var viewBtn ="<div class='hidden-sm hidden-xs btn-group'><button id='v_"+rowData.col1+"' class='btn btn-minier btn-success' onclick='view(\""+rowData.col1+"\")'><i class='ace-icon fa fa-cloud-upload bigger-100'></i>上传委托书</button></div>"
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
			try {
				Dropzone.autoDiscover = true;
			  	var myDropzone = new Dropzone("#dropzone" , {
			    paramName: "single", // The name that will be used to transfer the file
			    //maxFilesize: 5.5, // MB
			
				addRemoveLinks : true,
				dictRemoveFile:'删除',
				dictDefaultMessage :
				'<span class="bigger-150 bolder"><i class="ace-icon fa fa-caret-right red"></i>拖动文件</span>到此处上传\
				<span class="smaller-80 grey">(或者点击此处)</span> <br /> \
				<i class="upload-icon ace-icon fa fa-cloud-upload blue fa-3x"></i>',
				dictResponseError: '错误 正在上传文件',
				
				//change the previewTemplate to use Bootstrap progress bars
				previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>"
				,
				  init: function () {
			            this.on("success", function (data) {                
			                var res = eval('(' + data.xhr.responseText + ')');
			                var ftype='<%=ftype%>';
			                addfile(res.fileid,res.realname,res.realpath,res.displayname,ftype);
			            });
			            this.on("removedfile", function (data) {                
			                var res = eval('(' + data.xhr.responseText + ')');
			                remfile(res.fileid,res.realname,res.realpath);  
			            });
			        }
	
			  });
		  	 function remfile(fid,realname,realpath){
				  if(-1!=fid){
				  $.ajax({url: "<%=basePath%>page/html/content/report/removedfile.action",
					  type:"GET",
					  dataType:"JSON",
					  data: {'fileid':fid, 'realname':realname, 'realpath':realpath},
		              success: function(){
		            	  alert("文件和文件所产生的记录已经删除");
				 }});}
			  }
			  
			  function addfile(fid,realname,realpath,displayname,ftype){
				alert("保存文件");
			  }
			  
			   $(document).one('ajaxloadstart.page', function(e) {
					try {
						myDropzone.destroy();
					} catch(e) {}
			   });
			
			} catch(e) {
				  alert('不支持此浏览器!');
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
	
	function view(id){
		<%-- //window.location.href ="<%=basePath%>page/html/content/printreport/printcollatingreport.action"; --%>
		<%-- window.open("<%=basePath%>page/html/content/printreport/printcollatingreport.action"); --%>
		 var dialog = $("#dialog-confirm").removeClass('hide').dialog({
			autoOpen: true,//如果设置为true，则默认页面加载完毕后，就自动弹出对话框；相反则处理hidden状态。 
		    bgiframe: true, //解决ie6中遮罩层盖不住select的问题  
			hide:true,
			resizable: true,
			width: '600',
			modal: true,
			title: "上传委托书",
			buttons: [ 
						{
							text: "Cancel",
							"class" : "btn btn-minier",
							click: function() {
								document.getElementById("attorney").reset();
								$( this ).dialog( "close" );
							} 
						},
						{
							text: "OK",
							"class" : "btn btn-primary btn-minier",
							click: function() {
								var params=$("#dialog-confirm #attorney").serialize();
								params = decodeURIComponent(params,true); 
								alert(params);
								
								//$( this ).dialog( "close" ); 
							} 
						}
					]
		}); 
		
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