package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
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

	private Timestamp ctime;

	@Column(length=256)
	private String flag;

	@Column(length=256)
	private String funcdescr;

	@Column(length=256)
	private String funcname;

	@Column(length=256)
	private String matching;

	@Column(length=256)
	private String remark;

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

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFuncdescr() {
		return this.funcdescr;
	}

	public void setFuncdescr(String funcdescr) {
		this.funcdescr = funcdescr;
	}

	public String getFuncname() {
		return this.funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public String getMatching() {
		return this.matching;
	}

	public void setMatching(String matching) {
		this.matching = matching;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<SysPrivilege> getSysPrivileges() {
		return this.sysPrivileges;
	}

	public void setSysPrivileges(List<SysPrivilege> sysPrivileges) {
		this.sysPrivileges = sysPrivileges;
	}

}