package com.share.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SYS_MPRELATION database table.
 * 
 */
@Entity
@Table(name="SYS_MPRELATION")
@NamedQuery(name="SysMprelation.findAll", query="SELECT s FROM SysMprelation s")
public class SysMprelation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="MENU_ID", precision=12)
	private java.math.BigDecimal menuId;

	@Column(name="PRIVILEGE_ID", precision=12)
	private java.math.BigDecimal privilegeId;

	public SysMprelation() {
	}

	public java.math.BigDecimal getMenuId() {
		return this.menuId;
	}

	public void setMenuId(java.math.BigDecimal menuId) {
		this.menuId = menuId;
	}

	public java.math.BigDecimal getPrivilegeId() {
		return this.privilegeId;
	}

	public void setPrivilegeId(java.math.BigDecimal privilegeId) {
		this.privilegeId = privilegeId;
	}

}