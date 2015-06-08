package com.share.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.share.dao.BaseDAO;
import com.share.dto.DistrictsDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.UserDTO;
import com.share.dto.UsergroupDTO;
import com.share.model.SysDistrict;
import com.share.model.SysOrganization;
import com.share.model.SysUgrelation;
import com.share.model.SysUser;
import com.share.model.SysUsergroup;
import com.share.util.Pager;
import com.share.util.PrimaryKey;
import com.share.util.StringFormat;

@Service("SysService")
public class SysServiceImpl implements SysService {

	@Resource
	private BaseDAO<SysDistrict> sysDistrictDAO;
	@Resource
	private BaseDAO<SysOrganization> sysOrganizationDAO;
	@Resource
	private BaseDAO<SysUser> sysUserDAO;
	@Resource
	private BaseDAO<SysUsergroup> sysUsergroupDAO;
	@Resource
	private BaseDAO<SysUgrelation> sysUgrelationDAO;

	@Override
	public List<DistrictsDTO> querySYSDistrict(String hql, Object[] param) {
		List<DistrictsDTO> list = new ArrayList<DistrictsDTO>();
		List<SysDistrict> rs = sysDistrictDAO.find(hql, param);
		for (SysDistrict s : rs) {
			DistrictsDTO e = new DistrictsDTO();
			e.setCol1(s.getCol1());
			e.setCol2(s.getCol2());
			e.setCol3(s.getCol3());
			e.setDistrictsCode(s.getDistrictsCode());
			e.setDistrictsId(s.getDistrictsId());
			e.setDistrictsNmae(s.getDistrictsNmae());
			e.setFlag(s.getFlag());
			e.setFullname(s.getFullname());
			e.setOrd(s.getOrd());
			e.setParentId(s.getParentId());
			e.setText(s.getDistrictsNmae());
			list.add(e);
		}
		return list;
	}

	@Override
	public void saveSYSDistrict(DistrictsDTO districtsDTO) {
		// 查询
		String hql = " select sd from SysDistrict sd where 1=1 and sd.flag=? and sd.districtsId=? ";
		Object[] param = null;
		param = new Object[2];
		param[0] = "1";
		param[1] = districtsDTO.getDistrictsId();
		SysDistrict sd = sysDistrictDAO.find(hql, param).get(0);
		//
		String hql_n = " select sd from SysDistrict sd where 1=1 and sd.flag=? and sd.parentId=? order by sd.districtsId desc ";
		Object[] param_n = null;
		param_n = new Object[2];
		param_n[0] = "1";
		param_n[1] = districtsDTO.getDistrictsId();
		List<SysDistrict> sds = sysDistrictDAO.find(hql_n, param_n);
		if (sds.size() > 0) {
			SysDistrict s = sds.get(0);
			// 保存机构表
			SysDistrict o = new SysDistrict();
			long id = Long.valueOf(s.getDistrictsId());
			o.setDistrictsId((id + 1) + "");
			o.setDistrictsNmae(districtsDTO.getDistrictsNmae());
			o.setParentId(sd.getDistrictsId());
			o.setDistrictsCode(StringFormat.getformatting(o.getDistrictsId()));
			o.setFullname(sd.getFullname() + districtsDTO.getDistrictsNmae());
			o.setFlag("1");
			sysDistrictDAO.save(o);
		} else {
			SysDistrict o = new SysDistrict();
			o.setDistrictsId(sd.getDistrictsId() + "01");
			o.setDistrictsNmae(districtsDTO.getDistrictsNmae());
			o.setParentId(sd.getDistrictsId());
			o.setDistrictsCode(StringFormat.getformatting(o.getDistrictsId()));
			o.setFullname(sd.getFullname() + districtsDTO.getDistrictsNmae());
			o.setFlag("1");
			sysDistrictDAO.save(o);
		}
	}

	@Override
	public List<DistrictsDTO> querySYSDistrict(DistrictsDTO districtsDTO) {
		List<DistrictsDTO> ds = new ArrayList<DistrictsDTO>();
		String hql = " select sd from SysDistrict sd where 1=1 and sd.flag=? and sd.districtsNmae=? ";
		Object[] param = null;
		param = new Object[2];
		param[0] = "1";
		param[1] = districtsDTO.getDistrictsNmae();
		List<SysDistrict> sds = sysDistrictDAO.find(hql, param);
		for (SysDistrict e : sds) {
			DistrictsDTO m = new DistrictsDTO();
			m.setDistrictsId(e.getDistrictsId());
			m.setDistrictsCode(e.getDistrictsCode());
			m.setDistrictsNmae(e.getDistrictsNmae());
			m.setFlag(e.getFlag());
			m.setFullname(e.getFullname());
			m.setParentId(e.getParentId());
			ds.add(m);
		}
		return ds;
	}

	@Override
	public int updateSYSDistrict(String id) {
		String hql = " select sd from SysDistrict sd where 1=1 and sd.flag=? and sd.parentId=? ";
		Object[] param = null;
		param = new Object[2];
		param[0] = "1";
		param[1] = id;
		List<SysDistrict> sds = sysDistrictDAO.find(hql, param);
		String hql_u = "";
		Object[] param_u = null;
		if (sds.size() > 0) {
			hql_u = " update SysDistrict sd set sd.flag=? where sd.districtsId=? or sd.parentId=? ";
			param_u = new Object[3];
			param_u[0] = "0";
			param_u[1] = id;
			param_u[2] = id;
		} else {
			hql_u = " update SysDistrict sd set sd.flag=? where sd.districtsId=? ";
			param_u = new Object[2];
			param_u[0] = "0";
			param_u[1] = id;
		}
		int u = sysDistrictDAO.update(hql_u, param_u);

		return u;
	}

	@Override
	public List<OrganizationDTO> querySYSOrgs(String hql, Object[] param) {
		List<OrganizationDTO> list = new ArrayList<OrganizationDTO>();
		List<SysOrganization> rs = sysOrganizationDAO.find(hql, param);
		for (SysOrganization s : rs) {
			OrganizationDTO e = new OrganizationDTO();
			e.setOrgId(s.getOrgId());
			e.setOrgCode(s.getOrgCode());
			e.setOrgName(s.getOrgName());
			e.setDistrictsId(s.getSysDistrict().getDistrictsId());
			e.setFlag(s.getFlag());
			e.setCtime(s.getCtime());
			e.setUtime(s.getUtime());
			e.setParentId(s.getParentId());
			list.add(e);
		}
		return list;
	}

	@Override
	public void saveSYSOrg(OrganizationDTO organizationDTO) {
		// 查询
		String hql = " select so from SysOrganization so where 1=1 and so.flag=? and so.parentId=? order by so.orgId desc";
		Object[] param = null;
		param = new Object[2];
		param[0] = "1";
		param[1] = organizationDTO.getOrgId() + "";
		List<SysOrganization> sos = sysOrganizationDAO.find(hql, param);
		String preid = "";
		String orgid = "";
		String maxvalue = "";
		String key = "";
		SysOrganization o = new SysOrganization();
		SysDistrict s = new SysDistrict();
		if (sos.size() > 0) {
			SysOrganization so = sysOrganizationDAO.find(hql, param).get(0);
			// 保存机构表
			preid = so.getParentId().toString();
			orgid = so.getOrgId() + "";
			maxvalue = orgid.substring(preid.length());
			key = PrimaryKey.nextKey(preid, maxvalue);

			o.setParentId(so.getParentId());
		} else {
			orgid = organizationDTO.getOrgId() + "";
			int prikey_len = orgid.length();
			if (prikey_len == 2) {
				maxvalue = "00";
			} else if (prikey_len == 4) {
				maxvalue = "00";
			} else if (prikey_len == 6) {
				maxvalue = "000";
			} else if (prikey_len == 8 || prikey_len == 9) {
				maxvalue = "000";
			}
			key = PrimaryKey.nextKey(orgid, maxvalue);
			o.setParentId(orgid);
		}
		s.setDistrictsId(organizationDTO.getParentId());
		o.setOrgCode(StringFormat.getformatting12(key));
		o.setOrgName(organizationDTO.getOrgName());
		o.setSysDistrict(s);
		o.setOrgId(Long.valueOf(key));
		o.setFlag("1");
		sysOrganizationDAO.save(o);
	}

	@Override
	public List<UserDTO> querySYSUsers(Pager pager, List<Object> param) {
		List<UserDTO> us = new ArrayList<UserDTO>();
		String hql = " select su from SysUser su where 1=1 and su.flag=? and su.sysOrganization=? order by su.utime desc";
		String hqlc = " select count(*) as cnt from SysUser su where 1=1 and su.flag=? and su.sysOrganization=? ";
		Long cnt = sysUserDAO.count(hqlc, param);
		pager.setRecords(cnt);
		List<SysUser> sus = sysUserDAO
				.find(hql, param, pager.pager, pager.rows);
		for (SysUser s : sus) {
			UserDTO u = new UserDTO();
			u.setUserId(s.getUserId());
			u.setUaccount(s.getUaccount());
			u.setUname(s.getUname());
			u.setUpwds(s.getUpwds());
			u.setFlag(s.getFlag());
			u.setIdno(s.getIdno());
			u.setMobilephone(s.getMobilephone());
			u.setUtime(s.getUtime());
			us.add(u);
		}
		return us;
	}

	@Override
	public void saveSYSUser(UserDTO userDTO) {
		SysUser o = this.sysUserDAO.get(SysUser.class, userDTO.getUserId());
		if (null == o) {
			o = new SysUser();
		}
		o.setUname(userDTO.getUname());
		o.setUaccount(userDTO.getUaccount());
		o.setUpwds(userDTO.getUpwds());
		o.setMobilephone(userDTO.getMobilephone());
		o.setIdno(userDTO.getIdno());
		if (userDTO.getUserId() == 0) {
			o.setCtime(new Timestamp(new Date().getTime()));
			o.setUtime(new Timestamp(new Date().getTime()));
			o.setFlag("1");
			SysOrganization so = new SysOrganization();
			so.setOrgId(Long.valueOf(userDTO.getOrgId()));
			o.setSysOrganization(so);
			sysUserDAO.save(o);
		} else {
			o.setUtime(new Timestamp(new Date().getTime()));
			sysUserDAO.update(o);
		}

	}

	public SysOrganization queryOrgById(Long oid) {
		String hql = "  select so from SysOrganization so where 1=1 and so.orgId=? ";
		Object[] param = null;
		param = new Object[1];
		param[0] = oid;
		SysOrganization so = sysOrganizationDAO.find(hql, param).get(0);
		return so;
	}

	@Override
	public UserDTO querySYSUserById(Long userId) {
		UserDTO u = new UserDTO();
		String hql = " select su from SysUser su where 1=1 and su.userId=? ";
		Object[] param = null;
		param = new Object[1];
		param[0] = userId;
		SysUser su = sysUserDAO.find(hql, param).get(0);
		u.setUserId(su.getUserId());
		u.setOrgId(su.getSysOrganization().getOrgId() + "");
		u.setUname(su.getUname());
		u.setUaccount(su.getUaccount());
		u.setUpwds(su.getUpwds());
		u.setMobilephone(su.getMobilephone());
		u.setIdno(su.getIdno());
		u.setCtime(su.getCtime());
		u.setUtime(su.getUtime());
		return u;
	}

	@Override
	public void updateSYSUserById(Long userId) {
		String hql = " update SysUser u set u.flag=?, u.utime=? where u.userId=? ";
		Object[] param = null;
		param = new Object[3];
		param[0] = "0";
		param[1] = new Timestamp(new Date().getTime());
		param[2] = userId;
		sysUserDAO.update(hql, param);
	}
	
	@Override
	public List<UsergroupDTO> querySYSUserGroupAll(){
		List<UsergroupDTO> userlist = new ArrayList<UsergroupDTO>();
		String hql = " select sug from SysUsergroup sug where 1=1 and sug.flag='1' order by sug.utime desc ";
		List<SysUsergroup> sugs = sysUsergroupDAO.find(hql);
		for (SysUsergroup s : sugs) {
			UsergroupDTO u = new UsergroupDTO();
			u.setUgId(s.getUgId());
			u.setUgPid(s.getUgPid());
			u.setUgName(s.getUgName());
			u.setFlag(s.getFlag());
			u.setCtime(s.getCtime());
			u.setUtime(s.getUtime());
			userlist.add(u);
		}
		return userlist;
	}
	
	@Override
	public List<UserDTO> querySYSUsers(List<Object> param){
		List<UserDTO> us = new ArrayList<UserDTO>();
		String hql = " select su from SysUser su where 1=1 and su.flag=? and su.sysOrganization=? order by su.utime desc";
		List<SysUser> sus = sysUserDAO.find(hql, param);
		for (SysUser s : sus) {
			UserDTO u = new UserDTO();
			u.setUserId(s.getUserId());
			u.setUaccount(s.getUaccount());
			u.setUname(s.getUname());
			u.setUpwds(s.getUpwds());
			u.setFlag(s.getFlag());
			u.setIdno(s.getIdno());
			u.setMobilephone(s.getMobilephone());
			u.setUtime(s.getUtime());
			us.add(u);
		}
		return us;
	}
	
	@Override
	public void saveSYSUgrelation(List<SysUgrelation> list){
		BigDecimal ugid = list.get(0).getUgId();
		String hql=" delete SysUgrelation su where su.ugId=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = ugid;
		sysUgrelationDAO.executeHql(hql, param);
		sysUgrelationDAO.saveBatch(list);
	}
	
	@Override
	public void saveSysUsergroup(String ugName){
		SysUsergroup o = new SysUsergroup();
		o.setUgName(ugName);
		o.setUgPid(new BigDecimal("-1"));
		o.setFlag("1");
		o.setCtime(new Timestamp(new Date().getTime()));
		o.setUtime(new Timestamp(new Date().getTime()));
		sysUsergroupDAO.save(o);
	}
	
	@Override
	public void delSysUsergroup(String ugId){
		String hql = " update SysUsergroup u set u.flag=?, u.utime=? where u.ugId=? ";
		Object[] param = null;
		param = new Object[3];
		param[0] = "0";
		param[1] = new Timestamp(new Date().getTime());
		param[2] = Long.valueOf(ugId);
		sysUsergroupDAO.update(hql, param);
	}
}
