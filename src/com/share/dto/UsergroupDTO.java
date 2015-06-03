package com.share.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UsergroupDTO {
	
	private long ugId;

	private Timestamp ctime;

	private String flag;

	private String ugName;

	private BigDecimal ugPid;

	private Timestamp utime;

	public long getUgId() {
		return ugId;
	}

	public void setUgId(long ugId) {
		this.ugId = ugId;
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

	public String getUgName() {
		return ugName;
	}

	public void setUgName(String ugName) {
		this.ugName = ugName;
	}

	public BigDecimal getUgPid() {
		return ugPid;
	}

	public void setUgPid(BigDecimal ugPid) {
		this.ugPid = ugPid;
	}

	public Timestamp getUtime() {
		return utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}
	
	
}
