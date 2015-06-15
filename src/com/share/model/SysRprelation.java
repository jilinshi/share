package com.share.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SYS_RPRELATION database table.
 * 
 */
@Entity
@Table(name="SYS_RPRELATION")
@NamedQuery(name="SysRprelation.findAll", query="SELECT s FROM SysRprelation s")
public class SysRprelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="PRIVILEGE_ID", precision=12)
	private java.math.BigDecimal privilegeId;
	@Id
	@Column(name="ROLE_ID", precision=12)
	private java.math.BigDecimal roleId;

	public SysRprelation() {
	}

	public java.math.BigDecimal getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(java.math.BigDecimal privilegeId) {
		this.privilegeId = privilegeId;
	}

	public java.math.BigDecimal getRoleId() {
		return this.roleId;
	}

	public void setRoleId(java.math.BigDecimal roleId) {
		this.roleId = roleId;
	}

}