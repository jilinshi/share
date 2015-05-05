package com.share.dto;

import java.sql.Timestamp;

public class AttorneyrecordDTO {
	private long aId;
	private String attorney;
	private String attorneyId;
	private String ckcontent;
	private String ckmonth;
	private Timestamp cktime;
	private String flag;
	private String masteridno;
	private String mastername;
	private Timestamp opertime;
	private Timestamp uploadtime;
	private String upoper;
	private String wtdanwei;
	private Timestamp wttime;
	
	public long getaId() {
		return aId;
	}
	public void setaId(long aId) {
		this.aId = aId;
	}
	public String getAttorney() {
		return attorney;
	}
	public void setAttorney(String attorney) {
		this.attorney = attorney;
	}
	public String getAttorneyId() {
		return attorneyId;
	}
	public void setAttorneyId(String attorneyId) {
		this.attorneyId = attorneyId;
	}
	public String getCkcontent() {
		return ckcontent;
	}
	public void setCkcontent(String ckcontent) {
		this.ckcontent = ckcontent;
	}
	public String getCkmonth() {
		return ckmonth;
	}
	public void setCkmonth(String ckmonth) {
		this.ckmonth = ckmonth;
	}
	public Timestamp getCktime() {
		return cktime;
	}
	public void setCktime(Timestamp cktime) {
		this.cktime = cktime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMasteridno() {
		return masteridno;
	}
	public void setMasteridno(String masteridno) {
		this.masteridno = masteridno;
	}
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	public Timestamp getOpertime() {
		return opertime;
	}
	public void setOpertime(Timestamp opertime) {
		this.opertime = opertime;
	}
	public Timestamp getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Timestamp uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getUpoper() {
		return upoper;
	}
	public void setUpoper(String upoper) {
		this.upoper = upoper;
	}
	public String getWtdanwei() {
		return wtdanwei;
	}
	public void setWtdanwei(String wtdanwei) {
		this.wtdanwei = wtdanwei;
	}
	public Timestamp getWttime() {
		return wttime;
	}
	public void setWttime(Timestamp wttime) {
		this.wttime = wttime;
	}
}
