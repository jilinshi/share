package com.share.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.share.service.YbService;

public class OperationlogInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 5538146788690142747L;
	@Resource
	private YbService ybjkService;

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();

		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		System.out.println(request.getServletPath());
		//插入操作日志
		return invocation.invoke();
	}

}
