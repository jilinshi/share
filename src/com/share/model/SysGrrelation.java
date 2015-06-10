package com.share.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SYS_GRRELATION database table.
 * 
 */
@Entity
@Table(name="SYS_GRRELATION")
@NamedQuery(name="SysGrrelation.findAll", query="SELECT s FROM SysGrrelation s")
public class SysGrrelation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ROLE_ID", precision=12)
	private java.math.BigDecimal roleId;
	
	@Column(name="UG_ID", precision=12)
	private java.math.BigDecimal ugId;

	public SysGrrelation() {
	}

	public java.math.BigDecimal getRoleId() {
		return this.roleId;
	}

	public void setRoleId(java.math.BigDecimal roleId) {
		this.roleId = roleId;
	}

	public java.math.BigDecimal getUgId() {
		return this.ugId;
	}

	public void setUgId(java.math.BigDecimal ugId) {
		this.ugId = ugId;
	}

}