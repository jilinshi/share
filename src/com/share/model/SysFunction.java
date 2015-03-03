package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SYS_FUNCTION database table.
 * 
 */
@Entity
@Table(name="SYS_FUNCTION")
@NamedQuery(name="SysFunction.findAll", query="SELECT s FROM SysFunction s")
public class SysFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_FUNCTION_FUNCTIONID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_FUNCTION_FUNCTIONID_GENERATOR")
	@Column(name="FUNCTION_ID", unique=true, nullable=false, precision=12)
	private long functionId;

	//bi-directional many-to-many association to SysPrivilege
	@ManyToMany(mappedBy="sysFunctions")
	private List<SysPrivilege> sysPrivileges;

	public SysFunction() {
	}

	public long getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(long functionId) {
		this.functionId = functionId;
	}

	public List<SysPrivilege> getSysPrivileges() {
		return this.sysPrivileges;
	}

	public void setSysPrivileges(List<SysPrivilege> sysPrivileges) {
		this.sysPrivileges = sysPrivileges;
	}

}