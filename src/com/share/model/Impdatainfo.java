package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the IMPDATAINFO database table.
 * 
 */
@Entity
@Table(name="IMPDATAINFO")
@NamedQuery(name="Impdatainfo.findAll", query="SELECT i FROM Impdatainfo i")
public class Impdatainfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="IMPDATAINFO_INFOID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IMPDATAINFO_INFOID_GENERATOR")
	@Column(name="INFO_ID", unique=true, nullable=false, precision=22)
	private long infoId;

	@Column(name="FILE_ID", precision=22)
	private BigDecimal fileId;

	@Column(length=256)
	private String filename;

	@Column(length=256)
	private String operstat;

	private Timestamp opertime;

	@Column(length=256)
	private String realpath;

	@Column(name="USER_ID", precision=22)
	private BigDecimal userId;

	public Impdatainfo() {
	}

	public long getInfoId() {
		return this.infoId;
	}

	public void setInfoId(long infoId) {
		this.infoId = infoId;
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

	public String getOperstat() {
		return this.operstat;
	}

	public void setOperstat(String operstat) {
		this.operstat = operstat;
	}

	public Timestamp getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Timestamp opertime) {
		this.opertime = opertime;
	}

	public String getRealpath() {
		return this.realpath;
	}

	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

}