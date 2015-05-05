package com.share.service;

import java.util.List;

import com.share.model.Attorneyrecord;
import com.share.model.VAttorney;
import com.share.util.Pager;

public interface CheckReportService {
	/**
	 * 查询当月所有授权书
	 * @param jwhere 
	 * @param param 
	 * @param pager 
	 * 
	 * @return
	 */
	public List<VAttorney> findAllAttorneyrecord(Pager pager, List<Object> param, String jwhere);

	/**
	 * 通过身份证号查询授权书
	 * 
	 * @return
	 */
	public Attorneyrecord findAttorneyrecordByMaster(); 
}
