package com.share.interceptor;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.share.dto.SysLogDTO;
import com.share.dto.UserDTO;
import com.share.service.SysMgrService;

public class OperationlogInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 5538146788690142747L;
	@Resource
	private SysMgrService sysMgrService;

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);

		if (!"/login.action".equals(request.getServletPath())) {
			HttpSession session = request.getSession();
			UserDTO userDTO = (UserDTO) session.getAttribute("user");
			SysLogDTO l = new SysLogDTO();
			l.setIpaddr(request.getRemoteHost() + ":" + request.getRemotePort()
					+ "<" + request.getRemoteAddr() + "-"
					+ request.getRemoteUser() + ">");
			l.setLogInfo("操作:功能操作-登录名:" + userDTO.getUaccount() + "-");
			l.setUrl(request.getServletPath());
			l.setUserId(new BigDecimal(userDTO.getUserId()));
			sysMgrService.saveSysLogs(l);
		}

		return invocation.invoke();
	}

}
