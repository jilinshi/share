package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the V_IMPFILES database table.
 * 
 */
@Entity
@Table(name = "V_IMPFILES")
@NamedQuery(name = "VImpfile.findAll", query = "SELECT v FROM VImpfile v")
public class VImpfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "FILE_ID", precision = 22)
	private BigDecimal fileId;

	@Column(length = 256)
	private String filename;

	@Column(length = 256)
	private String ftype;
	@Id
	@Column(name = "INFO_ID1", nullable = false, precision = 22)
	private BigDecimal infoId1;

	@Column(length = 256)
	private String operstat;

	@Column(length = 6)
	private String operstatval;

	private Timestamp opertime;

	@Column(name = "ORG_ID", precision = 12)
	private BigDecimal orgId;

	@Column(length = 256)
	private String realname;

	@Column(length = 256)
	private String realpath;

	@Column(length = 256)
	private String uname;

	private Timestamp uptime;

	@Column(name = "USER_ID", precision = 22)
	private BigDecimal userId;

	public VImpfile() {
	}

	public BigDecimal getFileId() {
		return this.fileId;
	}

	public void setFileId(BigDecimal fileId) {
		this.fileId = fileId;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFtype() {
		return this.ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public BigDecimal getInfoId1() {
		return this.infoId1;
	}

	public void setInfoId1(BigDecimal infoId1) {
		this.infoId1 = infoId1;
	}

	public String getOperstat() {
		return this.operstat;
	}

	public void setOperstat(String operstat) {
		this.operstat = operstat;
	}

	public String getOperstatval() {
		return this.operstatval;
	}

	public void setOperstatval(String operstatval) {
		this.operstatval = operstatval;
	}

	public Timestamp getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Timestamp opertime) {
		this.opertime = opertime;
	}

	public BigDecimal getOrgId() {
		return this.orgId;
	}

	public void setOrgId(BigDecimal orgId) {
		this.orgId = orgId;
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

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Timestamp getUptime() {
		return this.uptime;
	}

	public void setUptime(Timestamp uptime) {
		this.uptime = uptime;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

}