package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the IMPDATAINFO database table.
 * 
 */
@Entity
@Table(name="IMPDATAINFO")
@NamedQuery(name="Impdatainfo.findAll", query="SELECT i FROM Impdatainfo i")
public class Impdatainfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="IMPDATAINFO_INFOID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IMPDATAINFO_INFOID_GENERATOR")
	@Column(name="INFO_ID", unique=true, nullable=false, precision=22)
	private Long infoId;

	@Column(name="FILE_ID", precision=22)
	private BigDecimal fileId;

	@Column(length=256)
	private String filename;

	@Column(length=256)
	private String operstat;

	private Timestamp opertime;

	@Column(length=256)
	private String realpath;

	@Column(name="USER_ID", precision=22)
	private BigDecimal userId;

	//bi-directional many-to-one association to CkBurial
	@OneToMany(mappedBy="impdatainfo")
	private List<CkBurial> ckBurials;

	//bi-directional many-to-one association to CkFund
	@OneToMany(mappedBy="impdatainfo")
	private List<CkFund> ckFunds;

	//bi-directional many-to-one association to CkHouseproperty
	@OneToMany(mappedBy="impdatainfo")
	private List<CkHouseproperty> ckHouseproperties;

	//bi-directional many-to-one association to CkInsurance
	@OneToMany(mappedBy="impdatainfo")
	private List<CkInsurance> ckInsurances;

	//bi-directional many-to-one association to CkVehicle
	@OneToMany(mappedBy="impdatainfo")
	private List<CkVehicle> ckVehicles;

	public Impdatainfo() {
	}

	public Long getInfoId() {
		return this.infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public BigDecimal getFileId() {
		return this.fileId;
	}

	public void setFileId(BigDecimal fileId) {
		this.fileId = fileId;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getOperstat() {
		return this.operstat;
	}

	public void setOperstat(String operstat) {
		this.operstat = operstat;
	}

	public Timestamp getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Timestamp opertime) {
		this.opertime = opertime;
	}

	public String getRealpath() {
		return this.realpath;
	}

	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	public List<CkBurial> getCkBurials() {
		return this.ckBurials;
	}

	public void setCkBurials(List<CkBurial> ckBurials) {
		this.ckBurials = ckBurials;
	}

	public CkBurial addCkBurial(CkBurial ckBurial) {
		getCkBurials().add(ckBurial);
		ckBurial.setImpdatainfo(this);

		return ckBurial;
	}

	public CkBurial removeCkBurial(CkBurial ckBurial) {
		getCkBurials().remove(ckBurial);
		ckBurial.setImpdatainfo(null);

		return ckBurial;
	}

	public List<CkFund> getCkFunds() {
		return this.ckFunds;
	}

	public void setCkFunds(List<CkFund> ckFunds) {
		this.ckFunds = ckFunds;
	}

	public CkFund addCkFund(CkFund ckFund) {
		getCkFunds().add(ckFund);
		ckFund.setImpdatainfo(this);

		return ckFund;
	}

	public CkFund removeCkFund(CkFund ckFund) {
		getCkFunds().remove(ckFund);
		ckFund.setImpdatainfo(null);

		return ckFund;
	}

	public List<CkHouseproperty> getCkHouseproperties() {
		return this.ckHouseproperties;
	}

	public void setCkHouseproperties(List<CkHouseproperty> ckHouseproperties) {
		this.ckHouseproperties = ckHouseproperties;
	}

	public CkHouseproperty addCkHouseproperty(CkHouseproperty ckHouseproperty) {
		getCkHouseproperties().add(ckHouseproperty);
		ckHouseproperty.setImpdatainfo(this);

		return ckHouseproperty;
	}

	public CkHouseproperty removeCkHouseproperty(CkHouseproperty ckHouseproperty) {
		getCkHouseproperties().remove(ckHouseproperty);
		ckHouseproperty.setImpdatainfo(null);

		return ckHouseproperty;
	}

	public List<CkInsurance> getCkInsurances() {
		return this.ckInsurances;
	}

	public void setCkInsurances(List<CkInsurance> ckInsurances) {
		this.ckInsurances = ckInsurances;
	}

	public CkInsurance addCkInsurance(CkInsurance ckInsurance) {
		getCkInsurances().add(ckInsurance);
		ckInsurance.setImpdatainfo(this);

		return ckInsurance;
	}

	public CkInsurance removeCkInsurance(CkInsurance ckInsurance) {
		getCkInsurances().remove(ckInsurance);
		ckInsurance.setImpdatainfo(null);

		return ckInsurance;
	}

	public List<CkVehicle> getCkVehicles() {
		return this.ckVehicles;
	}

	public void setCkVehicles(List<CkVehicle> ckVehicles) {
		this.ckVehicles = ckVehicles;
	}

	public CkVehicle addCkVehicle(CkVehicle ckVehicle) {
		getCkVehicles().add(ckVehicle);
		ckVehicle.setImpdatainfo(this);

		return ckVehicle;
	}

	public CkVehicle removeCkVehicle(CkVehicle ckVehicle) {
		getCkVehicles().remove(ckVehicle);
		ckVehicle.setImpdatainfo(null);

		return ckVehicle;
	}

}