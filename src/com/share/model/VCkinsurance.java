package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the V_CKINSURANCE database table.
 * 
 */
@Entity
@Table(name="V_CKINSURANCE")
@NamedQuery(name="VCkinsurance.findAll", query="SELECT v FROM VCkinsurance v")
public class VCkinsurance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=256)
	private String col4;

	@Column(length=256)
	private String col5;

	@Column(length=256)
	private String col6;

	@Column(length=128)
	private String comp;

	@Column(length=4)
	private String ds;

	@Column(length=128)
	private String fno;

	@Column(length=15)
	private String id15;

	@Column(length=18)
	private String id18;

	@Id
	@Column(length=18)
	private String idno;

	@Column(name="INFO_ID", precision=22)
	private BigDecimal infoId;

	@Column(length=20)
	private String masterid;

	@Column(length=256)
	private String mastername;

	@Column(length=384)
	private String oo;

	@Column(length=256)
	private String pname;

	@Column(length=128)
	private String sbidno;

	@Column(length=128)
	private String sbname;

	@Column(length=128)
	private String txmoney;

	@Column(length=128)
	private String txtime;

	private Timestamp updatetime;

	public VCkinsurance() {
	}

	public String getCol4() {
		return this.col4;
	}

	public void setCol4(String col4) {
		this.col4 = col4;
	}

	public String getCol5() {
		return this.col5;
	}

	public void setCol5(String col5) {
		this.col5 = col5;
	}

	public String getCol6() {
		return this.col6;
	}

	public void setCol6(String col6) {
		this.col6 = col6;
	}

	public String getComp() {
		return this.comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public String getDs() {
		return this.ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
	}

	public String getFno() {
		return this.fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getId15() {
		return this.id15;
	}

	public void setId15(String id15) {
		this.id15 = id15;
	}

	public String getId18() {
		return this.id18;
	}

	public void setId18(String id18) {
		this.id18 = id18;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public BigDecimal getInfoId() {
		return this.infoId;
	}

	public void setInfoId(BigDecimal infoId) {
		this.infoId = infoId;
	}

	public String getMasterid() {
		return this.masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public String getMastername() {
		return this.mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

	public String getOo() {
		return this.oo;
	}

	public void setOo(String oo) {
		this.oo = oo;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getSbidno() {
		return this.sbidno;
	}

	public void setSbidno(String sbidno) {
		this.sbidno = sbidno;
	}

	public String getSbname() {
		return this.sbname;
	}

	public void setSbname(String sbname) {
		this.sbname = sbname;
	}

	public String getTxmoney() {
		return this.txmoney;
	}

	public void setTxmoney(String txmoney) {
		this.txmoney = txmoney;
	}

	public String getTxtime() {
		return this.txtime;
	}

	public void setTxtime(String txtime) {
		this.txtime = txtime;
	}

	public Timestamp getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

}