package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HCK_INSURANCE database table.
 * 
 */
@Embeddable
public class HckInsurancePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=32)
	private String hsubject;

	@Column(name="IN_ID", unique=true, nullable=false, precision=12)
	private long inId;

	public HckInsurancePK() {
	}
	public String getHsubject() {
		return this.hsubject;
	}
	public void setHsubject(String hsubject) {
		this.hsubject = hsubject;
	}
	public long getInId() {
		return this.inId;
	}
	public void setInId(long inId) {
		this.inId = inId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HckInsurancePK)) {
			return false;
		}
		HckInsurancePK castOther = (HckInsurancePK)other;
		return 
			this.hsubject.equals(castOther.hsubject)
			&& (this.inId == castOther.inId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.hsubject.hashCode();
		hash = hash * prime + ((int) (this.inId ^ (this.inId >>> 32)));
		
		return hash;
	}
}