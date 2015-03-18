<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	if (session.getAttribute("user") != null) {
		session.removeAttribute("user");
	}
	session.invalidate();
	response.sendRedirect(basePath + "login.jsp");
%>
