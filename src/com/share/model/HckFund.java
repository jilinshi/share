package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the HCK_FUND database table.
 * 
 */
@Entity
@Table(name="HCK_FUND")
@NamedQuery(name="HckFund.findAll", query="SELECT h FROM HckFund h")
public class HckFund implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HckFundPK id;

	@Column(length=256)
	private String area;

	@Column(precision=10, scale=2)
	private BigDecimal banlance;

	@Column(length=256)
	private String begintime;

	@Column(length=256)
	private String bidno;

	@Column(length=256)
	private String bname;

	@Column(precision=10, scale=2)
	private BigDecimal cardinal;

	@Column(length=256)
	private String col1;

	@Column(length=256)
	private String col2;

	@Column(length=256)
	private String col3;

	@Column(length=256)
	private String col4;

	@Column(length=256)
	private String col5;

	@Column(length=256)
	private String col6;

	@Column(length=256)
	private String col7;

	@Column(length=256)
	private String col8;

	@Column(length=256)
	private String col9;

	@Column(length=256)
	private String ctime;

	@Column(length=256)
	private String gjjaccount;

	@Column(length=18)
	private String idno;

	@Column(name="INFO_ID", precision=22)
	private BigDecimal infoId;

	@Column(length=12)
	private String lasttime;

	@Column(length=256)
	private String pname;

	@Column(length=256)
	private String remark;

	@Column(name="\"STATE\"", length=256)
	private String state;

	@Column(length=256)
	private String subject;

	@Column(length=256)
	private String unitname;

	public HckFund() {
	}

	public HckFundPK getId() {
		return this.id;
	}

	public void setId(HckFundPK id) {
		this.id = id;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public BigDecimal getBanlance() {
		return this.banlance;
	}

	public void setBanlance(BigDecimal banlance) {
		this.banlance = banlance;
	}

	public String getBegintime() {
		return this.begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getBidno() {
		return this.bidno;
	}

	public void setBidno(String bidno) {
		this.bidno = bidno;
	}

	public String getBname() {
		return this.bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public BigDecimal getCardinal() {
		return this.cardinal;
	}

	public void setCardinal(BigDecimal cardinal) {
		this.cardinal = cardinal;
	}

	public String getCol1() {
		return this.col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return this.col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return this.col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
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

	public String getCol7() {
		return this.col7;
	}

	public void setCol7(String col7) {
		this.col7 = col7;
	}

	public String getCol8() {
		return this.col8;
	}

	public void setCol8(String col8) {
		this.col8 = col8;
	}

	public String getCol9() {
		return this.col9;
	}

	public void setCol9(String col9) {
		this.col9 = col9;
	}

	public String getCtime() {
		return this.ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getGjjaccount() {
		return this.gjjaccount;
	}

	public void setGjjaccount(String gjjaccount) {
		this.gjjaccount = gjjaccount;
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

	public String getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUnitname() {
		return this.unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

}