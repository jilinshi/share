package com.share.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SYS_UNITCODE database table.
 * 
 */
@Entity
@Table(name="SYS_UNITCODE")
@NamedQuery(name="SysUnitcode.findAll", query="SELECT s FROM SysUnitcode s")
public class SysUnitcode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_UNITCODE_UID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_UNITCODE_UID_GENERATOR")
	@Column(name="U_ID", unique=true, nullable=false, precision=12)
	private long uId;

	@Column(length=256)
	private String flag;

	@Column(length=256)
	private String uaddr;

	@Column(length=256)
	private String ucode;

	@Column(length=256)
	private String uname;

	public SysUnitcode() {
	}

	public long getUId() {
		return this.uId;
	}

	public void setUId(long uId) {
		this.uId = uId;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUaddr() {
		return this.uaddr;
	}

	public void setUaddr(String uaddr) {
		this.uaddr = uaddr;
	}

	public String getUcode() {
		return this.ucode;
	}

	public void setUcode(String ucode) {
		this.ucode = ucode;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}