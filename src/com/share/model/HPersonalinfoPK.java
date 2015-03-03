package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the H_PERSONALINFO database table.
 * 
 */
@Embeddable
public class HPersonalinfoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false, length=18)
	private String hsubject;

	@Column(name="PI_ID", unique=true, nullable=false, precision=12)
	private long piId;

	public HPersonalinfoPK() {
	}
	public String getHsubject() {
		return this.hsubject;
	}
	public void setHsubject(String hsubject) {
		this.hsubject = hsubject;
	}
	public long getPiId() {
		return this.piId;
	}
	public void setPiId(long piId) {
		this.piId = piId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HPersonalinfoPK)) {
			return false;
		}
		HPersonalinfoPK castOther = (HPersonalinfoPK)other;
		return 
			this.hsubject.equals(castOther.hsubject)
			&& (this.piId == castOther.piId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.hsubject.hashCode();
		hash = hash * prime + ((int) (this.piId ^ (this.piId >>> 32)));
		
		return hash;
	}
}