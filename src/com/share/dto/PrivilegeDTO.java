package com.share.dto;

import java.sql.Timestamp;

public class PrivilegeDTO {

	private long privilegeId;
	private Timestamp ctime;
	private String flag;
	private String privicode;
	private String priviname;
	private String remark;
	
	public long getPrivilegeId() {
		return privilegeId;
	}
	public void setPrivilegeId(long privilegeId) {
		this.privilegeId = privilegeId;
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
	public String getPrivicode() {
		return privicode;
	}
	public void setPrivicode(String privicode) {
		this.privicode = privicode;
	}
	public String getPriviname() {
		return priviname;
	}
	public void setPriviname(String priviname) {
		this.priviname = priviname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
