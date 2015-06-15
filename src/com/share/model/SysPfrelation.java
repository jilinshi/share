package com.share.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SYS_PFRELATION database table.
 * 
 */
@Entity
@Table(name="SYS_PFRELATION")
@NamedQuery(name="SysPfrelation.findAll", query="SELECT s FROM SysPfrelation s")
public class SysPfrelation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="FUNCTION_ID", precision=12)
	private java.math.BigDecimal functionId;

	@Column(name="PRIVILEGE_ID", precision=12)
	private java.math.BigDecimal privilegeId;

	public SysPfrelation() {
	}

	public java.math.BigDecimal getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(java.math.BigDecimal functionId) {
		this.functionId = functionId;
	}

	public java.math.BigDecimal getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(java.math.BigDecimal privilegeId) {
		this.privilegeId = privilegeId;
	}

}