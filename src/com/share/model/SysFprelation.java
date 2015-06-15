package com.share.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SYS_FPRELATION database table.
 * 
 */
@Entity
@Table(name="SYS_FPRELATION")
@NamedQuery(name="SysFprelation.findAll", query="SELECT s FROM SysFprelation s")
public class SysFprelation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="FILE_ID", precision=12)
	private java.math.BigDecimal fileId;

	@Column(name="PRIVILEGE_ID", precision=12)
	private java.math.BigDecimal privilegeId;

	public SysFprelation() {
	}

	public java.math.BigDecimal getFileId() {
		return this.fileId;
	}

	public void setFileId(java.math.BigDecimal fileId) {
		this.fileId = fileId;
	}

	public java.math.BigDecimal getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(java.math.BigDecimal privilegeId) {
		this.privilegeId = privilegeId;
	}

}