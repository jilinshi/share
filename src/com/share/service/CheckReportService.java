package com.share.service;

import java.util.List;

import com.share.model.Attorneyrecord;
import com.share.model.VAttorney;
import com.share.util.Pager;

public interface CheckReportService {
	/**
	 * ��ѯ����������Ȩ��
	 * @param jwhere 
	 * @param param 
	 * @param pager 
	 * 
	 * @return
	 */
	public List<VAttorney> findAllAttorneyrecord(Pager pager, List<Object> param, String jwhere);

	/**
	 * ͨ�����֤�Ų�ѯ��Ȩ��
	 * 
	 * @return
	 */
	public Attorneyrecord findAttorneyrecordByMaster(); 
}
