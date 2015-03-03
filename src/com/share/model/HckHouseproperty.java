package com.share.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HCK_HOUSEPROPERTY database table.
 * 
 */
@Entity
@Table(name="HCK_HOUSEPROPERTY")
@NamedQuery(name="HckHouseproperty.findAll", query="SELECT h FROM HckHouseproperty h")
public class HckHouseproperty implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HckHousepropertyPK id;

	@Column(length=128)
	private String bidno;

	@Column(length=128)
	private String bltime;

	@Column(length=128)
	private String bname;

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

	@Column(length=128)
	private String fczh;

	@Column(length=128)
	private String ggtime;

	@Column(length=18)
	private String idno;

	@Column(length=128)
	private String mianji;

	@Column(length=128)
	private String oo1;

	@Column(length=128)
	private String oo2;

	@Column(length=128)
	private String oo3;

	@Column(length=128)
	private String pname;

	@Column(length=256)
	private String remark;

	@Column(length=128)
	private String slyw;

	@Column(length=256)
	private String subject;

	@Column(length=128)
	private String zuoluo;

	public HckHouseproperty() {
	}

	public HckHousepropertyPK getId() {
		return this.id;
	}

	public void setId(HckHousepropertyPK id) {
		this.id = id;
	}

	public String getBidno() {
		return this.bidno;
	}

	public void setBidno(String bidno) {
		this.bidno = bidno;
	}

	public String getBltime() {
		return this.bltime;
	}

	public void setBltime(String bltime) {
		this.bltime = bltime;
	}

	public String getBname() {
		return this.bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
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

	public String getFczh() {
		return this.fczh;
	}

	public void setFczh(String fczh) {
		this.fczh = fczh;
	}

	public String getGgtime() {
		return this.ggtime;
	}

	public void setGgtime(String ggtime) {
		this.ggtime = ggtime;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getMianji() {
		return this.mianji;
	}

	public void setMianji(String mianji) {
		this.mianji = mianji;
	}

	public String getOo1() {
		return this.oo1;
	}

	public void setOo1(String oo1) {
		this.oo1 = oo1;
	}

	public String getOo2() {
		return this.oo2;
	}

	public void setOo2(String oo2) {
		this.oo2 = oo2;
	}

	public String getOo3() {
		return this.oo3;
	}

	public void setOo3(String oo3) {
		this.oo3 = oo3;
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

	public String getSlyw() {
		return this.slyw;
	}

	public void setSlyw(String slyw) {
		this.slyw = slyw;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getZuoluo() {
		return this.zuoluo;
	}

	public void setZuoluo(String zuoluo) {
		this.zuoluo = zuoluo;
	}

}