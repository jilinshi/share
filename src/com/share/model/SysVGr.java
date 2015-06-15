package com.share.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the SYS_V_GR database table.
 * 
 */
@Entity
@Table(name="SYS_V_GR")
@NamedQuery(name="SysVGr.findAll", query="SELECT s FROM SysVGr s")
public class SysVGr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=128)
	private String flag;

	@Column(name="ROLE_ID", nullable=false, precision=12)
	private BigDecimal roleId;

	@Column(length=128)
	private String rolename;

	@Column(length=256)
	private String srctime;

	@Column(length=256)
	private String srutime;

	@Column(length=256)
	private String surctime;

	@Column(length=128)
	private String surflag;

	@Column(length=256)
	private String surutime;
	@Id
	@Column(name="UG_ID", nullable=false, precision=12)
	private BigDecimal ugId;

	@Column(name="UG_NAME", length=128)
	private String ugName;

	@Column(name="UG_PID", precision=12)
	private BigDecimal ugPid;

	public SysVGr() {
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public Object getSrutime() {
		return this.srutime;
	}

	public void setSrutime(String srutime) {
		this.srutime = srutime;
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

}