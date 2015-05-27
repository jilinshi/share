package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SYS_ORGANIZATION database table.
 * 
 */
@Entity
@Table(name="SYS_ORGANIZATION")
@NamedQuery(name="SysOrganization.findAll", query="SELECT s FROM SysOrganization s")
public class SysOrganization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORG_ID", unique=true, nullable=false, precision=12)
	private long orgId;

	private Timestamp ctime;

	@Column(length=256)
	private String flag;

	@Column(name="ORG_CODE", length=256)
	private String orgCode;

	@Column(name="ORG_NAME", length=256)
	private String orgName;

	@Column(name="PARENT_ID", precision=12)
	private String parentId;

	private Timestamp utime;

	//bi-directional many-to-one association to SysDistrict
	@ManyToOne
	@JoinColumn(name="DISTRICTS_ID")
	private SysDistrict sysDistrict;

	//bi-directional many-to-one association to SysUser
	@OneToMany(mappedBy="sysOrganization")
	private List<SysUser> sysUsers;

	public SysOrganization() {
	}

	public long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
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

	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Timestamp getUtime() {
		return this.utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public SysDistrict getSysDistrict() {
		return this.sysDistrict;
	}

	public void setSysDistrict(SysDistrict sysDistrict) {
		this.sysDistrict = sysDistrict;
	}

	public List<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	public SysUser addSysUser(SysUser sysUser) {
		getSysUsers().add(sysUser);
		sysUser.setSysOrganization(this);

		return sysUser;
	}

	public SysUser removeSysUser(SysUser sysUser) {
		getSysUsers().remove(sysUser);
		sysUser.setSysOrganization(null);

		return sysUser;
	}

}