<%@page import="com.share.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserDTO user =(UserDTO) session.getAttribute("user");
	Long orgId=user.getSysOrganization().getOrgId();
%>
<title>吉林市信息共享平台</title>
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
					<label>身份证号码</label>
					<input type="text" class="input-large" placeholder="身份证号码" name="memberDTO.paperid"/>
					<button type="button" class="btn btn-info btn-sm" onclick="javascript:onClik();">
						<i class="ace-icon fa fa-search bigger-110"></i>查询
					</button>
					<button type="button" class="btn btn-success btn-sm" onclick="javascript:add();">
						<i class="ace-icon fa fa-add bigger-110"></i>添加人员
					</button>
				</form>
			</div>
		</div>
		</div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
		<div id="dialog-confirm" class="hide">
			<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" id="myForm" method="post" >
					<div class="space-10"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="onNo"> 所在辖区 </label>
						<div class="col-sm-9">
							<select style="width:200px;" class="chosen-select form-control" id="onNo" name="memberDTO.onNo" data-placeholder="请选择 . . ."></select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="membername"> 本人姓名 </label>
						<div class="col-sm-9">
							<input type="text" name="memberDTO.membername" id="membername" placeholder="" class="col-xs-10 col-sm" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="paperid"> 身份证号码 </label>
						<div class="col-sm-9">
							<input type="text" name="memberDTO.paperid" id="paperid" placeholder="" class="col-xs-10 col-sm" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="relmaster"> 本人家庭关系 </label>
						<div class="col-sm-9">
							<select style="width:200px;" class="chosen-select form-control" id="relmaster" name="memberDTO.relmaster" data-placeholder="请选择 . . ."></select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="masterName"> 户主姓名 </label>
						<div class="col-sm-9">
							<input type="text" name="memberDTO.masterName" id="masterName" placeholder="" class="col-xs-10 col-sm" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="masetPaperid"> 户主身份证号码  </label>
						<div class="col-sm-9">
							<input type="text" name="memberDTO.masetPaperid" id="masetPaperid" placeholder="" class="col-xs-10 col-sm" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" > 来源  </label>
						<div class="col-sm-9">
							<div class="radio">
								<label>
									<input name="memberDTO.ds" type="radio" class="ace" />
									<span class="lbl"> 城市</span> 
								</label>
								<label>
									<input name="memberDTO.ds" type="radio" class="ace" />
									<span class="lbl"> 农村</span>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="address"> 家庭住址 </label>
						<div class="col-sm-8">
							<textarea class="form-control limited" name="memberDTO.address" id="address" maxlength="50"></textarea>
						</div>
					</div>
				</form>	
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
<script src="<%=basePath%>assets/js/ace/elements.scroller.js"></script>
<script src="<%=basePath%>assets/js/jquery.inputlimiter.1.3.1.js"></script>
<script type="text/javascript">
	var scripts = [null,"<%=basePath%>assets/js/date-time/bootstrap-datepicker.js","<%=basePath%>assets/js/jqGrid/jquery.jqGrid.src.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js", null]
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
					setTimeout(function() {
						$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
					}, 0);
				}
		    })
			
			jQuery(grid_selector).jqGrid({
				url : "<%=basePath%>page/html/content/report/queryPersonals.action",
				mtype : "POST", 
				datatype : "json",
				postData : formData,
				height : 321,
				colNames : ['姓名','身份证号码','家庭关系','户主姓名','户主身份证号码'],
				colModel : [
					{name:'pname',formatter:"actionFormatter"},
				    {name:'idno',formatter:"actionFormatter"},
					{name:'col10',formatter:"actionFormatter"},
				    {name:'mastername',formatter:'actionFormatter'},
				    {name:'masterid',formatter:'actionFormatter'}
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
			
			$('textarea.limited').inputlimiter({
				 remText: '可以输入 %n个字,',
				 limitText: '最大输入 : %n 个字.'
			});
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
	
	function add(){
		 var orgid = <%=orgId %>;
		 chosen_Init('2');
		 chosen_onNo("22020201");
		 var dialog = $("#dialog-confirm").removeClass('hide').dialog({
				autoOpen: false,//如果设置为true，则默认页面加载完毕后，就自动弹出对话框；相反则处理hidden状态。 
			    bgiframe: true, //解决ie6中遮罩层盖不住select的问题  
				hide:true,
				resizable: true,
				width: '600',
				modal: true,
				title: "添加人员",
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
								alert( $("#myForm").serialize());
									<%-- $.ajax({
										type : "post",
										dataType : "json",
										url : "<%=basePath%>page/html/content/report/getPInfo.action",
										data: $("#myForm").serialize(),
										async : false,
										success : function(data) {
											
										}
									}); --%>
								} 
							}
						]
			});
		 dialog.dialog("open");
	};
	
	function chosen_Init(type){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/common/getDictionary.action",
			data: {sequ:type},
			async : true,
			success : function(data) {
				var dics = data.dictionary;
				var len = dics.length;
				var a = " ";
				for(var i=0; i<len; i++){
					var value = dics[i].dictkey;
					var text = dics[i].dictvalue;
					a = a + "<option value='"+ value +"'>"+ text +"</option>";
				}
				$("#relmaster").append("<option value=' '> </option>").append(a);
				$("#relmaster").chosen({allow_single_deselect:true});
			}
		});
	};
	
	function chosen_onNo(orgid){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/common/getOrgs.action",
			data: {orgId:orgid},
			async : true,
			success : function(data) {
			    var o = data.districts;
				var len = o.length;
				var a = " ";
				alert(o);
				/* for(var i=0; i<len; i++){
					var value = o[i].districts_id;
					var text = o[i].districts_code+"-"+o[i].districts_name;
					a = a + "<option value='"+ value +"'>"+ text +"</option>";
				} */
				$("#onNo").append("<option value=' '> </option>").append(a);
				$("#onNo").chosen({allow_single_deselect:true});
			}
		});
	}
	
</script>