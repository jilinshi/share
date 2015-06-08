package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the PP_BURIAL database table.
 * 
 */
@Entity
@Table(name="PP_BURIAL")
@NamedQuery(name="PpBurial.findAll", query="SELECT p FROM PpBurial p")
public class PpBurial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="A_ID")
	private BigDecimal aId;

	private String address;

	private String area;

	@Column(name="ATTORNEY_ID")
	private String attorneyId;

	private String ckmonth;

	private String col1;

	private String col2;

	private String col3;

	private String col4;

	private String col5;

	private String col6;

	private String col7;

	private String col8;

	private String col9;

	private String deathreason;

	private String ds;

	@Column(name="FILE_ID")
	private String fileId;

	private String hhtime;

	private String id15;

	private String id18;

	private String idno;

	private String idno1;

	@Column(name="IN_ID")
	private BigDecimal inId;

	private String masterid;

	private String mastername;

	private String o1;

	private String o2;

	private String o3;

	@Column(name="\"PAGE\"")
	private String page;
	@Id
	@Column(name="PI_ID")
	private BigDecimal piId;

	private String pname;

	private String pname1;

	private String psex;

	private String remark;

	private String telephone;

	private Timestamp updatetime;

	public PpBurial() {
	}

	public BigDecimal getAId() {
		return this.aId;
	}

	public void setAId(BigDecimal aId) {
		this.aId = aId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAttorneyId() {
		return this.attorneyId;
	}

	public void setAttorneyId(String attorneyId) {
		this.attorneyId = attorneyId;
	}

	public String getCkmonth() {
		return this.ckmonth;
	}

	public void setCkmonth(String ckmonth) {
		this.ckmonth = ckmonth;
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

	public String getDeathreason() {
		return this.deathreason;
	}

	public void setDeathreason(String deathreason) {
		this.deathreason = deathreason;
	}

	public String getDs() {
		return this.ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
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

	public String getIdno1() {
		return this.idno1;
	}

	public void setIdno1(String idno1) {
		this.idno1 = idno1;
	}

	public BigDecimal getInId() {
		return this.inId;
	}

	public void setInId(BigDecimal inId) {
		this.inId = inId;
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

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public BigDecimal getPiId() {
		return this.piId;
	}

	public void setPiId(BigDecimal piId) {
		this.piId = piId;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPname1() {
		return this.pname1;
	}

	public void setPname1(String pname1) {
		this.pname1 = pname1;
	}

	public String getPsex() {
		return this.psex;
	}

	public void setPsex(String psex) {
		this.psex = psex;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Timestamp getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

}