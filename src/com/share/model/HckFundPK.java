package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HCK_FUND database table.
 * 
 */
@Embeddable
public class HckFundPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=32)
	private String hsubject;

	@Column(name="GJJ_ID", unique=true, nullable=false, precision=12)
	private long gjjId;

	public HckFundPK() {
	}
	public String getHsubject() {
		return this.hsubject;
	}
	public void setHsubject(String hsubject) {
		this.hsubject = hsubject;
	}
	public long getGjjId() {
		return this.gjjId;
	}
	public void setGjjId(long gjjId) {
		this.gjjId = gjjId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HckFundPK)) {
			return false;
		}
		HckFundPK castOther = (HckFundPK)other;
		return 
			this.hsubject.equals(castOther.hsubject)
			&& (this.gjjId == castOther.gjjId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.hsubject.hashCode();
		hash = hash * prime + ((int) (this.gjjId ^ (this.gjjId >>> 32)));
		
		return hash;
	}
}