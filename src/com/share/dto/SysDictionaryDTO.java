package com.share.dto;

import java.math.BigDecimal;

public class SysDictionaryDTO {
	
	private String dictkey;
	private String dict;
	private String dictvalue;
	private String flag;
	private BigDecimal sequ;
	public String getDictkey() {
		return dictkey;
	}
	public void setDictkey(String dictkey) {
		this.dictkey = dictkey;
	}
	public String getDict() {
		return dict;
	}
	public void setDict(String dict) {
		this.dict = dict;
	}
	public String getDictvalue() {
		return dictvalue;
	}
	public void setDictvalue(String dictvalue) {
		this.dictvalue = dictvalue;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public BigDecimal getSequ() {
		return sequ;
	}
	public void setSequ(BigDecimal sequ) {
		this.sequ = sequ;
	}
	
}
