package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the SYS_FILES database table.
 * 
 */
@Entity
@Table(name="SYS_FILES")
@NamedQuery(name="SysFile.findAll", query="SELECT s FROM SysFile s")
public class SysFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_FILES_FILEID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_FILES_FILEID_GENERATOR")
	@Column(name="FILE_ID", unique=true, nullable=false, precision=12)
	private long fileId;

	@Column(length=256)
	private String filename;

	@Column(length=256)
	private String flag;

	@Column(length=256)
	private String ftype;

	@Column(length=256)
	private String realname;

	@Column(length=256)
	private String realpath;

	@Column(length=256)
	private String remark;

	private Timestamp uptime;

	//bi-directional many-to-many association to SysPrivilege
	@ManyToMany(mappedBy="sysFiles")
	private List<SysPrivilege> sysPrivileges;

	public SysFile() {
	}

	public long getFileId() {
		return this.fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFtype() {
		return this.ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRealpath() {
		return this.realpath;
	}

	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getUptime() {
		return this.uptime;
	}

	public void setUptime(Timestamp uptime) {
		this.uptime = uptime;
	}

	public List<SysPrivilege> getSysPrivileges() {
		return this.sysPrivileges;
	}

	public void setSysPrivileges(List<SysPrivilege> sysPrivileges) {
		this.sysPrivileges = sysPrivileges;
	}

}