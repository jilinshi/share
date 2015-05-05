package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the V_ATTORNEY database table.
 * 
 */
@Entity
@Table(name = "V_ATTORNEY")
@NamedQuery(name = "VAttorney.findAll", query = "SELECT v FROM VAttorney v")
public class VAttorney implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "A_ID", nullable = false, precision = 12)
	private BigDecimal aId;

	@Column(length = 256)
	private String attorney;

	@Column(name = "ATTORNEY_ID", length = 256)
	private String attorneyId;

	@Column(length = 256)
	private String ckcontent;

	@Column(length = 256)
	private String ckmonth;

	private Timestamp cktime;

	@Column(length = 256)
	private String col1;

	@Column(length = 256)
	private String col10;

	@Column(length = 256)
	private String col11;

	@Column(length = 256)
	private String col2;

	@Column(length = 256)
	private String col3;

	@Column(length = 256)
	private String col4;

	@Column(length = 256)
	private String col5;

	@Column(length = 256)
	private String col6;

	@Column(length = 256)
	private String col7;

	@Column(length = 256)
	private String col8;

	@Column(length = 256)
	private String col9;

	@Column(length = 256)
	private String flag;

	@Column(length = 256)
	private String masteridno;

	@Column(length = 256)
	private String mastername;

	private Timestamp opertime;

	private Timestamp uploadtime;

	@Column(length = 256)
	private String upoper;

	@Column(length = 256)
	private String wtdanwei;

	private Timestamp wttime;

	public VAttorney() {
	}

	public BigDecimal getAId() {
		return this.aId;
	}

	public void setAId(BigDecimal aId) {
		this.aId = aId;
	}

	public String getAttorney() {
		return this.attorney;
	}

	public void setAttorney(String attorney) {
		this.attorney = attorney;
	}

	public String getAttorneyId() {
		return this.attorneyId;
	}

	public void setAttorneyId(String attorneyId) {
		this.attorneyId = attorneyId;
	}

	public String getCkcontent() {
		return this.ckcontent;
	}

	public void setCkcontent(String ckcontent) {
		this.ckcontent = ckcontent;
	}

	public String getCkmonth() {
		return this.ckmonth;
	}

	public void setCkmonth(String ckmonth) {
		this.ckmonth = ckmonth;
	}

	public Timestamp getCktime() {
		return this.cktime;
	}

	public void setCktime(Timestamp cktime) {
		this.cktime = cktime;
	}

	public String getCol1() {
		return this.col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol10() {
		return this.col10;
	}

	public void setCol10(String col10) {
		this.col10 = col10;
	}

	public String getCol11() {
		return this.col11;
	}

	public void setCol11(String col11) {
		this.col11 = col11;
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

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMasteridno() {
		return this.masteridno;
	}

	public void setMasteridno(String masteridno) {
		this.masteridno = masteridno;
	}

	public String getMastername() {
		return this.mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

	public Timestamp getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Timestamp opertime) {
		this.opertime = opertime;
	}

	public Timestamp getUploadtime() {
		return this.uploadtime;
	}

	public void setUploadtime(Timestamp uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getUpoper() {
		return this.upoper;
	}

	public void setUpoper(String upoper) {
		this.upoper = upoper;
	}

	public String getWtdanwei() {
		return this.wtdanwei;
	}

	public void setWtdanwei(String wtdanwei) {
		this.wtdanwei = wtdanwei;
	}

	public Timestamp getWttime() {
		return this.wttime;
	}

	public void setWttime(Timestamp wttime) {
		this.wttime = wttime;
	}

}