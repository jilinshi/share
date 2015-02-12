package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the SYS_T_EMPLOYEE database table.
 * 
 */
@Entity
@Table(name = "SYS_T_EMPLOYEE")
public class SysTEmployee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 50)
	private String account;

	@Column(length = 20)
	private String empidcard;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID", length = 50)
	private String employeeId;

	@Column(length = 128)
	private String idcard;

	@Column(length = 128)
	private String ipaddr;

	@Column(precision = 22)
	private BigDecimal isadmin;

	@Column(name = "ORGANIZATION_ID", length = 50)
	private String organizationId;

	@Column(length = 50)
	private String password;

	public SysTEmployee() {
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmpidcard() {
		return this.empidcard;
	}

	public void setEmpidcard(String empidcard) {
		this.empidcard = empidcard;
	}

	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getIpaddr() {
		return this.ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public BigDecimal getIsadmin() {
		return this.isadmin;
	}

	public void setIsadmin(BigDecimal isadmin) {
		this.isadmin = isadmin;
	}

	public String getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}