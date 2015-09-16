package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the V_JD_ONNO database table.
 * 
 */
@Entity
@Table(name="V_JD_ONNO")
@NamedQuery(name="VJdOnno.findAll", query="SELECT v FROM VJdOnno v")
public class VJdOnno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ON_ALLNAME", length=200)
	private String onAllname;

	@Column(name="ON_FATHERID", length=50)
	private String onFatherid;

	@Column(name="ON_ID", length=50)
	private String onId;

	@Column(name="ON_LEVEL", precision=22)
	private BigDecimal onLevel;

	@Column(name="ON_NAME", length=50)
	private String onName;
	@Id
	@Column(name="ON_NO", length=50)
	private String onNo;

	public VJdOnno() {
	}

	public String getOnAllname() {
		return this.onAllname;
	}

	public void setOnAllname(String onAllname) {
		this.onAllname = onAllname;
	}

	public String getOnFatherid() {
		return this.onFatherid;
	}

	public void setOnFatherid(String onFatherid) {
		this.onFatherid = onFatherid;
	}

	public String getOnId() {
		return this.onId;
	}

	public void setOnId(String onId) {
		this.onId = onId;
	}

	public BigDecimal getOnLevel() {
		return this.onLevel;
	}

	public void setOnLevel(BigDecimal onLevel) {
		this.onLevel = onLevel;
	}

	public String getOnName() {
		return this.onName;
	}

	public void setOnName(String onName) {
		this.onName = onName;
	}

	public String getOnNo() {
		return this.onNo;
	}

	public void setOnNo(String onNo) {
		this.onNo = onNo;
	}

}