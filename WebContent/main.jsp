<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jquery/demo/demo.css">
<script type="text/javascript" src="<%=basePath%>jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jquery/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var index = 0;
	function addPanel(ctitle,chref) {
		index++;
		var flag = $('#tt').tabs('exists',ctitle);
		if(flag){
			$('#tt').tabs('select',ctitle);
		}else{
			$('#tt').tabs('add', {
				title : ctitle,
				href: chref,
				closable : true
			});
		}
	}
	function removePanel() {
		var tab = $('#tt').tabs('getSelected');
		if (tab) {
			var index = $('#tt').tabs('getTabIndex', tab);
			$('#tt').tabs('close', index);
		}
	}
</script>
<title>医保接口平台</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height: 50px; background: #f5f5f5; padding: 10px">
		<table width="100%" border=0>
			<tr>
				<td align="left"><strong> <font size="3"
						color="#336699">吉林市医保接口数据核对平台 </font></strong></td>
				<td align="right"><span><fontcolor="#336699">注销</font></span></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'west',split:true,title:'功能菜单'"
		style="width: 150px;">
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-add'" onclick="addPanel()"></a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-remove'"
			onclick="removePanel()"></a> -->

		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="数据核对" style="padding: 10px;" data-options="selected:true">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-mini-add'" onclick="addPanel('人员信息核对','page/check/querymemberinfoinit.action')">
					人员信息核对</a> <a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-mini-add'" onclick="addPanel('慢性病状态核对','')">慢性病状态核对</a>
			</div>				
			<div title="查询分析" style="padding: 10px">
				查询分析
			</div>
		</div>
	</div>
	<div
		data-options="region:'east',split:true,collapsed:true,title:'Building'"
		style="width: 100px; padding: 10px;">Building</div>
	<div data-options="region:'south',border:false"
		style="height: 40px; background: #f5f5f5; padding: 5px;">
		<table width="100%" border=0 align="center">
			<tr>
				<td align="center"><font size="1" color="#336699">版权所有：吉林明达软件有限责任公司
						吉林市社会救助局</font></td>
			</tr>
		</table>
	</div>
	<div id="tt" class="easyui-tabs"
		data-options="region:'center',iconCls:'icon-man',title:'登录机构：220200000&nbsp;&nbsp;用户：2292992313'"></div>
</body>
</html>