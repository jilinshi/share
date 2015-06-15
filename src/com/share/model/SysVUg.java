package com.share.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the SYS_V_UG database table.
 * 
 */
@Entity
@Table(name="SYS_V_UG")
@NamedQuery(name="SysVUg.findAll", query="SELECT s FROM SysVUg s")
public class SysVUg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=256)
	private String idno;

	@Column(length=256)
	private String mobilephone;

	@Column(name="ORG_ID", precision=12)
	private BigDecimal orgId;
	
	@Column(length=256)
	private String suctime;

	@Column(length=256)
	private String suflag;

	@Column(length=256)
	private String surctime;

	@Column(length=128)
	private String surflag;

	@Column(length=256)
	private String surutime;

	@Column(length=256)
	private String suutime;

	@Column(length=256)
	private String uaccount;

	@Column(name="UG_ID", precision=12)
	private BigDecimal ugId;

	@Column(name="UG_NAME", length=128)
	private String ugName;

	@Column(name="UG_PID", precision=12)
	private BigDecimal ugPid;

	@Column(length=256)
	private String uname;

	@Column(length=256)
	private String upwds;
	@Id
	@Column(name="USER_ID", nullable=false, precision=12)
	private BigDecimal userId;

	public SysVUg() {
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public BigDecimal getOrgId() {
		return this.orgId;
	}

	public void setOrgId(BigDecimal orgId) {
		this.orgId = orgId;
	}

	public Object getSuctime() {
		return this.suctime;
	}

	public void setSuctime(String suctime) {
		this.suctime = suctime;
	}

	public String getSuflag() {
		return this.suflag;
	}

	public void setSuflag(String suflag) {
		this.suflag = suflag;
	}

	public Object getSurctime() {
		return this.surctime;
	}

	public void setSurctime(String surctime) {
		this.surctime = surctime;
	}

	public String getSurflag() {
		return this.surflag;
	}

	public void setSurflag(String surflag) {
		this.surflag = surflag;
	}

	public Object getSurutime() {
		return this.surutime;
	}

	public void setSurutime(String surutime) {
		this.surutime = surutime;
	}

	public Object getSuutime() {
		return this.suutime;
	}

	public void setSuutime(String suutime) {
		this.suutime = suutime;
	}

	public String getUaccount() {
		return this.uaccount;
	}

	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}

	public BigDecimal getUgId() {
		return this.ugId;
	}

	public void setUgId(BigDecimal ugId) {
		this.ugId = ugId;
	}

	public String getUgName() {
		return this.ugName;
	}

	public void setUgName(String ugName) {
		this.ugName = ugName;
	}

	public BigDecimal getUgPid() {
		return this.ugPid;
	}

	public void setUgPid(BigDecimal ugPid) {
		this.ugPid = ugPid;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwds() {
		return this.upwds;
	}

	public void setUpwds(String upwds) {
		this.upwds = upwds;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

}