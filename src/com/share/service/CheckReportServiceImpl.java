package com.share.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.share.dao.BaseDAO;
import com.share.model.Attorneyrecord;
import com.share.model.Personalinfo;
import com.share.model.VAttorney;
import com.share.util.Pager;

@Service("checkReportService")
public class CheckReportServiceImpl implements CheckReportService {
	@Resource
	private BaseDAO<VAttorney> vAttorneyDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.share.service.CheckReportService#findAttorneyrecordByMaster()
	 */
	@Override
	public Attorneyrecord findAttorneyrecordByMaster() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.share.service.CheckReportService#findAllAttorneyrecord(com.share.
	 * util.Pager, java.util.List, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<VAttorney> findAllAttorneyrecord(Pager pager,
			List<Object> param, String jwhere) {
		Map session = ActionContext.getContext().getSession();
		String hql = "select p from VAttorney p where 1=1"
				+ jwhere;
		String hqlc = "select count(*) as cnt from VAttorney p where 1=1 "
				+ jwhere;
		Long cnt = vAttorneyDAO.count(hqlc, param);
		pager.setRecords(cnt);
		List<VAttorney> rs = vAttorneyDAO.find(hql, param, pager.pager,
				pager.rows);
		session.put("hql", hql);
		session.put("param", param);
		return rs;
	}

}
