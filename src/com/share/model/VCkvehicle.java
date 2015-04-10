package com.share.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the V_CKVEHICLE database table.
 * 
 */
@Entity
@Table(name="V_CKVEHICLE")
@NamedQuery(name="VCkvehicle.findAll", query="SELECT v FROM VCkvehicle v")
public class VCkvehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=128)
	private String brand;

	@Column(length=128)
	private String buytime;

	@Column(length=256)
	private String ckcol1;

	@Column(length=256)
	private String ckcol2;

	@Column(length=256)
	private String ckcol3;

	@Column(length=256)
	private String ckcol4;

	@Column(length=256)
	private String ckcol5;

	@Column(length=256)
	private String ckcol6;

	@Column(length=256)
	private String ckcol7;

	@Column(length=256)
	private String ckcol8;

	@Column(length=256)
	private String ckcol9;

	@Column(length=18)
	private String ckidno;

	@Column(length=128)
	private String ckpname;

	@Column(length=256)
	private String ckremark;

	@Column(length=256)
	private String cksubject;

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

	@Column(length=4)
	private String ds;

	@Column(length=128)
	private String fno;

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

	@Column(name="MOTO_NO", length=128)
	private String motoNo;

	@Column(length=384)
	private String oo;

	@Column(length=128)
	private String oo1;

	@Column(length=128)
	private String oo2;

	@Column(length=128)
	private String oo3;
	
	@Column(name="PI_ID", nullable=false, length=256)
	private String piId;

	@Column(length=256)
	private String pname;

	@Column(length=1024)
	private String remark;

	private Timestamp updatetime;
	@Id
	@Column(name="V_ID", nullable=false, precision=12)
	private BigDecimal vId;

	@Column(name="VEHICAL_NO", length=128)
	private String vehicalNo;

	@Column(length=128)
	private String vtype;

	public VCkvehicle() {
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBuytime() {
		return this.buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}

	public String getCkcol1() {
		return this.ckcol1;
	}

	public void setCkcol1(String ckcol1) {
		this.ckcol1 = ckcol1;
	}

	public String getCkcol2() {
		return this.ckcol2;
	}

	public void setCkcol2(String ckcol2) {
		this.ckcol2 = ckcol2;
	}

	public String getCkcol3() {
		return this.ckcol3;
	}

	public void setCkcol3(String ckcol3) {
		this.ckcol3 = ckcol3;
	}

	public String getCkcol4() {
		return this.ckcol4;
	}

	public void setCkcol4(String ckcol4) {
		this.ckcol4 = ckcol4;
	}

	public String getCkcol5() {
		return this.ckcol5;
	}

	public void setCkcol5(String ckcol5) {
		this.ckcol5 = ckcol5;
	}

	public String getCkcol6() {
		return this.ckcol6;
	}

	public void setCkcol6(String ckcol6) {
		this.ckcol6 = ckcol6;
	}

	public String getCkcol7() {
		return this.ckcol7;
	}

	public void setCkcol7(String ckcol7) {
		this.ckcol7 = ckcol7;
	}

	public String getCkcol8() {
		return this.ckcol8;
	}

	public void setCkcol8(String ckcol8) {
		this.ckcol8 = ckcol8;
	}

	public String getCkcol9() {
		return this.ckcol9;
	}

	public void setCkcol9(String ckcol9) {
		this.ckcol9 = ckcol9;
	}

	public String getCkidno() {
		return this.ckidno;
	}

	public void setCkidno(String ckidno) {
		this.ckidno = ckidno;
	}

	public String getCkpname() {
		return this.ckpname;
	}

	public void setCkpname(String ckpname) {
		this.ckpname = ckpname;
	}

	public String getCkremark() {
		return this.ckremark;
	}

	public void setCkremark(String ckremark) {
		this.ckremark = ckremark;
	}

	public String getCksubject() {
		return this.cksubject;
	}

	public void setCksubject(String cksubject) {
		this.cksubject = cksubject;
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

	public String getMotoNo() {
		return this.motoNo;
	}

	public void setMotoNo(String motoNo) {
		this.motoNo = motoNo;
	}

	public String getOo() {
		return this.oo;
	}

	public void setOo(String oo) {
		this.oo = oo;
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

	public BigDecimal getVId() {
		return this.vId;
	}

	public void setVId(BigDecimal vId) {
		this.vId = vId;
	}

	public String getVehicalNo() {
		return this.vehicalNo;
	}

	public void setVehicalNo(String vehicalNo) {
		this.vehicalNo = vehicalNo;
	}

	public String getVtype() {
		return this.vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

}