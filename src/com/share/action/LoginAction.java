package com.share.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.UserDTO;
import com.share.service.YbService;

@Controller
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(LoginAction.class);
	@Resource
	private YbService ybjkService;

	private String username;
	private String password;
	private String token;

	public String login() {
		UserDTO userDTO = new UserDTO();
		userDTO.setAccount(username);
		userDTO.setPassword(password);
		userDTO.setToken(token);
		userDTO = ybjkService.findUser(userDTO);
		log.info("======info>>" + username + ">>>" + password);
		if (null != userDTO) {
			ActionContext.getContext().getSession().put("user", userDTO);
			return SUCCESS;
		} else {
			return "nouser";
		}

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
