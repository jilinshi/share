package com.share.action;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
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
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(LoginAction.class);
	@Resource
	private YbService ybjkService;
	@Resource
	private SysMgrService sysMgrService;

	private String username;
	private String password;
	private String token;

	public String login() {

		ActionContext ctx = ActionContext.getContext();

		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";

		UserDTO userDTO = new UserDTO();
		userDTO.setUaccount(username);
		userDTO.setUpwds(password);
		userDTO.setToken(token);
		userDTO = ybjkService.findUser(userDTO);
		log.info("======info>>" + username + ">>>");
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
			return "nouser";
		}

	}

	private void saveSyslog(HttpServletRequest request, UserDTO userDTO,
			String info) {
		SysLogDTO l = new SysLogDTO();
		l.setIpaddr(request.getRemoteHost() + ":" + request.getRemotePort()
				+ "<" + request.getRemoteAddr() + "-" + request.getRemoteUser()
				+ ">");
		l.setLogInfo(info);
		l.setUrl(request.getServletPath());
		l.setUserId(new BigDecimal(userDTO.getUserId()));
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
}
