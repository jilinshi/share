package com.share.dto;

import java.sql.Timestamp;

public class RoleDTO {
	
	private long roleId;
	private Timestamp ctime;
	private String flag;
	private String rolename;
	private Timestamp utime;
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public Timestamp getCtime() {
		return ctime;
	}
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public Timestamp getUtime() {
		return utime;
	}
	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}
	
}
