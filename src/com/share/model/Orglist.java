package com.share.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the ORGLIST database table.
 * 
 */
@Entity
@Table(name = "ORGLIST")
public class Orglist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 128)
	private String asorgname;

	@Column(length = 128)
	private String fullname;
	@Id
	@Column(name = "ORGANIZATION_ID", length = 128)
	private String organizationId;

	@Column(length = 128)
	private String orglevel;

	@Column(length = 128)
	private String parentorgid;

	@Column(name = "SEQ_ID", length = 128)
	private String seqId;

	@Column(length = 128)
	private String serialnumber;

	@Column(length = 128)
	private String status;

	public Orglist() {
	}

	public String getAsorgname() {
		return this.asorgname;
	}

	public void setAsorgname(String asorgname) {
		this.asorgname = asorgname;
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

	public String getOrglevel() {
		return this.orglevel;
	}

	public void setOrglevel(String orglevel) {
		this.orglevel = orglevel;
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