package com.share.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the PP_HOUSEPROPERTY database table.
 * 
 */
@Entity
@Table(name="PP_HOUSEPROPERTY")
@NamedQuery(name="PpHouseproperty.findAll", query="SELECT p FROM PpHouseproperty p")
public class PpHouseproperty implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Column(length=4)
	private String ds;

	@Column(length=15)
	private String id15;

	@Column(length=18)
	private String id18;

	@Column(length=18)
	private String idno;

	@Column(length=20)
	private String masterid;

	@Column(length=256)
	private String mastername;

	@Column(length=128)
	private String o1;

	@Column(length=128)
	private String o2;

	@Column(length=128)
	private String o3;
	@Id
	@Column(name="PI_ID", nullable=false, length=256)
	private String piId;

	@Column(length=256)
	private String pname;

	@Column(length=1024)
	private String remark;

	private Timestamp updatetime;
	
	@Column(length = 256)
	private String area;

	public PpHouseproperty() {
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

	public String getDs() {
		return this.ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
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

	public String getO1() {
		return this.o1;
	}

	public void setO1(String o1) {
		this.o1 = o1;
	}

	public String getO2() {
		return this.o2;
	}

	public void setO2(String o2) {
		this.o2 = o2;
	}

	public String getO3() {
		return this.o3;
	}

	public void setO3(String o3) {
		this.o3 = o3;
	}

	public String getPiId() {
		return this.piId;
	}

	public void setPiId(String piId) {
		this.piId = piId;
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

	public Timestamp getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}