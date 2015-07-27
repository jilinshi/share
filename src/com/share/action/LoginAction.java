package com.share.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.MenuDTO;
import com.share.dto.SysLogDTO;
import com.share.dto.UserDTO;
import com.share.service.SysMgrService;
import com.share.service.YbService;
import com.share.util.GenMenu;

@Controller
public class LoginAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, SessionAware {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(LoginAction.class);

	private HttpServletResponse response;
	private HttpServletRequest request;
	

	@Resource
	private YbService ybjkService;
	@Resource
	private SysMgrService sysMgrService;

	private String username;
	private String password;
	private String token;
	private String ace;
	private String result;

	public String login() {

		ActionContext ctx = ActionContext.getContext();

		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		operCookie(request);

		UserDTO userDTO = new UserDTO();
		userDTO.setUaccount(username);
		userDTO.setUpwds(password);
		userDTO.setToken(token);
		userDTO = ybjkService.findUser(userDTO);
		if (null != userDTO) {
			ActionContext.getContext().getSession().put("user", userDTO);

			log.info("===========" + userDTO.getUserId()
					+ "==========================");
			List<MenuDTO> menus = ybjkService.findMenusByUser(userDTO);
			log.info("===========菜单数量=====" + menus.size()
					+ "==========================" + menus.get(0).getMenuname());

			String info = "操作:用户登录-登录名:aaa-结果:成功";
			saveSyslog(request, userDTO, info);

			GenMenu gm = new GenMenu();
			String menuinfo = gm.getMenuStr(menus);
			menuinfo = menuinfo.replace("***", " ");
			menuinfo = menuinfo.replace("*u*", basePath);
			ActionContext.getContext().getSession().put("menuinfo", menuinfo);
			return SUCCESS;
		} else {

			String info = "操作:用户登录-登录名:aaa-结果:失败";
			saveSyslog(request, userDTO, info);
			result = "用户名或密码错误，请重新登陆！";
			return "result";
		}

	}

	private void operCookie(HttpServletRequest request) {
		if ("1".equals(ace)) {
			Cookie cookie = new Cookie("user.cookie", username + "," + password);
			// System.out.println("添加cookie");
			cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周
			response.addCookie(cookie);// 添加cookie到response中
		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("user.cookie".equals(cookie.getName())) {
						cookie.setValue("");
						cookie.setMaxAge(0);
					}
				}
			}

		}
	}

	public String logout() {
		return SUCCESS;
	}

	private void saveSyslog(HttpServletRequest request, UserDTO userDTO,
			String info) {
		SysLogDTO l = new SysLogDTO();
		l.setIpaddr(request.getRemoteHost() + ":" + request.getRemotePort()
				+ "<" + request.getRemoteAddr() + "-" + request.getRemoteUser()
				+ ">");
		l.setLogInfo(info);
		l.setUrl(request.getServletPath());
		if(userDTO!=null){
			l.setUserId(new BigDecimal(userDTO.getUserId()));
		}
		sysMgrService.saveSysLogs(l);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAce() {
		return ace;
	}

	public void setAce(String ace) {
		this.ace = ace;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {

	}


	@Override
	public void setServletRequest(HttpServletRequest request ) {
		this.request=request;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
