package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SYS_DICTIONARY database table.
 * 
 */
@Entity
@Table(name="SYS_DICTIONARY")
@NamedQuery(name="SysDictionary.findAll", query="SELECT s FROM SysDictionary s")
public class SysDictionary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_DICTIONARY_DICTKEY_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_DICTIONARY_DICTKEY_GENERATOR")
	@Column(unique=true, nullable=false, length=256)
	private String dictkey;

	@Column(length=256)
	private String dict;

	@Column(length=256)
	private String dictvalue;

	@Column(length=16)
	private String flag;

	@Column(precision=12)
	private BigDecimal sequ;

	public SysDictionary() {
	}

	public String getDictkey() {
		return this.dictkey;
	}

	public void setDictkey(String dictkey) {
		this.dictkey = dictkey;
	}

	public String getDict() {
		return this.dict;
	}

	public void setDict(String dict) {
		this.dict = dict;
	}

	public String getDictvalue() {
		return this.dictvalue;
	}

	public void setDictvalue(String dictvalue) {
		this.dictvalue = dictvalue;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BigDecimal getSequ() {
		return this.sequ;
	}

	public void setSequ(BigDecimal sequ) {
		this.sequ = sequ;
	}

}