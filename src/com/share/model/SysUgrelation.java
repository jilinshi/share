package com.share.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SYS_UGRELATION database table.
 * 
 */
@Entity
@Table(name="SYS_UGRELATION")
@NamedQuery(name="SysUgrelation.findAll", query="SELECT s FROM SysUgrelation s")
public class SysUgrelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="UG_ID", precision=12)
	private java.math.BigDecimal ugId;

	@Id
	@Column(name="USER_ID", precision=12)
	private java.math.BigDecimal userId;

	public SysUgrelation() {
	}

	public java.math.BigDecimal getUgId() {
		return this.ugId;
	}

	public void setUgId(java.math.BigDecimal ugId) {
		this.ugId = ugId;
	}

	public java.math.BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(java.math.BigDecimal userId) {
		this.userId = userId;
	}

}