package com.share.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SYS_URRELATION database table.
 * 
 */
@Entity
@Table(name="SYS_URRELATION")
@NamedQuery(name="SysUrrelation.findAll", query="SELECT s FROM SysUrrelation s")
public class SysUrrelation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ROLE_ID", precision=12)
	private java.math.BigDecimal roleId;

	@Column(name="USER_ID", precision=12)
	private java.math.BigDecimal userId;

	public SysUrrelation() {
	}

	public java.math.BigDecimal getRoleId() {
		return this.roleId;
	}

	public void setRoleId(java.math.BigDecimal roleId) {
		this.roleId = roleId;
	}

	public java.math.BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(java.math.BigDecimal userId) {
		this.userId = userId;
	}

}