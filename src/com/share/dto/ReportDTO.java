package com.share.dto;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportDTO {

	private String col1;
	private JRBeanCollectionDataSource users;

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public JRBeanCollectionDataSource getUsers() {
		return users;
	}

	public void setUsers(JRBeanCollectionDataSource users) {
		this.users = users;
	}

}
