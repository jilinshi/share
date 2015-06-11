package com.share.dto;

import java.sql.Timestamp;

public class FunctionDTO {
	
	private long functionId;

	private Timestamp ctime;

	private String flag;

	private String funcdescr;

	private String funcname;

	private String matching;

	private String remark;

	public long getFunctionId() {
		return functionId;
	}

	public void setFunctionId(long functionId) {
		this.functionId = functionId;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFuncdescr() {
		return funcdescr;
	}

	public void setFuncdescr(String funcdescr) {
		this.funcdescr = funcdescr;
	}

	public String getFuncname() {
		return funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public String getMatching() {
		return matching;
	}

	public void setMatching(String matching) {
		this.matching = matching;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
