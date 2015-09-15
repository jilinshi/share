<%@page import="com.share.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserDTO user =(UserDTO) session.getAttribute("user");
	Long orgId=user.getOrgId();
	String uaccout=user.getUaccount();
	if("admin".equals(uaccout)){
		uaccout=orgId+"";
	}
%>
<title>吉林市信息共享平台</title>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0"> 
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
					<label for="form-field-select-hd1">机构</label>
					<select style="width:200px;" class="chosen-select" id="form-field-select-hd1" name="memberDTO.onNo" data-placeholder="请选择 . . .">
					</select>
					<label>身份证号码</label>
					<input type="text" class="input-large" placeholder="身份证号码" name="memberDTO.paperid"/>
					<label>标识</label>
					<select style="width:200px;" class="chosen-select" id="form-field-select-hd2" name="memberDTO.remark" data-placeholder="请选择 . . .">
					</select>
					<input type="hidden" name="memberDTO.orgid" value="<%=uaccout %>" />
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
							<select style="width:250px;" class="chosen-select form-control" id="onNo" name="memberDTO.onNo" data-placeholder="请选择 . . ."></select>
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
							<input type="text" name="memberDTO.paperid" id="paperid" placeholder="" class="col-xs-10 col-sm" onBlur="chang_paperid(this);"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="relmaster"> 本人家庭关系 </label>
						<div class="col-sm-9">
							<select style="width:250px;" class="chosen-select form-control" id="relmaster" name="memberDTO.relmaster" data-placeholder="请选择 . . ."></select>
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
							<input type="text" name="memberDTO.masetPaperid" id="masetPaperid" placeholder="" class="col-xs-10 col-sm" onBlur="chang_masetPaperid(this);" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" > 来源  </label>
						<div class="col-sm-9">
							<div class="radio">
								<label>
									<input id="1" name="memberDTO.ds" type="radio" class="ace" value="1"/>
									<span class="lbl"> 城市</span> 
								</label>
								<label>
									<input id="2" name="memberDTO.ds" type="radio" class="ace" value="2"/>
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
					<div class="form-group">
							<div id="message" align="center" style="color:#F00"></div>
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
			chosen_on();
			chosen_remark();
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
				colNames : ['','家庭编号','姓名','身份证号码','家庭关系','户主姓名','户主身份证号码','人员状态','标识','操作'],
				colModel : [
					{name:'piId',formatter:"actionFormatter",hidden:true},
					{name:'col1',formatter:"actionFormatter"},
					{name:'pname',formatter:"actionFormatter"},
				    {name:'idno',formatter:"actionFormatter"},
					{name:'col10',formatter:"actionFormatter"},
				    {name:'mastername',formatter:'actionFormatter'},
				    {name:'masterid',formatter:'actionFormatter'},
				    {name:'col3',formatter:'actionFormatter'},
				    {name:'remark',formatter:'actionFormatter',cellattr: addCellAttr_color},
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
				  var viewBtn ="<div class='hidden-sm hidden-xs btn-group'><button id='v_"+rowData.piId+"' class='btn btn-minier btn-success' onclick='extract("+rowData.piId+")'><i class='ace-icon fa fa-sign-out bigger-100'></i>核对</button></div>"
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
        jQuery("#grid-table").setGridParam({postData : jsonuserinfo,page : 1}).trigger("reloadGrid");
	};
	
	function add(){
		 var orgid = <%=uaccout%>;
		 chosen_Init('2');
		 chosen_onNo(orgid);
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
									$("#paperid").attr("value","");
									$("#membername").attr("value","");
							    	$("#masterName").attr("value","");
							    	$("#masetPaperid").attr("value","");
							    	$("#address").val("");
									$("#onNo").chosen("destroy");
									$('input:radio[name="memberDTO.ds"]').removeAttr("checked");
									$('#relmaster option[value=" "]').attr("selected","selected");
							    	$("#relmaster").trigger("chosen:updated");
							    	$('#onNo option[value=" "]').attr("selected","selected");
							    	$("#onNo").trigger("chosen:updated");
									$("#relmaster").chosen("destroy");
									document.getElementById('message').innerHTML="";
									$( this ).dialog( "close" );
								} 
							},
							{
								text: "保存",
								"class" : "btn btn-primary btn-minier",
								id:"save",
								click: function() {
									var flag = true;
									var paperid = $("#paperid").val();
									var membername = $("#membername").val();
							    	var mastername = $("#masterName").val();
							    	var masterpaperid = $("#masetPaperid").val();
							    	var ds = $('input:radio[name="memberDTO.ds"]:checked').val();
							    	var onNo=$("#onNo").val();
							    	var relmaster=$("#relmaster").val();
							    	var address=$("#address").val();
							    	if(onNo==" "){
							    		alert("请选择所在辖区！")
										flag = false;
										return flag;
							    	}
							    	if(membername==""){
										alert("请填写本人姓名！")
										flag = false;
										return flag;
									}
							    	if(paperid==""){
										alert("请填写本人身份证号码！")
										flag = false;
										return flag;
									}
							    	if(relmaster==" "){
							    		alert("请选择家庭关系！")
										flag = false;
										return flag;
							    	}
							    	if(mastername==""){
										alert("请填写户主姓名！")
										flag = false;
										return flag;
									}
							    	if(masterpaperid==""){
										alert("请填写户主身份证号码！")
										flag = false;
										return flag;
									}
							    	if(ds==1||ds==2){
							    	}else{
							    		alert("请选择来源！")
										flag = false;
										return flag;
							    	}
							    	if(address==""){
										alert("请填写家庭住址！")
										flag = false;
										return flag;
									}
									if(flag){
										$.ajax({
											type : "post",
											dataType : "json",
											url : "<%=basePath%>page/html/content/report/savePInfo.action",
											data: $("#myForm").serialize(),
											async : true,
											success : function(data) {
												var msg = data.msg;
												alert(msg);
												dialog.dialog( "close" );
											}
										});
									}
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
					var value = dics[i].dictvalue;
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
			url : "<%=basePath%>page/html/content/report/getVJdOnnoList.action",
			data: {orgId:orgid},
			async : true,
			success : function(data) {
			    var o = data.vjdonnos;
				var len = o.length;
				var a = " ";
				 for(var i=0; i<len; i++){
					var value = o[i].onNo;
					var text = o[i].onNo+"-"+o[i].onName;
					a = a + "<option value='"+ value +"'>"+ text +"</option>";
				}
				$("#onNo").append("<option value=' '> </option>").append(a);
				$("#onNo").chosen({allow_single_deselect:true});
			}
		});
	};
	
	function chang_paperid(v){
		var idcard = v.value;
		var len = idcard.length;
		if(len==0){
			alert("请输入身份证号码！");
		}else{
			$.ajax({
				type : "post",
				dataType : "json",
				url : "<%=basePath%>page/html/content/report/valPeperid.action",
				data: {idcard:idcard},
				async : true,
				success : function(data) {
					var result = data.result;
				    var o = data.msg;
				    if(result==1){
				    	var m = data.mems;
				    	$('#relmaster option[value='+m.relmaster+']').attr("selected","selected");
				    	$("#relmaster").trigger("chosen:updated");
				    	$('#onNo option[value='+m.onNo.substring(0,10)+']').attr("selected","selected");
				    	$("#onNo").trigger("chosen:updated");
				    	$('input:radio[name="memberDTO.ds"][value='+m.ds+']').attr("checked",'checked');
				    	$("#membername").attr("value",m.membername);
				    	$("#masterName").attr("value",m.masterName);
				    	$("#masetPaperid").attr("value",m.masetPaperid);
				    	$("#address").val(m.address);
				    	$("#save").attr("disabled",true);
				    	document.getElementById('message').innerHTML="此人已存在，不能重复录入！";
				    }else if(result==2){
				    		
					}else if(result==3){
				    	alert(o);
				    }
				}
			});
		}
	};
	
	function chang_masetPaperid(v){
		var idcard = v.value;
		var len = idcard.length;
		if(len==0){
			alert("请输入户主身份证号码！");
		}else{
			$.ajax({
				type : "post",
				dataType : "json",
				url : "<%=basePath%>page/html/content/report/valPeperid.action",
				data: {idcard:idcard},
				async : true,
				success : function(data) {
					var result = data.result;
				    var o = data.msg;
				    if(result==1){
				    	var m = data.mems;
				    	$('#relmaster option[value='+m.relmaster+']').attr("selected","selected");
				    	$("#relmaster").trigger("chosen:updated");
				    	$('#onNo option[value='+m.onNo.substring(0,10)+']').attr("selected","selected");
				    	$("#onNo").trigger("chosen:updated");
				    	$('input:radio[name="memberDTO.ds"][value='+m.ds+']').attr("checked",'checked');
				    	$("#membername").attr("value",m.membername);
				    	$("#masterName").attr("value",m.masterName);
				    	$("#paperid").attr("value",m.paperid);
				    	$("#address").val(m.address);
				    	$("#save").attr("disabled",true);
				    	document.getElementById('message').innerHTML="此人已存在，不能重复录入！";
				    }else if(result==2){
				    		
					}else if(result==3){
				    	alert(o);
				    }
				}
			});
		}
	};
	
	function extract(id){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/report/getExtract.action",
			data: {piId:id},
			async : false,
			success : function(data) {
				var msg = data.msg;
				alert(msg);
				var jsonuserinfo = $('#searchform').serializeObject();  
		        jQuery("#grid-table").setGridParam({postData : jsonuserinfo,page : 1}).trigger("reloadGrid");
			}
			
		});
	};
	function chosen_on(){
    	$('#form-field-select-hd1 option[value=" "]').attr("selected","selected");
    	$("#form-field-select-hd1").trigger("chosen:updated");
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/report/getVJdOnnoList.action",
			success : function(data) {
				var vjdonnos = data.vjdonnos;
				var len = vjdonnos.length;
				var a = " ";
				for(var i=0; i<len; i++){
					var value = vjdonnos[i].onNo;
					var text = vjdonnos[i].onName;
					a = a + "<option value='"+ value +"'>"+ text +"</option>";
				}
				
				$("#form-field-select-hd1").append("<option value=' '> </option>").append(a);
				$("#form-field-select-hd1").chosen({allow_single_deselect:true});
				
			}
		});
	};
	function chosen_remark(){
		$.ajax({
			type : "post",
			dataType : "json",
			url : "<%=basePath%>page/html/content/report/getRemarkList.action",
			success : function(data) {
				var remarks = data.remarks;
				var len = remarks.length;
				var a = " ";
				for(var i=0; i<len; i++){
					var value = remarks[i].remarkid;
					var text = remarks[i].remark;
					a = a + "<option value='"+ value +"'>"+ text +"</option>";
				}
				
				$("#form-field-select-hd2").append("<option value=' '> </option>").append(a);
				$("#form-field-select-hd2").chosen({allow_single_deselect:true});
				
			}
		});
	};
	function returnLogin(){
		window.open('<%=basePath%>login.jsp', '吉林市社会救助局信息共享平台');
		window.open('', '_self');
	};
</script>