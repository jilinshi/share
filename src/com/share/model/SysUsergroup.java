package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SYS_USERGROUP database table.
 * 
 */
@Entity
@Table(name="SYS_USERGROUP")
@NamedQuery(name="SysUsergroup.findAll", query="SELECT s FROM SysUsergroup s")
public class SysUsergroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_USERGROUP_UGID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_USERGROUP_UGID_GENERATOR")
	@Column(name="UG_ID", unique=true, nullable=false, precision=12)
	private long ugId;

	private Timestamp ctime;

	@Column(length=128)
	private String flag;

	@Column(name="UG_NAME", length=128)
	private String ugName;

	@Column(name="UG_PID", precision=12)
	private BigDecimal ugPid;

	private Timestamp utime;

	//bi-directional many-to-many association to SysRole
	@ManyToMany
	@JoinTable(
		name="SYS_GRRELATION"
		, joinColumns={
			@JoinColumn(name="UG_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ROLE_ID")
			}
		)
	private List<SysRole> sysRoles;

	//bi-directional many-to-many association to SysUser
	@ManyToMany(mappedBy="sysUsergroups")
	private List<SysUser> sysUsers;

	public SysUsergroup() {
	}

	public long getUgId() {
		return this.ugId;
	}

	public void setUgId(long ugId) {
		this.ugId = ugId;
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

	public Timestamp getUtime() {
		return this.utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public List<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public List<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

}