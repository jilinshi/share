package com.share.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RES_BURIAL database table.
 * 
 */
@Entity
@Table(name="RES_BURIAL")
@NamedQuery(name="ResBurial.findAll", query="SELECT r FROM ResBurial r")
public class ResBurial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RES_BURIAL_ID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RES_BURIAL_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=12)
	private long id;

	@Column(length=256)
	private String address;

	@Column(length=256)
	private String deathreason;

	@Column(name="FILE_ID", length=256)
	private String fileId;

	@Column(length=256)
	private String hhtime;

	@Column(length=256)
	private String idno;

	@Column(name="\"PAGE\"", length=256)
	private String page;

	@Column(length=256)
	private String pname;

	@Column(length=256)
	private String psex;

	@Column(length=256)
	private String telephone;

	public ResBurial() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeathreason() {
		return this.deathreason;
	}

	public void setDeathreason(String deathreason) {
		this.deathreason = deathreason;
	}

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getHhtime() {
		return this.hhtime;
	}

	public void setHhtime(String hhtime) {
		this.hhtime = hhtime;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPsex() {
		return this.psex;
	}

	public void setPsex(String psex) {
		this.psex = psex;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}