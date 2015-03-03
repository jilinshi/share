package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HCK_HOUSEPROPERTY database table.
 * 
 */
@Embeddable
public class HckHousepropertyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="F_ID", unique=true, nullable=false, precision=12)
	private long fId;

	@Column(unique=true, nullable=false, length=32)
	private String hsubject;

	public HckHousepropertyPK() {
	}
	public long getFId() {
		return this.fId;
	}
	public void setFId(long fId) {
		this.fId = fId;
	}
	public String getHsubject() {
		return this.hsubject;
	}
	public void setHsubject(String hsubject) {
		this.hsubject = hsubject;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HckHousepropertyPK)) {
			return false;
		}
		HckHousepropertyPK castOther = (HckHousepropertyPK)other;
		return 
			(this.fId == castOther.fId)
			&& this.hsubject.equals(castOther.hsubject);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.fId ^ (this.fId >>> 32)));
		hash = hash * prime + this.hsubject.hashCode();
		
		return hash;
	}
}