package com.share.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the SYS_V_UR database table.
 * 
 */
@Entity
@Table(name="SYS_V_UR")
@NamedQuery(name="SysVUr.findAll", query="SELECT s FROM SysVUr s")
public class SysVUr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=256)
	private String idno;

	@Column(length=256)
	private String mobilephone;

	@Column(name="ORG_ID", precision=12)
	private BigDecimal orgId;

	@Column(name="ROLE_ID", nullable=false, precision=12)
	private BigDecimal roleId;

	@Column(length=128)
	private String rolename;

	@Column(length=256)
	private String srctime;

	@Column(length=128)
	private String srflag;

	@Column(length=256)
	private String srutime;

	@Column(length=256)
	private String suctime;

	@Column(length=256)
	private String suflag;

	@Column(length=256)
	private String suutime;

	@Column(length=256)
	private String uaccount;

	@Column(length=256)
	private String uname;

	@Column(length=256)
	private String upwds;
	@Id
	@Column(name="USER_ID", nullable=false, precision=12)
	private BigDecimal userId;

	public SysVUr() {
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

	public BigDecimal getRoleId() {
		return this.roleId;
	}

	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Object getSrctime() {
		return this.srctime;
	}

	public void setSrctime(String srctime) {
		this.srctime = srctime;
	}

	public String getSrflag() {
		return this.srflag;
	}

	public void setSrflag(String srflag) {
		this.srflag = srflag;
	}

	public Object getSrutime() {
		return this.srutime;
	}

	public void setSrutime(String srutime) {
		this.srutime = srutime;
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