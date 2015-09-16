package com.share.dto;

import java.math.BigDecimal;

import javax.persistence.Column;

public class VJdOnnoDTO {

	private String onAllname;
	private String onFatherid;
	private String onId;
	private BigDecimal onLevel;
	private String onName;
	private String onNo;
	public String getOnAllname() {
		return onAllname;
	}
	public void setOnAllname(String onAllname) {
		this.onAllname = onAllname;
	}
	public String getOnFatherid() {
		return onFatherid;
	}
	public void setOnFatherid(String onFatherid) {
		this.onFatherid = onFatherid;
	}
	public String getOnId() {
		return onId;
	}
	public void setOnId(String onId) {
		this.onId = onId;
	}
	public BigDecimal getOnLevel() {
		return onLevel;
	}
	public void setOnLevel(BigDecimal onLevel) {
		this.onLevel = onLevel;
	}
	public String getOnName() {
		return onName;
	}
	public void setOnName(String onName) {
		this.onName = onName;
	}
	public String getOnNo() {
		return onNo;
	}
	public void setOnNo(String onNo) {
		this.onNo = onNo;
	}
	
}
