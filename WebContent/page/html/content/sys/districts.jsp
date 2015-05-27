<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<title>吉林市信息共享平台</title>
<link rel="stylesheet" href="<%=basePath %>assets/plugins/jqx/styles/jqx.base.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>assets/plugins/jqx/styles/jqx.energyblue.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>assets/plugins/style.css" rel="stylesheet" type="text/css" />
<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-sm-3">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">行政区划列表</h4>
			</div>
			<div class="widget-body" style="border: none;">
				<div class="widget-toolbox" id="widget-toolbox-1">
					<div class="btn-toolbar">
						<div class="btn-group">
							<button class="btn btn-sm btn-success btn-white btn-round" id="ExpandAll">
								<i class="ace-icon fa fa-plus green"></i>展开
							</button>
						</div>
						<div class="btn-group">
							<button class="btn btn-sm btn-success btn-white btn-round" id="CollapseAll">
								<i class="ace-icon fa fa-minus green"></i>合并
							</button>
						</div>
						<div class="btn-group">
							<button class="btn btn-sm btn-success btn-white btn-round" id="Remove">
								<i class="ace-icon fa fa-close green"></i>删除
							</button>
						</div>
					</div>
				</div>
				<div class="widget-main padding-8" style=" overflow: hidden; border: none;" id='jqxTree'>
					<div data-spy="scroll" data-offset="0" class="scrollspy-example" style="height: 340px;">
						<div id="myspin" style="display: ''"><i class="ace-icon fa fa-spinner fa-spin orange bigger-200"></i></div>
						<div id="mytree" style="display: none">
						<ul id='treebody'>
							<!-- 菜单树 -->
						</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-9">
		<div class="widget-box widget-color-blue2">
			<div class="widget-header">
				<h4 class="widget-title lighter smaller">添加行政区划</h4>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-8"  style="height: 400px;">
					<form class="form-horizontal" role="form" id="myForm">
						<div class="space-4"></div>
						<br/>
						<!-- #section:elements.form -->
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 所属辖区行政区划名称 </label>

							<div class="col-sm-9">
								<input type="text" id="text_org" placeholder="请从行政区划列表中选择..." class="col-xs-10 col-sm-7" disabled/>
								<input type="hidden" id="text_hidden" name="districtsDTO.text" placeholder="请从行政区划列表中选择..." class="col-xs-10 col-sm-7" />
							</div>
						</div>
						<br/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 所属辖区行政区划编号 </label>

							<div class="col-sm-9">
								<input type="text" id="districtsId" placeholder="所属辖区行政区划编号" class="col-xs-10 col-sm-7" disabled/>
								<input type="hidden" id="districtsId_hidden" name="districtsDTO.districtsId" placeholder="所属辖区行政区划编号" class="col-xs-10 col-sm-7"/>
							</div>
						</div>
						<br/>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 新增行政区划名称 </label>

							<div class="col-sm-9">
								<input type="text" id="districtsNmae" name="districtsDTO.districtsNmae" placeholder="请输入行政区划名称..." class="col-xs-10 col-sm-7" onBlur="v_districtsNmae()"/>
							</div>
						</div>
						<br/><br/><br/>
						<div class="clearfix form-actions" >
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-info" type="button" id="sub">
									<i class="ace-icon fa fa-check bigger-110"></i>
									保存
								</button>

								&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset">
									<i class="ace-icon fa fa-undo bigger-110"></i>
									重置
								</button>
							</div>
						</div>
						<!-- /section:elements.form -->
					</form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
			var $path_base = "<%=basePath%>";//in Ace demo this will be used for editurl parameter
		</script>
</div>
<script type="text/javascript">
var scripts = [null,"<%=basePath %>assets/plugins/jqx/jqxcore.js","<%=basePath %>assets/plugins/jqx/jqxexpander.js","<%=basePath %>assets/plugins/jqx/jqxbuttons.js","<%=basePath %>assets/plugins/jqx/jqxscrollbar.js","<%=basePath %>assets/plugins/jqx/jqxtree.js","<%=basePath %>assets/plugins/jqx/jqxcheckbox.js","<%=basePath%>assets/js/jqGrid/i18n/grid.locale-cn.js", null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		jQuery(function($) {
			InitTree();
		});
		function InitTree(){
			 $.ajaxSetup({cache:false});
			 $.ajax({ 
		            type: "post", 
		            url: "<%=basePath%>page/html/content/sys/findDistrictList.action",
					dataType : "json",
					lang:{loading:"数据加载中……"},  
					async : false, //必须同步等返回值
					success : function(data) {
						var myspin = $("#myspin");
						var mytree = $("#mytree");
						mytree.css("display","none");
						var myData = data.districts;
						var li = "";
						 $.each(myData, function(key, val) {	
							if(val.parentId=="-1"){
								//设置一级菜单
								li+= "<li style='border: solid 0px;' id='li_0-"+val.districtsId+"' item-expanded='true'><i class='ace-icon fa fa-home blue'>&nbsp;</i>"+val.districtsNmae+"<ul id='ul_0-"+val.districtsId+"'></ul></li>";
							}
						});
						 //置于ul中
						$("#treebody").append(li);
						 //设置二级菜单
						$.each(myData, function(key, val) {
							var v =  val.districtsId;
							if(v.length==6){
								var cli = " <li style='border: solid 0px;' id='li_1-"+val.districtsId+"'><i class='ace-icon fa fa-folder orange'>&nbsp;</i>"+val.districtsNmae+"<ul id='ul_1-"+val.districtsId+"'></ul></li> ";
								$("#ul_0-"+val.parentId).append(cli);
							}
							
						}); 
						//设置三级菜单
						$.each(myData, function(key, val) {
							var v =  val.districtsId;
							if(v.length==8){
								var cli = " <li style='border: solid 0px;' id='li_2-"+val.districtsId+"'><i class='ace-icon fa fa-folder green'>&nbsp;</i>"+val.districtsNmae+"<ul id='ul_2-"+val.districtsId+"'></ul></li> ";
								$("#ul_1-"+val.parentId).append(cli);
							}
						}); 
						//设置三级菜单
						$.each(myData, function(key, val) {
							var v =  val.districtsId;
							if(v.length==10){
								var cli = " <li style='border: solid 0px;' id='li_3-"+val.districtsId+"'><i class='ace-icon fa fa-circle-o pink'>&nbsp;</i>"+val.districtsNmae+"</li> ";
								$("#ul_2-"+val.parentId).append(cli);
							}
						});
	 					//创建 jqxTree
				        $('#jqxTree').jqxTree({ theme: 'energyblue'});
				        $('#jqxTree').css('visibility', 'visible');
				        $('#jqxTree').on('select', function (event) {
				        	var args = event.args;
			                var item = $('#jqxTree').jqxTree('getItem', args.element);
			                 if(item.label != null){
			                	 var id = item.id;
					             $('#text_org').val(item.label);
					             $('#districtsId').val(id.split("-")[1]);
					             $('#text_hidden').val(item.label);
					             $('#districtsId_hidden').val(id.split("-")[1]);
			                 }
			            });
				        myspin.css("display","none");
				        mytree.css("display","");

					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown);
					}
					
				});
		}
		// Expand All
        $('#ExpandAll').click(function () {
           $('#jqxTree').jqxTree('expandAll');
        });

        // Collapse All
        $('#CollapseAll').click(function () {
            $('#jqxTree').jqxTree('collapseAll');
        });
        
        //
        $('#Remove').click(function () {
        	var districtsId = $("#districtsId").val();
        	if(districtsId==""){
        		alert("请选择行政区划！");
        	}else{
	        	$.ajax({ 
		            type: "post", 
		            url: "<%=basePath%>page/html/content/sys/removeDistricts.action",
					dataType : "json",
					async : false, //必须同步等返回值
					data : {districtsId:districtsId},
					success : function(data) {
						var msg = data.msg;
						alert(msg);	
						location.reload();
					}
	    	 	});
        	}
        });
        
        //保存
        $("#sub").click(function (){
        	var text_org = $("#text_org").val();
        	var districtsId = $("#districtsId").val();
        	var districtsNmae = $("#districtsNmae").val();
        	var flag = true;
        	if(text_org==""){
        		alert("所属辖区行政区划名称不能为空，请从行政区划列表中选择！");
        		flag = false;
        		return flag;
        	}else{
        		if(districtsId.length==10){
        			alert("不能创建新行政区划！");
        			flag = false;
        			return flag;
        		}
        	}
        	if(districtsNmae==""){
        		alert("请填写新增行政区划名称！");
        		flag = false;
        		return flag;
        	}
        	if(flag){
        	  $.ajax({ 
		            type: "post", 
		            url: "<%=basePath%>page/html/content/sys/saveDistricts.action",
					dataType : "json",
					async : false, //必须同步等返回值
					data : $("#myForm").serialize(),
					success : function(data) {
						var msg = data.msg;
						alert(msg);	
						location.reload();
					}
        	 });
        	}
        });
	});
function v_districtsNmae(){
	var districtsNmae = $("#districtsNmae").val();
	if(districtsNmae != ""){
		$.ajax({ 
	        type: "post", 
	        url: "<%=basePath%>page/html/content/sys/findDistricts.action",
			dataType : "json",
			async : false, //必须同步等返回值
			data : $("#myForm").serialize(),
			success : function(data) {
				var ds = data.ds;
				var r = data.r;
				if(r=="1"){
					$("#sub").attr("disabled",true);
					alert("行政区划编号：" + ds.districtsCode + ", 行政区划名称：" + ds.districtsNmae + ",已经存在！");
				}else if(r=="0"){
					$("#sub").attr("disabled",false);
				}
			}
	        
	 	});
	}
}
</script>