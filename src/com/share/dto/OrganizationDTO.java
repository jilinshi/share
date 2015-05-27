package com.share.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrganizationDTO {
	
	private long orgId;

	private Timestamp ctime;

	private String flag;

	private String orgCode;

	private String orgName;

	private String parentId;

	private Timestamp utime;
	
	private String districtsId;
	
	private String orgNameParent;

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Timestamp getUtime() {
		return utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public String getDistrictsId() {
		return districtsId;
	}

	public void setDistrictsId(String districtsId) {
		this.districtsId = districtsId;
	}

	public String getOrgNameParent() {
		return orgNameParent;
	}

	public void setOrgNameParent(String orgNameParent) {
		this.orgNameParent = orgNameParent;
	}

	

}
