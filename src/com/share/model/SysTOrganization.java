package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the SYS_T_ORGANIZATION database table.
 * 
 */
@Entity
@Table(name = "SYS_T_ORGANIZATION")
public class SysTOrganization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 128)
	private String asorgname;

	@Column(name = "\"DEPTH\"", length = 128)
	private String depth;

	@Column(length = 128)
	private String fullname;
	@Id
	@Column(name = "ORGANIZATION_ID", length = 128)
	private String organizationId;

	@Column(length = 128)
	private String parentorgid;

	@Column(name = "SEQ_ID", length = 128)
	private String seqId;

	@Column(length = 128)
	private String serialnumber;

	@Column(length = 128)
	private String status;

	public SysTOrganization() {
	}

	public String getAsorgname() {
		return this.asorgname;
	}

	public void setAsorgname(String asorgname) {
		this.asorgname = asorgname;
	}

	public String getDepth() {
		return this.depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getParentorgid() {
		return this.parentorgid;
	}

	public void setParentorgid(String parentorgid) {
		this.parentorgid = parentorgid;
	}

	public String getSeqId() {
		return this.seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	public String getSerialnumber() {
		return this.serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}