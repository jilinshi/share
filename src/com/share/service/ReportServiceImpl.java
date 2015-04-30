package com.share.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.share.dao.BaseDAO;
import com.share.dto.OrganizationDTO;
import com.share.model.Personalinfo;
import com.share.model.SysOrganization;
import com.share.model.VCkburial;
import com.share.model.VCkfund;
import com.share.model.VCkhouseproperty;
import com.share.model.VCkinsurance;
import com.share.model.VCkvehicle;
import com.share.util.Pager;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	@Resource
	private BaseDAO<Personalinfo> personalinfoDAO;
	@Resource
	private BaseDAO<VCkinsurance> vCkinsuranceDAO;
	@Resource
	private BaseDAO<VCkburial> vCkburialDAO;
	@Resource
	private BaseDAO<VCkhouseproperty> vCkhousepropertyDAO;
	@Resource
	private BaseDAO<VCkvehicle> vCkvehicleDAO;
	@Resource
	private BaseDAO<VCkfund> vCkfundDAO;
	@Resource
	private BaseDAO<SysOrganization> sysOrganizationDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.share.service.ReportService#queryPersonalinfos(com.share.util.Pager,
	 * java.util.List, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Personalinfo> queryPersonalinfos(Pager pager,
			List<Object> param, String jwhere) {
		Map session = ActionContext.getContext().getSession();
		String hql = "select p from Personalinfo p where 1=1 and p.col10='本人' " + jwhere;
		String hqlc = "select count(*) as cnt from Personalinfo p where 1=1 and p.col10='本人' "
				+ jwhere;
		Long cnt = personalinfoDAO.count(hqlc, param);
		pager.setRecords(cnt);
		List<Personalinfo> rs = personalinfoDAO.find(hql, param, pager.pager,
				pager.rows);
		session.put("hql", hql);
		session.put("param", param);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.share.service.ReportService#findOrganlist(long)
	 */
	@Override
	public List<OrganizationDTO> findOrganlist(long orgid) {
		List<OrganizationDTO> resultlist = new ArrayList<OrganizationDTO>();
		String hql = "select o from SysOrganization o where o.orgId = ? or o.parentId = ? order by  o.orgId";
		Object[] param = null;
		param = new Object[2];
		param[0] = orgid;
		param[1] = new BigDecimal(orgid + "");
		List<SysOrganization> rs = sysOrganizationDAO.find(hql, param);
		for (SysOrganization s : rs) {
			OrganizationDTO e = new OrganizationDTO();
			e.setOrgId(s.getOrgId());
			e.setOrgCode(s.getOrgCode());
			e.setOrgName(s.getOrgName());
			e.setParentId(s.getParentId());
			resultlist.add(e);
		}
		return resultlist;
	}

	/**
	 * 用过户主身份证号查询成员列表
	 * 
	 * @param masterid
	 * @return
	 */
	@Override
	public List<Personalinfo> findPersonsByMAID(String masterid) {
		String hql = "select p from Personalinfo p where 1=1 and p.masterid=? order by p.col10";
		Object[] param = null;
		param = new Object[1];
		param[0] = masterid;
		return personalinfoDAO.find(hql, param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.share.service.ReportService#findInsuranceByMAID(java.lang.String)
	 */
	@Override
	public List<VCkinsurance> findInsuranceByMAID(String masterid) {
		String hql = "select v from VCkinsurance v where 1=1 and v.masterid=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = masterid;
		return vCkinsuranceDAO.find(hql, param);
	}

	@Override
	public List<VCkfund> findFundByMAID(String masterid) {
		String hql = "select v from VCkfund v where 1=1 and v.masterid=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = masterid;
		return vCkfundDAO.find(hql, param);
	}

	@Override
	public List<VCkhouseproperty> findHousepropertyByMAID(String masterid) {
		String hql = "select v from VCkhouseproperty v where 1=1 and v.masterid=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = masterid;
		return vCkhousepropertyDAO.find(hql, param);
	}

	@Override
	public List<VCkvehicle> findVehicleByMAID(String masterid) {
		String hql = "select v from VCkvehicle v where 1=1 and v.masterid=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = masterid;
		return vCkvehicleDAO.find(hql, param);
	}

	@Override
	public List<VCkburial> findBurialByMAID(String masterid) {
		String hql = "select v from VCkburial v where 1=1 and v.masterid=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = masterid;
		return vCkburialDAO.find(hql, param);
	}
	
	@Override
	public Long getPcountByFNO(String familyno){
		String hql = "select count(*) as cnt from Personalinfo p where 1=1 and p.col1=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = familyno;
		return personalinfoDAO.count(hql, param);
	}
}
