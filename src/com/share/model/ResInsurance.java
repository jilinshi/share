package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RES_INSURANCE database table.
 * 
 */
@Entity
@Table(name="RES_INSURANCE")
@NamedQuery(name="ResInsurance.findAll", query="SELECT r FROM ResInsurance r")
public class ResInsurance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RES_INSURANCE_INID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RES_INSURANCE_INID_GENERATOR")
	@Column(name="IN_ID")
	private long inId;

	private String birthday;

	private String danwei;

	@Column(name="FILE_ID")
	private String fileId;

	private String idno;

	private String inno;

	@Column(name="JF_BEGIN")
	private String jfBegin;

	@Column(name="LQ_BEGIN")
	private String lqBegin;

	@Column(name="LQ_MONEY")
	private BigDecimal lqMoney;

	private String pname;

	public ResInsurance() {
	}

	public long getInId() {
		return this.inId;
	}

	public void setInId(long inId) {
		this.inId = inId;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDanwei() {
		return this.danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getInno() {
		return this.inno;
	}

	public void setInno(String inno) {
		this.inno = inno;
	}

	public String getJfBegin() {
		return this.jfBegin;
	}

	public void setJfBegin(String jfBegin) {
		this.jfBegin = jfBegin;
	}

	public String getLqBegin() {
		return this.lqBegin;
	}

	public void setLqBegin(String lqBegin) {
		this.lqBegin = lqBegin;
	}

	public BigDecimal getLqMoney() {
		return this.lqMoney;
	}

	public void setLqMoney(BigDecimal lqMoney) {
		this.lqMoney = lqMoney;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

}