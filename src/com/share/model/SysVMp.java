package com.share.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the SYS_V_MP database table.
 * 
 */
@Entity
@Table(name="SYS_V_MP")
@NamedQuery(name="SysVMp.findAll", query="SELECT s FROM SysVMp s")
public class SysVMp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=128)
	private String ico;
	@Id
	@Column(name="MENU_ID", nullable=false, precision=12)
	private BigDecimal menuId;

	@Column(length=128)
	private String menucode;

	@Column(length=128)
	private String menuname;

	@Column(length=128)
	private String menuurl;

	@Column(name="PM_ID", precision=12)
	private BigDecimal pmId;

	@Column(length=128)
	private String privicode;

	@Column(name="PRIVILEGE_ID", nullable=false, precision=12)
	private BigDecimal privilegeId;

	@Column(length=128)
	private String priviname;

	@Column(length=256)
	private String smctime;

	@Column(length=128)
	private String smflag;

	@Column(length=128)
	private String smremark;

	@Column(length=256)
	private String spctime;

	@Column(length=128)
	private String spflag;

	@Column(length=128)
	private String spremark;

	@Column(length=256)
	private String utime;

	public SysVMp() {
	}

	public String getIco() {
		return this.ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public BigDecimal getMenuId() {
		return this.menuId;
	}

	public void setMenuId(BigDecimal menuId) {
		this.menuId = menuId;
	}

	public String getMenucode() {
		return this.menucode;
	}

	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}

	public String getMenuname() {
		return this.menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenuurl() {
		return this.menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public BigDecimal getPmId() {
		return this.pmId;
	}

	public void setPmId(BigDecimal pmId) {
		this.pmId = pmId;
	}

	public String getPrivicode() {
		return this.privicode;
	}

	public void setPrivicode(String privicode) {
		this.privicode = privicode;
	}

	public BigDecimal getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(BigDecimal privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPriviname() {
		return this.priviname;
	}

	public void setPriviname(String priviname) {
		this.priviname = priviname;
	}

	public Object getSmctime() {
		return this.smctime;
	}

	public void setSmctime(String smctime) {
		this.smctime = smctime;
	}

	public String getSmflag() {
		return this.smflag;
	}

	public void setSmflag(String smflag) {
		this.smflag = smflag;
	}

	public String getSmremark() {
		return this.smremark;
	}

	public void setSmremark(String smremark) {
		this.smremark = smremark;
	}

	public Object getSpctime() {
		return this.spctime;
	}

	public void setSpctime(String spctime) {
		this.spctime = spctime;
	}

	public String getSpflag() {
		return this.spflag;
	}

	public void setSpflag(String spflag) {
		this.spflag = spflag;
	}

	public String getSpremark() {
		return this.spremark;
	}

	public void setSpremark(String spremark) {
		this.spremark = spremark;
	}

	public Object getUtime() {
		return this.utime;
	}

	public void setUtime(String utime) {
		this.utime = utime;
	}

}