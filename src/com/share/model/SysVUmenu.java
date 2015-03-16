package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the SYS_V_UMENU database table.
 * 
 */
@Entity
@Table(name = "SYS_V_UMENU")
@NamedQuery(name = "SysVUmenu.findAll", query = "SELECT s FROM SysVUmenu s")
public class SysVUmenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 256)
	private String idno;
	@Id
	@Column(precision = 22)
	private BigDecimal idx;

	@Column(name = "MENU_ID", nullable = false, precision = 12)
	private BigDecimal menuId;

	@Column(length = 128)
	private String menucode;

	@Column(length = 128)
	private String menuname;

	@Column(length = 128)
	private String menuurl;

	@Column(length = 256)
	private String mobilephone;

	@Column(name = "ORG_ID", precision = 12)
	private BigDecimal orgId;

	@Column(name = "PM_ID", precision = 12)
	private BigDecimal pmId;

	@Column(length = 128)
	private String privicode;

	@Column(length = 128)
	private String priviname;

	@Column(name = "ROLE_ID", nullable = false, precision = 12)
	private BigDecimal roleId;

	@Column(length = 128)
	private String rolename;

	@Column(length = 256)
	private String uaccount;

	@Column(length = 256)
	private String uname;

	@Column(length = 256)
	private String upwds;

	@Column(name = "USER_ID", nullable = false, precision = 12)
	private BigDecimal userId;

	public SysVUmenu() {
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public BigDecimal getIdx() {
		return this.idx;
	}

	public void setIdx(BigDecimal idx) {
		this.idx = idx;
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

	public String getPriviname() {
		return this.priviname;
	}

	public void setPriviname(String priviname) {
		this.priviname = priviname;
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