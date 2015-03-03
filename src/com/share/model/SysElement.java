package com.share.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SYS_ELEMENTS database table.
 * 
 */
@Entity
@Table(name="SYS_ELEMENTS")
@NamedQuery(name="SysElement.findAll", query="SELECT s FROM SysElement s")
public class SysElement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYS_ELEMENTS_ELEMENTID_GENERATOR", sequenceName="SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_ELEMENTS_ELEMENTID_GENERATOR")
	@Column(name="ELEMENT_ID", unique=true, nullable=false, precision=12)
	private long elementId;

	//bi-directional many-to-many association to SysPrivilege
	@ManyToMany(mappedBy="sysElements")
	private List<SysPrivilege> sysPrivileges;

	public SysElement() {
	}

	public long getElementId() {
		return this.elementId;
	}

	public void setElementId(long elementId) {
		this.elementId = elementId;
	}

	public List<SysPrivilege> getSysPrivileges() {
		return this.sysPrivileges;
	}

	public void setSysPrivileges(List<SysPrivilege> sysPrivileges) {
		this.sysPrivileges = sysPrivileges;
	}

}