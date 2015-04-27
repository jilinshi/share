package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the H_ATTORNEYRECORD database table.
 * 
 */
@Embeddable
public class HAttorneyrecordPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="A_ID", unique=true, nullable=false, precision=12)
	private long aId;

	@Column(unique=true, nullable=false, length=32)
	private String hsubject;

	public HAttorneyrecordPK() {
	}
	public long getAId() {
		return this.aId;
	}
	public void setAId(long aId) {
		this.aId = aId;
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
		if (!(other instanceof HAttorneyrecordPK)) {
			return false;
		}
		HAttorneyrecordPK castOther = (HAttorneyrecordPK)other;
		return 
			(this.aId == castOther.aId)
			&& this.hsubject.equals(castOther.hsubject);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.aId ^ (this.aId >>> 32)));
		hash = hash * prime + this.hsubject.hashCode();
		
		return hash;
	}
}