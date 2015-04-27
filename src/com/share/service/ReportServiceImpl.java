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
import com.share.util.Pager;

@Service("reportService")
public class ReportServiceImpl implements ReportService{
	
	@Resource
	private BaseDAO<Personalinfo> personalinfoDAO;
	@Resource
	private BaseDAO<SysOrganization> sysOrganizationDAO;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Personalinfo> queryPersonalinfos(Pager pager,
			List<Object> param, String jwhere) {
		Map session = ActionContext.getContext().getSession();
		String hql = "select p from Personalinfo p where 1=1 " + jwhere;
		String hqlc = "select count(*) as cnt from Personalinfo p where 1=1 "
				+ jwhere;
		Long cnt = personalinfoDAO.count(hqlc, param);
		pager.setRecords(cnt);
		List<Personalinfo> rs = personalinfoDAO.find(hql, param, pager.pager,
				pager.rows);
		session.put("hql", hql);
		session.put("param", param);
		return rs;
	}
	
	public List<OrganizationDTO> findOrganlist(long orgid) {
		List<OrganizationDTO> resultlist = new ArrayList<OrganizationDTO>();
		String hql = "select o from SysOrganization o where o.orgId = ? or o.parentId = ? order by  o.orgId";
		Object[] param = null; 
		param = new Object[2]; 
		param[0] = orgid;
		param[1] = new BigDecimal(orgid+"");
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

}
