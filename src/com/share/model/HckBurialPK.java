package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HCK_BURIAL database table.
 * 
 */
@Embeddable
public class HckBurialPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=32)
	private String hsubject;

	@Column(name="B_ID", unique=true, nullable=false, precision=12)
	private long bId;

	public HckBurialPK() {
	}
	public String getHsubject() {
		return this.hsubject;
	}
	public void setHsubject(String hsubject) {
		this.hsubject = hsubject;
	}
	public long getBId() {
		return this.bId;
	}
	public void setBId(long bId) {
		this.bId = bId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HckBurialPK)) {
			return false;
		}
		HckBurialPK castOther = (HckBurialPK)other;
		return 
			this.hsubject.equals(castOther.hsubject)
			&& (this.bId == castOther.bId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.hsubject.hashCode();
		hash = hash * prime + ((int) (this.bId ^ (this.bId >>> 32)));
		
		return hash;
	}
}