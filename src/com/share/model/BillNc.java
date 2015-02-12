package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the BILL_NC database table.
 * 
 */
@Entity
@Table(name="BILL_NC")
public class BillNc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BAR_AWARDFLAG")
	private String barAwardflag;

	@Column(name="BAR_BANK_ACCOUNTS")
	private String barBankAccounts;

	@Column(name="BAR_FAMILYCOUNT")
	private BigDecimal barFamilycount;

	@Column(name="BAR_FAMILYID")
	private String barFamilyid;

	@Column(name="BAR_FAMILYNO")
	private String barFamilyno;

	@Column(name="BAR_FMIDCARD")
	private String barFmidcard;
	@Id
	@Column(name="BAR_ID")
	private String barId;

	@Column(name="BAR_MASTER")
	private String barMaster;

	@Column(name="BAR_MONEY")
	private BigDecimal barMoney;

	@Column(name="BAR_SUBJECT")
	private String barSubject;

	@Column(name="ON_NO")
	private String onNo;

	@Column(name="SA_ID")
	private String saId;

	@Column(name="SB_ID")
	private String sbId;

	@Column(name="ST_ID")
	private String stId;

	public BillNc() {
	}

	public String getBarAwardflag() {
		return this.barAwardflag;
	}

	public void setBarAwardflag(String barAwardflag) {
		this.barAwardflag = barAwardflag;
	}

	public String getBarBankAccounts() {
		return this.barBankAccounts;
	}

	public void setBarBankAccounts(String barBankAccounts) {
		this.barBankAccounts = barBankAccounts;
	}

	public BigDecimal getBarFamilycount() {
		return this.barFamilycount;
	}

	public void setBarFamilycount(BigDecimal barFamilycount) {
		this.barFamilycount = barFamilycount;
	}

	public String getBarFamilyid() {
		return this.barFamilyid;
	}

	public void setBarFamilyid(String barFamilyid) {
		this.barFamilyid = barFamilyid;
	}

	public String getBarFamilyno() {
		return this.barFamilyno;
	}

	public void setBarFamilyno(String barFamilyno) {
		this.barFamilyno = barFamilyno;
	}

	public String getBarFmidcard() {
		return this.barFmidcard;
	}

	public void setBarFmidcard(String barFmidcard) {
		this.barFmidcard = barFmidcard;
	}

	public String getBarId() {
		return this.barId;
	}

	public void setBarId(String barId) {
		this.barId = barId;
	}

	public String getBarMaster() {
		return this.barMaster;
	}

	public void setBarMaster(String barMaster) {
		this.barMaster = barMaster;
	}

	public BigDecimal getBarMoney() {
		return this.barMoney;
	}

	public void setBarMoney(BigDecimal barMoney) {
		this.barMoney = barMoney;
	}

	public String getBarSubject() {
		return this.barSubject;
	}

	public void setBarSubject(String barSubject) {
		this.barSubject = barSubject;
	}

	public String getOnNo() {
		return this.onNo;
	}

	public void setOnNo(String onNo) {
		this.onNo = onNo;
	}

	public String getSaId() {
		return this.saId;
	}

	public void setSaId(String saId) {
		this.saId = saId;
	}

	public String getSbId() {
		return this.sbId;
	}

	public void setSbId(String sbId) {
		this.sbId = sbId;
	}

	public String getStId() {
		return this.stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

}