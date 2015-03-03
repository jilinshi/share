package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
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

	public List<SysPrivilege> getSysPrivileges() {
		return this.sysPrivileges;
	}

	public void setSysPrivileges(List<SysPrivilege> sysPrivileges) {
		this.sysPrivileges = sysPrivileges;
	}

}