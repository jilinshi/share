package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MEMBER_BASEINFO database table.
 * 
 */
@Entity
@Table(name="MEMBER_BASEINFO")
public class MemberBaseinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=200)
	private String address;

	@Column(name="AN_FILENAME", length=256)
	private String anFilename;

	@Column(precision=22)
	private BigDecimal asort;

	@Column(name="ASSIST_TYPE", length=2)
	private String assistType;

	@Column(length=50)
	private String assistcardid;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(length=20)
	private String defgrade;

	@Column(length=20)
	private String deformity;

	@Column(length=1)
	private String ds;

	@Column(length=50)
	private String familyno;

	@Column(length=50)
	private String health;

	@Column(precision=22)
	private BigDecimal isybsqdb;

	@Column(length=200)
	private String linkmode;

	@Column(name="MASTER_NAME", length=50)
	private String masterName;
	@Id
	@Column(name="MEMBER_ID", length=50)
	private String memberId;

	@Column(length=50)
	private String membername;

	@Column(name="O_PS", length=50)
	private String oPs;

	@Column(name="ON_NO", length=50)
	private String onNo;

	@Lob
	private byte[] onepic;

	@Temporal(TemporalType.DATE)
	private Date opertime;

	@Column(length=20)
	private String paperid;

	@Column(length=20)
	private String personstate;

	@Column(length=20)
	private String relmaster;

	@Column(length=200)
	private String rpraddress;

	@Column(length=20)
	private String rprkind;

	@Column(length=50)
	private String rprtype;

	@Column(length=2)
	private String sex;

	@Column(length=200)
	private String sickenname;

	@Column(length=50)
	private String sickentype;

	@Column(length=50)
	private String ssn;

	public MemberBaseinfo() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAnFilename() {
		return this.anFilename;
	}

	public void setAnFilename(String anFilename) {
		this.anFilename = anFilename;
	}

	public BigDecimal getAsort() {
		return this.asort;
	}

	public void setAsort(BigDecimal asort) {
		this.asort = asort;
	}

	public String getAssistType() {
		return this.assistType;
	}

	public void setAssistType(String assistType) {
		this.assistType = assistType;
	}

	public String getAssistcardid() {
		return this.assistcardid;
	}

	public void setAssistcardid(String assistcardid) {
		this.assistcardid = assistcardid;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDefgrade() {
		return this.defgrade;
	}

	public void setDefgrade(String defgrade) {
		this.defgrade = defgrade;
	}

	public String getDeformity() {
		return this.deformity;
	}

	public void setDeformity(String deformity) {
		this.deformity = deformity;
	}

	public String getDs() {
		return this.ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
	}

	public String getFamilyno() {
		return this.familyno;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public String getHealth() {
		return this.health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public BigDecimal getIsybsqdb() {
		return this.isybsqdb;
	}

	public void setIsybsqdb(BigDecimal isybsqdb) {
		this.isybsqdb = isybsqdb;
	}

	public String getLinkmode() {
		return this.linkmode;
	}

	public void setLinkmode(String linkmode) {
		this.linkmode = linkmode;
	}

	public String getMasterName() {
		return this.masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMembername() {
		return this.membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getOPs() {
		return this.oPs;
	}

	public void setOPs(String oPs) {
		this.oPs = oPs;
	}

	public String getOnNo() {
		return this.onNo;
	}

	public void setOnNo(String onNo) {
		this.onNo = onNo;
	}

	public byte[] getOnepic() {
		return this.onepic;
	}

	public void setOnepic(byte[] onepic) {
		this.onepic = onepic;
	}

	public Date getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}

	public String getPaperid() {
		return this.paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public String getPersonstate() {
		return this.personstate;
	}

	public void setPersonstate(String personstate) {
		this.personstate = personstate;
	}

	public String getRelmaster() {
		return this.relmaster;
	}

	public void setRelmaster(String relmaster) {
		this.relmaster = relmaster;
	}

	public String getRpraddress() {
		return this.rpraddress;
	}

	public void setRpraddress(String rpraddress) {
		this.rpraddress = rpraddress;
	}

	public String getRprkind() {
		return this.rprkind;
	}

	public void setRprkind(String rprkind) {
		this.rprkind = rprkind;
	}

	public String getRprtype() {
		return this.rprtype;
	}

	public void setRprtype(String rprtype) {
		this.rprtype = rprtype;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSickenname() {
		return this.sickenname;
	}

	public void setSickenname(String sickenname) {
		this.sickenname = sickenname;
	}

	public String getSickentype() {
		return this.sickentype;
	}

	public void setSickentype(String sickentype) {
		this.sickentype = sickentype;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

}