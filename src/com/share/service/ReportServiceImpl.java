package com.share.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.share.dao.BaseDAO;
import com.share.dto.AttorneyrecordDTO;
import com.share.dto.MemberDTO;
import com.share.dto.OrganizationDTO;
import com.share.model.Attorneyrecord;
import com.share.model.Personalinfo;
import com.share.model.PersonalinfoL;
import com.share.model.SysOrganization;
import com.share.model.VCkburial;
import com.share.model.VCkfund;
import com.share.model.VCkhouseproperty;
import com.share.model.VCkinsurance;
import com.share.model.VCkvehicle;
import com.share.util.IDCard;
import com.share.util.Pager;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	@Resource
	private BaseDAO<Personalinfo> personalinfoDAO;
	@Resource
	private BaseDAO<PersonalinfoL> personalinfoDAOL;
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
	@Resource
	private BaseDAO<Attorneyrecord> attorneyrecordDAO;

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
		param[1] = orgid+"";
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
	
	@Override
	public List<MemberDTO> getPersonsByFNO(String masterid){
		List<MemberDTO> mems = new ArrayList<MemberDTO>();
		String hql = "select p from Personalinfo p where 1=1 and p.masterid=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = masterid;
		List<Personalinfo> rs = personalinfoDAO.find(hql, param);
		for (Personalinfo s : rs) {
			MemberDTO m = new MemberDTO();
			m.setMasterName(s.getMastername());
			m.setMasetPaperid(s.getMasterid());
			m.setMembername(s.getPname());
			m.setPaperid(s.getIdno());
			m.setDs(s.getDs());
			m.setFamilyno(s.getCol1());
			m.setRelmaster(s.getCol10());
			m.setRpraddress(s.getCol11());
			mems.add(m);
		}
		return mems;
	}
	
	@Override
	public void saveAttorneyRecord(AttorneyrecordDTO ardto){
		Attorneyrecord o = new Attorneyrecord();
		o.setMasteridno(ardto.getMasteridno());
		o.setMastername(ardto.getMastername());
		o.setCkmonth(ardto.getCkmonth());
		o.setAttorneyId(ardto.getAttorneyId());
		o.setAttorney(ardto.getAttorney());
		o.setWtdanwei(ardto.getWtdanwei());
		o.setCkcontent(ardto.getCkcontent());
		long date = new Date().getTime();
		o.setWttime(new Timestamp(date));
		o.setOpertime(new Timestamp(date));
		o.setUploadtime(new Timestamp(date));
		o.setUpoper(ardto.getUpoper());
		o.setFlag("1");
		attorneyrecordDAO.save(o);
	}
	
	@Override
	public Long queryAttorneyRecordCount(AttorneyrecordDTO ardto){
		String hql = "select count(*) as cnt from Attorneyrecord ar where 1=1 and ar.masteridno=? and ar.ckmonth=? and ar.flag=?";
		Object[] param = null;
		param = new Object[3];
		param[0] = ardto.getMasteridno();
		param[1] = ardto.getCkmonth();
		param[2] = ardto.getFlag();
		return attorneyrecordDAO.count(hql, param);
	}
	
	@Override
	public List<Personalinfo> queryPersonalinfoAll(Pager pager,
			List<Object> param, String jwhere) {
		String hql = "select p from Personalinfo p where 1=1 " + jwhere + " order by p.col1, p.masterid";
		String hqlc = "select count(*) as cnt from Personalinfo p where 1=1 " + jwhere + " order by p.masterid";
		Long cnt = personalinfoDAO.count(hqlc, param);
		pager.setRecords(cnt);
		List<Personalinfo> rs = personalinfoDAO.find(hql, param, pager.pager,
				pager.rows);
		return rs;
	}
	
	@Override
	public void savePersonalinfo(MemberDTO m){
		String paperid = m.getPaperid();
		String id18="";
		String id15="";
		if(paperid.length()==15){
			id18 = IDCard.uptoeighteen(paperid);
			id15 = paperid;
		}else if(paperid.length()==18){
			id18 = paperid;
			id15 = IDCard.uptofifteen(paperid);
		}
		Personalinfo o = new Personalinfo();
		o.setId18(id18);
		o.setId15(id15);
		o.setIdno(m.getPaperid());
		o.setPname(m.getMembername());
		o.setMasterid(m.getMasetPaperid());
		o.setMastername(m.getMasterName());
		o.setCol10(m.getRelmaster());
		o.setCol11(m.getAddress());
		int random = new Random().nextInt(10000);
		o.setCol1(m.getOnNo()+random);
		o.setDs(m.getDs());
		long date = new Date().getTime();
		o.setUpdatetime(new Timestamp(date));
		personalinfoDAO.save(o);
		PersonalinfoL ol = new PersonalinfoL();
		BeanCopier copy = BeanCopier.create(Personalinfo.class, PersonalinfoL.class,false);
		copy.copy(o, ol, null);
		personalinfoDAOL.save(ol);
	};
	
	@Override
	public List<MemberDTO> queryPersonByCard(String idcard) {
		List<MemberDTO> mems = new ArrayList<MemberDTO>();
		String hql = "select p from Personalinfo p where 1=1 and p.id18=? or p.id15=? order by p.masterid";
		Object[] param = null;
		param = new Object[2];
		param[0] = idcard;
		param[1] = idcard;
		List<Personalinfo> rs = personalinfoDAO.find(hql, param);
		for(Personalinfo s: rs){
			MemberDTO m = new MemberDTO();
			m.setMembername(s.getPname());
			m.setPaperid(s.getIdno());
			m.setMasetPaperid(s.getMasterid());
			m.setMasterName(s.getMastername());
			m.setRelmaster(s.getCol10());
			m.setAddress(s.getCol11());
			m.setDs(s.getDs());
			m.setOnNo(s.getCol1());
			mems.add(m);
		}
		return mems;
	}
	
	@Override
	public int updatePersonalById(String piId){
		int u = 0;
		Object[] param = null;
		param = new Object[2];
		param[0] = "核对";
		param[1] = Long.parseLong(piId);
		String hql = "update Personalinfo p set p.remark=? where p.piId=?";
		u = personalinfoDAO.update(hql, param);
		return u;
	}
}
