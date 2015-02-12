package com.share.dto;

import java.math.BigDecimal;

public class UserDTO {

	private String username;
	private String password;
	private String account;
	private String empidcard;
	private String employeeId;
	private String idcard;
	private String ipaddr;
	private BigDecimal isadmin;
	private String organizationId;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmpidcard() {
		return empidcard;
	}

	public void setEmpidcard(String empidcard) {
		this.empidcard = empidcard;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public BigDecimal getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(BigDecimal isadmin) {
		this.isadmin = isadmin;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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

}
