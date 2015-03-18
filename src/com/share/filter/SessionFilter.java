package com.share.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.share.dto.UserDTO;

public class SessionFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = ((HttpServletRequest) arg0).getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user != null) {
			//System.out.println(user.getUaccount());
			arg2.doFilter(arg0, arg1);
		} else {
			//System.out.println(request.getServletPath());
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			response.sendRedirect(basePath + "logout.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
