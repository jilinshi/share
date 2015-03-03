package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SYS_PRIVILEGE database table.
 * 
 */
@Entity
@Table(name="SYS_PRIVILEGE")
@NamedQuery(name="SysPrivilege.findAll", query="SELECT s FROM SysPrivilege s")
public class SysPrivilege implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_PRIVILEGE_PRIVILEGEID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_PRIVILEGE_PRIVILEGEID_GENERATOR")
	@Column(name="PRIVILEGE_ID", unique=true, nullable=false, precision=12)
	private long privilegeId;

	private Timestamp ctime;

	@Column(length=128)
	private String flag;

	@Column(length=128)
	private String privicode;

	@Column(length=128)
	private String priviname;

	@Column(length=128)
	private String remark;

	//bi-directional many-to-many association to SysElement
	@ManyToMany
	@JoinTable(
		name="SYS_EPRELATION"
		, joinColumns={
			@JoinColumn(name="PRIVILEGE_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ELEMENT_ID")
			}
		)
	private List<SysElement> sysElements;

	//bi-directional many-to-many association to SysFile
	@ManyToMany
	@JoinTable(
		name="SYS_FPRELATION"
		, joinColumns={
			@JoinColumn(name="PRIVILEGE_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FILE_ID")
			}
		)
	private List<SysFile> sysFiles;

	//bi-directional many-to-many association to SysFunction
	@ManyToMany
	@JoinTable(
		name="SYS_PFRELATION"
		, joinColumns={
			@JoinColumn(name="PRIVILEGE_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="FUNCTION_ID")
			}
		)
	private List<SysFunction> sysFunctions;

	//bi-directional many-to-many association to SysMenus
	@ManyToMany
	@JoinTable(
		name="SYS_MPRELATION"
		, joinColumns={
			@JoinColumn(name="PRIVILEGE_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="MENU_ID")
			}
		)
	private List<SysMenus> sysMenuses;

	//bi-directional many-to-many association to SysRole
	@ManyToMany(mappedBy="sysPrivileges")
	private List<SysRole> sysRoles;

	public SysPrivilege() {
	}

	public long getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(long privilegeId) {
		this.privilegeId = privilegeId;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<SysElement> getSysElements() {
		return this.sysElements;
	}

	public void setSysElements(List<SysElement> sysElements) {
		this.sysElements = sysElements;
	}

	public List<SysFile> getSysFiles() {
		return this.sysFiles;
	}

	public void setSysFiles(List<SysFile> sysFiles) {
		this.sysFiles = sysFiles;
	}

	public List<SysFunction> getSysFunctions() {
		return this.sysFunctions;
	}

	public void setSysFunctions(List<SysFunction> sysFunctions) {
		this.sysFunctions = sysFunctions;
	}

	public List<SysMenus> getSysMenuses() {
		return this.sysMenuses;
	}

	public void setSysMenuses(List<SysMenus> sysMenuses) {
		this.sysMenuses = sysMenuses;
	}

	public List<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

}