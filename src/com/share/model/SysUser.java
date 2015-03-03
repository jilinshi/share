package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SYS_USERS database table.
 * 
 */
@Entity
@Table(name="SYS_USERS")
@NamedQuery(name="SysUser.findAll", query="SELECT s FROM SysUser s")
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_USERS_USERID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_USERS_USERID_GENERATOR")
	@Column(name="USER_ID", unique=true, nullable=false, precision=12)
	private long userId;

	private Timestamp ctime;

	@Column(length=256)
	private String flag;

	@Column(length=256)
	private String idno;

	@Column(length=256)
	private String mobilephone;

	@Column(length=256)
	private String uaccount;

	@Column(length=256)
	private String uname;

	@Column(length=256)
	private String upwds;

	private Timestamp utime;

	//bi-directional many-to-one association to SysOrganization
	@ManyToOne
	@JoinColumn(name="ORG_ID")
	private SysOrganization sysOrganization;

	//bi-directional many-to-many association to SysRole
	@ManyToMany
	@JoinTable(
		name="SYS_URRELATION"
		, joinColumns={
			@JoinColumn(name="USER_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ROLE_ID")
			}
		)
	private List<SysRole> sysRoles;

	//bi-directional many-to-many association to SysUsergroup
	@ManyToMany
	@JoinTable(
		name="SYS_UGRELATION"
		, joinColumns={
			@JoinColumn(name="USER_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="UG_ID")
			}
		)
	private List<SysUsergroup> sysUsergroups;

	public SysUser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public Timestamp getUtime() {
		return this.utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public SysOrganization getSysOrganization() {
		return this.sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

	public List<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public List<SysUsergroup> getSysUsergroups() {
		return this.sysUsergroups;
	}

	public void setSysUsergroups(List<SysUsergroup> sysUsergroups) {
		this.sysUsergroups = sysUsergroups;
	}

}