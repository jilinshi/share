package com.share.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the H_PERSONALINFO_LS database table.
 * 
 */
@Embeddable
public class HPersonalinfoLPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PI_ID", unique=true, nullable=false, length=256)
	private String piId;

	@Column(unique=true, nullable=false, length=32)
	private String hsubject;

	public HPersonalinfoLPK() {
	}
	public String getPiId() {
		return this.piId;
	}
	public void setPiId(String piId) {
		this.piId = piId;
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
		if (!(other instanceof HPersonalinfoLPK)) {
			return false;
		}
		HPersonalinfoLPK castOther = (HPersonalinfoLPK)other;
		return 
			this.piId.equals(castOther.piId)
			&& this.hsubject.equals(castOther.hsubject);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.piId.hashCode();
		hash = hash * prime + this.hsubject.hashCode();
		
		return hash;
	}
}