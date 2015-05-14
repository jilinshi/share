<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>授权书</title>
</head>
<body>
	<img
		src="<%=basePath%>page/html/content/printreport/queryAttorneysByMaid.action?imgname=WT-<s:property value="masterid"/>">
	<s:iterator value="memberDTOs">
		<img
			src="<%=basePath%>page/html/content/printreport/queryAttorneysByMaid.action?imgname=A-<s:property value="paperid"/>">
		<img
			src="<%=basePath%>page/html/content/printreport/queryAttorneysByMaid.action?imgname=B-<s:property value="paperid"/>">
	</s:iterator>
</body>
</html>