package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SYS_MENUS database table.
 * 
 */
@Entity
@Table(name="SYS_MENUS")
@NamedQuery(name="SysMenus.findAll", query="SELECT s FROM SysMenus s")
public class SysMenus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_MENUS_MENUID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_MENUS_MENUID_GENERATOR")
	@Column(name="MENU_ID", unique=true, nullable=false, precision=12)
	private long menuId;

	private Timestamp ctime;

	@Column(length=128)
	private String flag;

	@Column(length=128)
	private String menucode;

	@Column(length=128)
	private String menuname;

	@Column(length=128)
	private String menuurl;

	@Column(name="PM_ID", precision=12)
	private BigDecimal pmId;

	@Column(length=128)
	private String remark;

	private Timestamp utime;

	//bi-directional many-to-many association to SysPrivilege
	@ManyToMany(mappedBy="sysMenuses")
	private List<SysPrivilege> sysPrivileges;

	public SysMenus() {
	}

	public long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getUtime() {
		return this.utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public List<SysPrivilege> getSysPrivileges() {
		return this.sysPrivileges;
	}

	public void setSysPrivileges(List<SysPrivilege> sysPrivileges) {
		this.sysPrivileges = sysPrivileges;
	}

}