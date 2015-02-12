package com.share.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.share.dto.UserDTO;

public class AuthorityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 2995616862370342572L;

	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext ctx = invocation.getInvocationContext();

		Map session = ctx.getSession();
		UserDTO user = (UserDTO) session.get("user");
		if (null == user) {
			return Action.LOGIN;
		} else {
			return invocation.invoke();
		}
	}

}
