package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HCK_VEHICLE database table.
 * 
 */
@Embeddable
public class HckVehiclePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="V_ID", unique=true, nullable=false, precision=12)
	private long vId;

	@Column(unique=true, nullable=false, length=32)
	private String hsubject;

	public HckVehiclePK() {
	}
	public long getVId() {
		return this.vId;
	}
	public void setVId(long vId) {
		this.vId = vId;
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
		if (!(other instanceof HckVehiclePK)) {
			return false;
		}
		HckVehiclePK castOther = (HckVehiclePK)other;
		return 
			(this.vId == castOther.vId)
			&& this.hsubject.equals(castOther.hsubject);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.vId ^ (this.vId >>> 32)));
		hash = hash * prime + this.hsubject.hashCode();
		
		return hash;
	}
}