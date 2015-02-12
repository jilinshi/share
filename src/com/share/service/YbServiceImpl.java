package com.share.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.share.dao.BaseDAO;
import com.share.dto.BillCsDTO;
import com.share.dto.BillNcDTO;
import com.share.dto.MemberDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.UserDTO;
import com.share.model.BillCs;
import com.share.model.BillNc;
import com.share.model.MemberBaseinfo;
import com.share.model.Orglist;
import com.share.model.SysTEmployee;
import com.share.model.SysTOrganization;

@Service("ybService")
public class YbServiceImpl implements YbService {

	@Resource
	private BaseDAO<SysTEmployee> sysTEmployeeDAO;
	@Resource
	private BaseDAO<MemberBaseinfo> memberBaseinfoDAO;
	@Resource
	private BaseDAO<SysTOrganization> SysTOrganizationDAO;
	@Resource
	private BaseDAO<Orglist> orglistDAO;
	@Resource
	private BaseDAO<BillCs> billCsDAO;
	@Resource
	private BaseDAO<BillNc> billNcDAO;

	public UserDTO findUser(UserDTO userDTO) {
		String hql = "";
		Object[] param = null;
		if ("95588".equals(userDTO.getToken())) {
			hql = "select emp from SysTEmployee emp where emp.account=? and emp.password=?";
			param = new Object[2];
			param[0] = userDTO.getAccount();
			param[1] = userDTO.getPassword();
		} else {
			hql = "select emp from SysTEmployee emp where emp.account=? and emp.password=? and emp.idcard=?";
			param = new Object[3];
			param[0] = userDTO.getAccount();
			param[1] = userDTO.getPassword();
			param[2] = userDTO.getToken();
		}

		List<SysTEmployee> rs = sysTEmployeeDAO.find(hql, param);
		if (null != rs && rs.size() > 0) {
			SysTEmployee sysTEmployee = rs.get(0);
			BeanUtils.copyProperties(sysTEmployee, userDTO);
		} else {
			userDTO = null;
		}
		return userDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	/*
	 * MEMBER_ID 121031 DS 1 FAMILYNO 22020102010247 MEMBERNAME ÕÅ²© PAPERID
	 * 220202810813422 SSN 677939 PERSONSTATE Õý³£ ASSIST_TYPE 00 ASORT 0 NUM 1
	 */
	public List<MemberDTO> finMembers(String sql,List<Object> param) {
		List<MemberDTO> resultlist = new ArrayList<MemberDTO>();
		
		List rs = memberBaseinfoDAO.findJDBCSql(sql, param);
		for (Iterator iterator = rs.iterator(); iterator.hasNext();) {
			MemberDTO e = new MemberDTO();
			Object[] s = (Object[]) iterator.next();
			e.setMemberId("" + s[0]);
			e.setDs("" + s[1]);
			e.setFamilyno("" + s[2]);
			e.setMembername("" + s[3]);
			e.setPaperid("" + s[4]);
			e.setSsn("" + s[5]);
			e.setPersonstate("" + s[6]);
			e.setAssistType("" + s[7]);
			e.setAsort(new BigDecimal(s[8].toString()));
			resultlist.add(e);
		}

		return resultlist;
	}

	@Override
	public String finMembersCount(String sql,List<Object> param) {
		
		Long cnt = memberBaseinfoDAO.countJDBCsql(sql, param);
		if (null != cnt) {
			return cnt + "";
		} else {
			return "0";
		}
	}

	public List<OrganizationDTO> findOrganlist(String onno) {
		List<OrganizationDTO> resultlist = new ArrayList<OrganizationDTO>();
		String hql = "select o from Orglist o where o.organizationId = ? or o.parentorgid = ? order by  o.organizationId";
		Object[] param = null;
		param = new Object[2];
		param[0] = onno;
		param[1] = onno;
		List<Orglist> rs = this.orglistDAO.find(hql, param);
		for (Orglist s : rs) {
			OrganizationDTO e = new OrganizationDTO();
			BeanUtils.copyProperties(s, e);
			resultlist.add(e);
		}
		return resultlist;

	}
	
	public MemberDTO findMemeber(MemberDTO memberDTO){
		MemberDTO mdto = new MemberDTO();
		String hql = "select m from MemberBaseinfo m where m.memberId = ? and m.ds = ? ";
		Object[] param = null;
		param = new Object[2];
		param[0] = memberDTO.getMemberId();
		param[1] = memberDTO.getDs();
		List<MemberBaseinfo> rs = memberBaseinfoDAO.find(hql, param);
		BeanUtils.copyProperties(rs.get(0), mdto);
		return mdto;
	}
	
	public List<BillCsDTO> findBillCs(BillCsDTO billCsDTO){
		List<BillCsDTO> resultlist = new ArrayList<BillCsDTO>();
		String hql = "select c from BillCs as c where c.barFamilyno = ? and c.stId = ? order by c.barSubject desc";
		Object[] param = null;
		param = new Object[2];
		param[0] = billCsDTO.getBarFamilyno();
		param[1] = billCsDTO.getStId();
		List<BillCs> rs = billCsDAO.find_top(hql, param, 0, 4);
		for (BillCs s : rs) {
			BillCsDTO e = new BillCsDTO();
			BeanUtils.copyProperties(s, e);
			resultlist.add(e);
		}
		return resultlist;
	}
	
	public List<BillNcDTO> findBillNc(BillNcDTO billNcDTO){
		List<BillNcDTO> resultlist = new ArrayList<BillNcDTO>();
		String hql = "select n from BillNc as n where n.barFamilyno = ? and n.stId = ? order by n.barSubject desc";
		Object[] param = null;
		param = new Object[2];
		param[0] = billNcDTO.getBarFamilyno();
		param[1] = billNcDTO.getStId();
		List<BillNc> rs = billNcDAO.find_top(hql, param, 0, 4);
		for (BillNc s : rs) {
			BillNcDTO e = new BillNcDTO();
			BeanUtils.copyProperties(s, e);
			resultlist.add(e);
		}
		return resultlist;
	}
	
	public int updateMember(MemberDTO memberDTO){
		int u=0;
		String hql = "update MemberBaseinfo m set m.assistType= ? ,m.asort= ? where m.memberId= ? and m.ds= ? ";
		Object[] param = null;
		param = new Object[4];
		param[0] = memberDTO.getAssistType();
		param[1] = memberDTO.getAsort();
		param[2] = memberDTO.getMemberId();
		param[3] = memberDTO.getDs();
		u = memberBaseinfoDAO.update(hql,param);
		return u;
	}
	
	public List<BillCsDTO> findALLBillCsByFNO(BillCsDTO billCsDTO){
		List<BillCsDTO> resultlist = new ArrayList<BillCsDTO>();
		String hql = "select c from BillCs as c where c.barFamilyno = ? and c.stId = ? order by c.barSubject desc";
		Object[] param = null;
		param = new Object[2];
		param[0] = billCsDTO.getBarFamilyno();
		param[1] = billCsDTO.getStId();
		List<BillCs> rs = billCsDAO.find(hql, param);
		for (BillCs s : rs) {
			BillCsDTO e = new BillCsDTO();
			BeanUtils.copyProperties(s, e);
			resultlist.add(e);
		}
		return resultlist;
	}
	
	public List<BillNcDTO> findALLBillNcByFNO(BillNcDTO billNcDTO){
		List<BillNcDTO> resultlist = new ArrayList<BillNcDTO>();
		String hql = "select n from BillNc as n where n.barFamilyno = ? and n.stId = ? order by n.barSubject desc";
		Object[] param = null;
		param = new Object[2];
		param[0] = billNcDTO.getBarFamilyno();
		param[1] = billNcDTO.getStId();
		List<BillNc> rs = billNcDAO.find(hql, param);
		for (BillNc s : rs) {
			BillNcDTO e = new BillNcDTO();
			BeanUtils.copyProperties(s, e);
			resultlist.add(e);
		}
		return resultlist;
	}
}
