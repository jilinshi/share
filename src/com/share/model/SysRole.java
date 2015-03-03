package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SYS_ROLE database table.
 * 
 */
@Entity
@Table(name="SYS_ROLE")
@NamedQuery(name="SysRole.findAll", query="SELECT s FROM SysRole s")
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_ROLE_ROLEID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_ROLE_ROLEID_GENERATOR")
	@Column(name="ROLE_ID", unique=true, nullable=false, precision=12)
	private long roleId;

	private Timestamp ctime;

	@Column(length=128)
	private String flag;

	@Column(length=128)
	private String rolename;

	private Timestamp utime;

	//bi-directional many-to-many association to SysPrivilege
	@ManyToMany
	@JoinTable(
		name="SYS_RPRELATION"
		, joinColumns={
			@JoinColumn(name="ROLE_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="PRIVILEGE_ID")
			}
		)
	private List<SysPrivilege> sysPrivileges;

	//bi-directional many-to-many association to SysUsergroup
	@ManyToMany(mappedBy="sysRoles")
	private List<SysUsergroup> sysUsergroups;

	//bi-directional many-to-many association to SysUser
	@ManyToMany(mappedBy="sysRoles")
	private List<SysUser> sysUsers;

	public SysRole() {
	}

	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
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

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
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

	public List<SysUsergroup> getSysUsergroups() {
		return this.sysUsergroups;
	}

	public void setSysUsergroups(List<SysUsergroup> sysUsergroups) {
		this.sysUsergroups = sysUsergroups;
	}

	public List<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

}