package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SYS_DISTRICTS database table.
 * 
 */
@Entity
@Table(name="SYS_DISTRICTS")
@NamedQuery(name="SysDistrict.findAll", query="SELECT s FROM SysDistrict s")
public class SysDistrict implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DISTRICTS_ID", unique=true, nullable=false, length=256)
	private String districtsId;

	@Column(length=256)
	private String col1;

	@Column(length=256)
	private String col2;

	@Column(length=256)
	private String col3;

	@Column(name="DISTRICTS_CODE", length=256)
	private String districtsCode;

	@Column(name="DISTRICTS_NMAE", length=256)
	private String districtsNmae;

	@Column(length=256)
	private String flag;

	@Column(length=256)
	private String fullname;

	@Column(precision=12)
	private BigDecimal ord;

	@Column(name="PARENT_ID", length=256)
	private String parentId;

	//bi-directional many-to-one association to SysOrganization
	@OneToMany(mappedBy="sysDistrict")
	private List<SysOrganization> sysOrganizations;

	public SysDistrict() {
	}

	public String getDistrictsId() {
		return this.districtsId;
	}

	public void setDistrictsId(String districtsId) {
		this.districtsId = districtsId;
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

	public String getDistrictsCode() {
		return this.districtsCode;
	}

	public void setDistrictsCode(String districtsCode) {
		this.districtsCode = districtsCode;
	}

	public String getDistrictsNmae() {
		return this.districtsNmae;
	}

	public void setDistrictsNmae(String districtsNmae) {
		this.districtsNmae = districtsNmae;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public BigDecimal getOrd() {
		return this.ord;
	}

	public void setOrd(BigDecimal ord) {
		this.ord = ord;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<SysOrganization> getSysOrganizations() {
		return this.sysOrganizations;
	}

	public void setSysOrganizations(List<SysOrganization> sysOrganizations) {
		this.sysOrganizations = sysOrganizations;
	}

	public SysOrganization addSysOrganization(SysOrganization sysOrganization) {
		getSysOrganizations().add(sysOrganization);
		sysOrganization.setSysDistrict(this);

		return sysOrganization;
	}

	public SysOrganization removeSysOrganization(SysOrganization sysOrganization) {
		getSysOrganizations().remove(sysOrganization);
		sysOrganization.setSysDistrict(null);

		return sysOrganization;
	}

}