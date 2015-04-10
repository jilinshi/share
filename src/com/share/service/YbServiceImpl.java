package com.share.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.share.dao.BaseDAO;
import com.share.dto.BillCsDTO;
import com.share.dto.BillNcDTO;
import com.share.dto.HousepropertyDTO;
import com.share.dto.InsuranceDTO;
import com.share.dto.MemberDTO;
import com.share.dto.MenuDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.UserDTO;
import com.share.model.SysMenus;
import com.share.model.SysUser;
import com.share.model.VCkhouseproperty;
import com.share.model.VCkinsurance;
import com.share.util.Pager;
import com.share.util.XmlExcel;

@Service("ybService")
public class YbServiceImpl implements YbService {

	@Resource
	private BaseDAO<SysUser> sysUserDAO;
	@Resource
	private BaseDAO<SysMenus> sysMeunsDAO;
	@Resource
	private BaseDAO<VCkinsurance> vCkinsuranceDAO;
	@Resource
	private BaseDAO<VCkhouseproperty> vCkhousepropertyDAO;

	public UserDTO findUser(UserDTO userDTO) {
		String hql = "";
		Object[] param = null;
		if ("95588".equals(userDTO.getToken())) {
			hql = "select emp from SysUser emp where emp.uaccount=? and emp.upwds=?";
			param = new Object[2];
			param[0] = userDTO.getUaccount();
			param[1] = userDTO.getUpwds();
		} else {
			hql = "select emp from SysUser emp where emp.uaccount=? and emp.upwds=? and emp.idno=?";
			param = new Object[3];
			param[0] = userDTO.getUaccount();
			param[1] = userDTO.getUpwds();
			param[2] = userDTO.getToken();
		}

		List<SysUser> rs = sysUserDAO.find(hql, param);
		if (null != rs && rs.size() > 0) {
			SysUser sysUser = rs.get(0);
			BeanUtils.copyProperties(sysUser, userDTO);
		} else {
			userDTO = null;
		}
		return userDTO;
	}

	@Override
	/*
	 * MEMBER_ID 121031 DS 1 FAMILYNO 22020102010247 MEMBERNAME ÕÅ²© PAPERID
	 * 220202810813422 SSN 677939 PERSONSTATE Õý³£ ASSIST_TYPE 00 ASORT 0 NUM 1
	 */
	public List<MemberDTO> finMembers(String sql, List<Object> param) {
		List<MemberDTO> resultlist = new ArrayList<MemberDTO>();

		/*
		 * List rs = memberBaseinfoDAO.findJDBCSql(sql, param); for (Iterator
		 * iterator = rs.iterator(); iterator.hasNext();) { MemberDTO e = new
		 * MemberDTO(); Object[] s = (Object[]) iterator.next();
		 * e.setMemberId("" + s[0]); e.setDs("" + s[1]); e.setFamilyno("" +
		 * s[2]); e.setMembername("" + s[3]); e.setPaperid("" + s[4]);
		 * e.setSsn("" + s[5]); e.setPersonstate("" + s[6]); e.setAssistType(""
		 * + s[7]); e.setAsort(new BigDecimal(s[8].toString()));
		 * resultlist.add(e); }
		 */

		return resultlist;
	}

	@Override
	public String finMembersCount(String sql, List<Object> param) {

		Long cnt = sysUserDAO.countJDBCsql(sql, param);
		if (null != cnt) {
			return cnt + "";
		} else {
			return "0";
		}

	}

	@SuppressWarnings("rawtypes")
	public List<UserDTO> finMemberstest(String sql, List<Object> param) {
		List<UserDTO> resultlist = new ArrayList<UserDTO>();

		List rs = sysUserDAO.findJDBCSql(sql, param);
		for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
			UserDTO e = new UserDTO();
			Object[] s = (Object[]) iterator.next();
			e.setFlag("" + s[7]);
			e.setUname("" + s[4]);
			resultlist.add(e);
		}

		return resultlist;
	}

	public List<OrganizationDTO> findOrganlist(String onno) {
		List<OrganizationDTO> resultlist = new ArrayList<OrganizationDTO>();
		/*
		 * String hql =
		 * "select o from Orglist o where o.organizationId = ? or o.parentorgid = ? order by  o.organizationId"
		 * ; Object[] param = null; param = new Object[2]; param[0] = onno;
		 * param[1] = onno; List<Orglist> rs = this.orglistDAO.find(hql, param);
		 * for (Orglist s : rs) { OrganizationDTO e = new OrganizationDTO();
		 * BeanUtils.copyProperties(s, e); resultlist.add(e); }
		 */
		return resultlist;

	}

	public MemberDTO findMemeber(MemberDTO memberDTO) {
		MemberDTO mdto = new MemberDTO();
		/*
		 * String hql =
		 * "select m from MemberBaseinfo m where m.memberId = ? and m.ds = ? ";
		 * Object[] param = null; param = new Object[2]; param[0] =
		 * memberDTO.getMemberId(); param[1] = memberDTO.getDs();
		 * List<MemberBaseinfo> rs = memberBaseinfoDAO.find(hql, param);
		 * BeanUtils.copyProperties(rs.get(0), mdto);
		 */
		return mdto;
	}

	public List<BillCsDTO> findBillCs(BillCsDTO billCsDTO) {
		List<BillCsDTO> resultlist = new ArrayList<BillCsDTO>();
		/*
		 * String hql =
		 * "select c from BillCs as c where c.barFamilyno = ? and c.stId = ? order by c.barSubject desc"
		 * ; Object[] param = null; param = new Object[2]; param[0] =
		 * billCsDTO.getBarFamilyno(); param[1] = billCsDTO.getStId();
		 * List<BillCs> rs = billCsDAO.find_top(hql, param, 0, 4); for (BillCs s
		 * : rs) { BillCsDTO e = new BillCsDTO(); BeanUtils.copyProperties(s,
		 * e); resultlist.add(e); }
		 */
		return resultlist;
	}

	public List<BillNcDTO> findBillNc(BillNcDTO billNcDTO) {
		List<BillNcDTO> resultlist = new ArrayList<BillNcDTO>();
		/*
		 * String hql =
		 * "select n from BillNc as n where n.barFamilyno = ? and n.stId = ? order by n.barSubject desc"
		 * ; Object[] param = null; param = new Object[2]; param[0] =
		 * billNcDTO.getBarFamilyno(); param[1] = billNcDTO.getStId();
		 * List<BillNc> rs = billNcDAO.find_top(hql, param, 0, 4); for (BillNc s
		 * : rs) { BillNcDTO e = new BillNcDTO(); BeanUtils.copyProperties(s,
		 * e); resultlist.add(e); }
		 */
		return resultlist;
	}

	public int updateMember(MemberDTO memberDTO) {
		int u = 0;
		/*
		 * String hql =
		 * "update MemberBaseinfo m set m.assistType= ? ,m.asort= ? where m.memberId= ? and m.ds= ? "
		 * ; Object[] param = null; param = new Object[4]; param[0] =
		 * memberDTO.getAssistType(); param[1] = memberDTO.getAsort(); param[2]
		 * = memberDTO.getMemberId(); param[3] = memberDTO.getDs(); u =
		 * memberBaseinfoDAO.update(hql,param);
		 */
		return u;
	}

	public List<BillCsDTO> findALLBillCsByFNO(BillCsDTO billCsDTO) {
		List<BillCsDTO> resultlist = new ArrayList<BillCsDTO>();
		/*
		 * String hql =
		 * "select c from BillCs as c where c.barFamilyno = ? and c.stId = ? order by c.barSubject desc"
		 * ; Object[] param = null; param = new Object[2]; param[0] =
		 * billCsDTO.getBarFamilyno(); param[1] = billCsDTO.getStId();
		 * List<BillCs> rs = billCsDAO.find(hql, param); for (BillCs s : rs) {
		 * BillCsDTO e = new BillCsDTO(); BeanUtils.copyProperties(s, e);
		 * resultlist.add(e); }
		 */
		return resultlist;
	}

	public List<BillNcDTO> findALLBillNcByFNO(BillNcDTO billNcDTO) {
		List<BillNcDTO> resultlist = new ArrayList<BillNcDTO>();
		/*
		 * String hql =
		 * "select n from BillNc as n where n.barFamilyno = ? and n.stId = ? order by n.barSubject desc"
		 * ; Object[] param = null; param = new Object[2]; param[0] =
		 * billNcDTO.getBarFamilyno(); param[1] = billNcDTO.getStId();
		 * List<BillNc> rs = billNcDAO.find(hql, param); for (BillNc s : rs) {
		 * BillNcDTO e = new BillNcDTO(); BeanUtils.copyProperties(s, e);
		 * resultlist.add(e); }
		 */
		return resultlist;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<MenuDTO> findMenusByUser(UserDTO userDTO) {
		List<MenuDTO> resultlist = new ArrayList<MenuDTO>();
		String sql = "";
		Object[] param = null;
		sql = "select * from sys_menus m where m.menu_id in (select t.MENU_ID "
				+ " from SYS_V_UMENU t  where t.USER_ID = ?  and t.PRIVICODE = 'READONLY' "
				+ " union all  select g.MENU_ID  from SYS_V_GMENU g "
				+ " where g.USER_ID = ?  and g.PRIVICODE = 'READONLY') "
				+ " order by m.menucode";
		param = new Object[2];
		param[0] = userDTO.getUserId();
		param[1] = userDTO.getUserId();

		List<SysMenus> rs = (List<SysMenus>) sysMeunsDAO.findJDBCSql(sql,
				param, SysMenus.class);

		for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
			MenuDTO e = new MenuDTO();
			BeanUtils.copyProperties(iterator.next(), e);
			resultlist.add(e);
		}

		return resultlist;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<VCkinsurance> queryCkInsurances(Pager pager, List<Object> param , String jwhere) {
		Map session = ActionContext.getContext().getSession();
		String hql = "select ci from VCkinsurance ci where 1=1 "+jwhere;
		String hqlc = "select count(*) as cnt from VCkinsurance ci where 1=1 "+jwhere;
		Long cnt = vCkinsuranceDAO.count(hqlc, param);
		pager.setRecords(cnt);
		List<VCkinsurance> rs = vCkinsuranceDAO.find(hql, param, pager.pager,
				pager.rows);
		LinkedHashMap<String, String>  title = XmlExcel.getXmlexcel().getTableMap("vckinsurance");
		session.put("hql", hql);
		session.put("param", param);
		session.put("title", title);
		return rs;
	}
	
	@Override
	public List<VCkinsurance> queryCkInsuranceById(InsuranceDTO insuranceDTO){
		Object[] param = new Object[1];
		BigDecimal id = new BigDecimal(insuranceDTO.getInId());
		param[0] = id;
		String hql = "select ci from VCkinsurance ci where ci.inId=?";
		List<VCkinsurance> rs = vCkinsuranceDAO.find(hql, param);
		return rs;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<VCkhouseproperty> queryCkHousepropertys(Pager pager, List<Object> param , String jwhere) {
		Map session = ActionContext.getContext().getSession();
		String hql = "select ch from VCkhouseproperty ch where 1=1 "+jwhere;
		String hqlc = "select count(*) as cnt from VCkhouseproperty ch where 1=1 "+jwhere;
		Long cnt = vCkhousepropertyDAO.count(hqlc, param);
		pager.setRecords(cnt);
		List<VCkhouseproperty> rs = vCkhousepropertyDAO.find(hql, param, pager.pager,
				pager.rows);
		LinkedHashMap<String, String>  title = XmlExcel.getXmlexcel().getTableMap("vckhouseproperty");
		session.put("hql", hql);
		session.put("param", param);
		session.put("param1", null);
		session.put("title", title);
		return rs;
	}
	
	@Override
	public List<VCkhouseproperty> queryCkhousepropertyById(HousepropertyDTO housepropertyDTO){
		Object[] param = new Object[1];
		BigDecimal id = new BigDecimal(housepropertyDTO.getHid());
		param[0] = id;
		String hql = "select ch from VCkhouseproperty ch where ch.fId=?";
		List<VCkhouseproperty> rs = vCkhousepropertyDAO.find(hql, param);
		return rs;
	}

}
