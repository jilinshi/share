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
import com.share.dto.FileDTO;
import com.share.dto.FunctionDTO;
import com.share.dto.MenuDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.PrivilegeDTO;
import com.share.dto.RoleDTO;
import com.share.dto.UserDTO;
import com.share.dto.UsergroupDTO;
import com.share.model.SysDistrict;
import com.share.model.SysFile;
import com.share.model.SysFprelation;
import com.share.model.SysFunction;
import com.share.model.SysGrrelation;
import com.share.model.SysMenus;
import com.share.model.SysMprelation;
import com.share.model.SysOrganization;
import com.share.model.SysPfrelation;
import com.share.model.SysPrivilege;
import com.share.model.SysRole;
import com.share.model.SysRprelation;
import com.share.model.SysUgrelation;
import com.share.model.SysUrrelation;
import com.share.model.SysUser;
import com.share.model.SysUsergroup;
import com.share.model.SysVGr;
import com.share.model.SysVMp;
import com.share.model.SysVRp;
import com.share.model.SysVUg;
import com.share.model.SysVUr;
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
	@Resource
	private BaseDAO<SysRole> sysRoleDAO;
	@Resource
	private BaseDAO<SysGrrelation> sysGrrelationDAO;
	@Resource
	private BaseDAO<SysUrrelation> sysUrrelationDAO;
	@Resource
	private BaseDAO<SysPrivilege> sysPrivilegeDAO;
	@Resource
	private BaseDAO<SysMenus> sysMenusDAO;
	@Resource
	private BaseDAO<SysFunction> sysFunctionDAO;
	@Resource
	private BaseDAO<SysFile> sysFileDAO;
	@Resource
	private BaseDAO<SysRprelation> sysRprelationDAO;
	@Resource
	private BaseDAO<SysMprelation> sysMprelationDAO;
	@Resource
	private BaseDAO<SysPfrelation> sysPfrelationDAO;
	@Resource
	private BaseDAO<SysFprelation> sysFprelationDAO;
	@Resource
	private BaseDAO<SysVUg> sysVUgDAO;
	@Resource
	private BaseDAO<SysVGr> sysVGrDAO;
	@Resource
	private BaseDAO<SysVUr> sysVUrDAO;
	@Resource
	private BaseDAO<SysVRp> sysVRpDAO;
	@Resource
	private BaseDAO<SysVMp> sysVMpDAO;
	
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
		SysOrganization so2 = new SysOrganization();
		so2.setOrgId(userDTO.getOrgId());
		o.setSysOrganization(so2);
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
		u.setOrgId(su.getSysOrganization().getOrgId());
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
	public List<UsergroupDTO> querySYSUserGroupAll() {
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
	public List<UserDTO> querySYSUsers(List<Object> param) {
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
	public void saveSYSUgrelation(List<SysUgrelation> list) {
		BigDecimal ugid = list.get(0).getUgId();
		String hql = " delete SysUgrelation su where su.ugId=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = ugid;
		sysUgrelationDAO.executeHql(hql, param);
		sysUgrelationDAO.saveBatch(list);
	}

	@Override
	public void saveSysUsergroup(String ugName) {
		SysUsergroup o = new SysUsergroup();
		o.setUgName(ugName);
		o.setUgPid(new BigDecimal("-1"));
		o.setFlag("1");
		o.setCtime(new Timestamp(new Date().getTime()));
		o.setUtime(new Timestamp(new Date().getTime()));
		sysUsergroupDAO.save(o);
	}

	@Override
	public void delSysUsergroup(String ugId) {
		String hql = " update SysUsergroup u set u.flag=?, u.utime=? where u.ugId=? ";
		Object[] param = null;
		param = new Object[3];
		param[0] = "0";
		param[1] = new Timestamp(new Date().getTime());
		param[2] = Long.valueOf(ugId);
		sysUsergroupDAO.update(hql, param);
	}
	
	@Override
	public List<RoleDTO> querySYSRoleAll(){
		List<RoleDTO> rolelist = new ArrayList<RoleDTO>();
		String hql = " select sr from SysRole sr where 1=1 and sr.flag='1' order by sr.utime desc ";
		List<SysRole> sr = sysRoleDAO.find(hql);
		for (SysRole s : sr) {
			RoleDTO r = new RoleDTO();
			r.setRoleId(s.getRoleId());
			r.setRolename(s.getRolename());
			r.setFlag(s.getFlag());
			r.setCtime(s.getCtime());
			r.setUtime(s.getUtime());
			rolelist.add(r);
		}
		return rolelist;
	}
	
	@Override
	public void saveSysRole(String rolename){
		SysRole o = new SysRole();
		o.setRolename(rolename);
		o.setFlag("1");
		o.setCtime(new Timestamp(new Date().getTime()));
		o.setUtime(new Timestamp(new Date().getTime()));
		sysRoleDAO.save(o);
	}
	
	@Override
	public void delSysRole(String roleId){
		String hql = " update SysRole sr set sr.flag=?, sr.utime=? where sr.roleId=? ";
		Object[] param = null;
		param = new Object[3];
		param[0] = "0";
		param[1] = new Timestamp(new Date().getTime());
		param[2] = Long.valueOf(roleId);
		sysRoleDAO.update(hql, param);
	}
	
	@Override
	public List<UserDTO> querySYSUserAll(){
		List<UserDTO> userlist = new ArrayList<UserDTO>();
		String hql = " select su from SysUser su where 1=1 and su.flag='1' order by su.userId ";
		List<SysUser> su = sysUserDAO.find(hql);
		for (SysUser s : su) {
			UserDTO u = new UserDTO();
			u.setUserId(s.getUserId());
			u.setUaccount(s.getUaccount());
			u.setUname(s.getUname());
			u.setUpwds(s.getUpwds());
			u.setFlag(s.getFlag());
			u.setIdno(s.getIdno());
			u.setMobilephone(s.getMobilephone());
			u.setUtime(s.getUtime());
			userlist.add(u);
		}
		return userlist;
		
	}
	
	@Override
	public void saveSYSGrrelation(List<SysGrrelation> grrs){
		BigDecimal roleid = grrs.get(0).getRoleId();
		String hql = " delete SysGrrelation su where su.roleId=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = roleid;
		sysGrrelationDAO.executeHql(hql, param);
		sysGrrelationDAO.saveBatch(grrs);
	}
	
	@Override
	public void saveSYSUrrelation(List<SysUrrelation> urrs){
		BigDecimal roleid = urrs.get(0).getRoleId();
		String hql = " delete SysUrrelation su where su.roleId=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = roleid;
		sysUrrelationDAO.executeHql(hql, param);
		sysUrrelationDAO.saveBatch(urrs);
	}
	
	@Override
	public List<PrivilegeDTO> querySYSPrivilegeAll(){
		List<PrivilegeDTO> privilegelist = new ArrayList<PrivilegeDTO>();
		String hql = " select sp from SysPrivilege sp where 1=1 and sp.flag='1' order by sp.privilegeId ";
		List<SysPrivilege> sp = sysPrivilegeDAO.find(hql);
		for (SysPrivilege s : sp) {
			PrivilegeDTO p= new PrivilegeDTO();
			p.setPrivilegeId(s.getPrivilegeId());
			p.setPriviname(s.getPriviname());
			p.setPrivicode(s.getPrivicode());
			p.setRemark(s.getRemark());
			p.setFlag(s.getFlag());
			p.setCtime(s.getCtime());
			privilegelist.add(p);
		}
		return privilegelist;
	}
	
	@Override
	public List<MenuDTO> querySYSMenusAll(){
		List<MenuDTO> menulist = new ArrayList<MenuDTO>();
		String hql = " select sp from SysMenus sp where 1=1 and sp.flag='1' order by sp.menucode ";
		List<SysMenus> sp = sysMenusDAO.find(hql);
		for (SysMenus s : sp) {
			MenuDTO p= new MenuDTO();
			p.setMenuId(s.getMenuId());
			p.setPmId(s.getPmId());
			p.setMenuname(s.getMenuname());
			p.setMenuurl(s.getMenuurl());
			p.setMenucode(s.getMenucode());
			p.setRemark(s.getRemark());
			p.setFlag(s.getFlag());
			p.setCtime(s.getCtime());
			p.setUtime(s.getUtime());
			p.setIco(s.getIco());
			menulist.add(p);
		}
		return menulist;
	}
	
	@Override
	public List<FunctionDTO> querySYSFunctionAll(){
		List<FunctionDTO> functionlist = new ArrayList<FunctionDTO>();
		String hql = " select sp from SysFunction sp where 1=1 and sp.flag='1' order by sp.functionId ";
		List<SysFunction> sp = sysFunctionDAO.find(hql);
		for (SysFunction s : sp) {
			FunctionDTO p= new FunctionDTO();
			p.setFunctionId(s.getFunctionId());
			p.setFuncname(s.getFuncname());
			p.setFuncdescr(s.getFuncdescr());
			p.setMatching(s.getMatching());
			p.setRemark(s.getRemark());
			p.setFlag(s.getFlag());
			p.setCtime(s.getCtime());
			functionlist.add(p);
		}
		return functionlist;
	}
	
	@Override
	public List<FileDTO> querySYSFileAll(){
		List<FileDTO> filelist = new ArrayList<FileDTO>();
		String hql = " select sp from SysFile sp where 1=1 and sp.flag='1' order by sp.fileId ";
		List<SysFile> sp = sysFileDAO.find(hql);
		for (SysFile s : sp) {
			FileDTO p= new FileDTO();
			p.setFileId(new BigDecimal(s.getFileId()));
			p.setFtype(s.getFtype());
			p.setFilename(s.getFilename());
			p.setRealname(s.getRealname());
			p.setRealpath(s.getRealpath());
			p.setRemark(s.getRemark());
			p.setFlag(s.getFlag());
			filelist.add(p);
		}
		return filelist;
		
	}
	
	@Override
	public void saveSysPrivilege(PrivilegeDTO pdto){
		SysPrivilege o = new SysPrivilege();
		o.setPriviname(pdto.getPriviname());
		o.setPrivicode(pdto.getPrivicode());
		o.setRemark(pdto.getPriviname());
		o.setFlag("1");
		o.setCtime(new Timestamp(new Date().getTime()));
		sysPrivilegeDAO.save(o);
	}
	
	@Override
	public void delSysPrivilege(String privilegeId){
		String hql = " update SysPrivilege sp set sp.flag=? where sp.privilegeId=? ";
		Object[] param = null;
		param = new Object[2];
		param[0] = "0";
		param[1] = Long.valueOf(privilegeId);
		sysRoleDAO.update(hql, param);
	}
	
	@Override
	public void saveSYSRprelation(List<SysRprelation> rrs){
		BigDecimal priid = rrs.get(0).getPrivilegeId();
		String hql = " delete SysRprelation sr where sr.privilegeId=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = priid;
		sysRprelationDAO.executeHql(hql, param);
		sysRprelationDAO.saveBatch(rrs);
	}
	
	@Override
	public void saveSYSMprelation(List<SysMprelation> mrs){
		BigDecimal priid = mrs.get(0).getPrivilegeId();
		String hql = " delete SysMprelation mr where mr.privilegeId=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = priid;
		sysMprelationDAO.executeHql(hql, param);
		sysMprelationDAO.saveBatch(mrs);
	}
	
	@Override
	public void saveSYSPfrelation(List<SysPfrelation> prs){
		BigDecimal priid = prs.get(0).getPrivilegeId();
		String hql = " delete SysPfrelation pr where pr.privilegeId=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = priid;
		sysPfrelationDAO.executeHql(hql, param);
		sysPfrelationDAO.saveBatch(prs);
	}
	
	@Override
	public void saveSYSFprelation(List<SysFprelation> frs){
		BigDecimal priid = frs.get(0).getPrivilegeId();
		String hql = " delete SysFprelation fr where fr.privilegeId=?";
		Object[] param = null;
		param = new Object[1];
		param[0] = priid;
		sysFprelationDAO.executeHql(hql, param);
		sysFprelationDAO.saveBatch(frs);
	}
	
	@Override
	public List<SysVUg> querySYSUgUsers(BigDecimal ugId){
		Object[] param = null;
		param = new Object[1];
		param[0] = ugId;
		String hql = " select s from SysVUg s where 1=1 and s.ugId=?";
		List<SysVUg> suus = sysVUgDAO.find(hql, param);
		return suus;
	}
	
	@Override
	public List<SysVGr> querySYSVGr(BigDecimal roleId){
		Object[] param = null;
		param = new Object[1];
		param[0] = roleId;
		String hql = " select s from SysVGr s where 1=1 and s.roleId=?";
		List<SysVGr> sgrs = sysVGrDAO.find(hql, param);
		return sgrs;
	}
	
	@Override
	public List<SysVUr> querySYSVUr(BigDecimal roleId){
		Object[] param = null;
		param = new Object[1];
		param[0] = roleId;
		String hql = " select s from SysVUr s where 1=1 and s.roleId=?";
		List<SysVUr> sgrs = sysVUrDAO.find(hql, param);
		return sgrs;
	}
	
	@Override
	public List<SysVRp> querySYSVRp(BigDecimal privilegeId){
		Object[] param = null;
		param = new Object[1];
		param[0] = privilegeId;
		String hql = " select s from SysVRp s where 1=1 and s.privilegeId=?";
		List<SysVRp> srps = sysVRpDAO.find(hql, param);
		return srps;
		
	}
	
	@Override
	public List<SysVMp> querySYSVMp(BigDecimal privilegeId){
		Object[] param = null;
		param = new Object[1];
		param[0] = privilegeId;
		String hql = " select s from SysVMp s where 1=1 and s.privilegeId=?";
		List<SysVMp> smps = sysVMpDAO.find(hql, param);
		return smps;
	}
	
	@Override
	public List<SysMenus> queryMenuCodeByPmid(String menuId){
		Object[] param = null;
		param = new Object[1];
		param[0] = new BigDecimal(menuId);
		String hql = " select s from SysMenus s where 1=1 and s.pmId=? order by s.menucode desc";
		List<SysMenus> sm= sysMenusDAO.find(hql,param);
		return sm;
	}
	
	@Override
	public List<SysMenus> queryMenuCodeById(String menuId){
		Object[] param = null;
		param = new Object[1];
		param[0] = Long.valueOf(menuId);
		String hql = " select s from SysMenus s where 1=1 and s.flag='1' and s.menuId=? order by s.menucode desc";
		List<SysMenus> sm= sysMenusDAO.find(hql,param);
		return sm;
	}
	
	@Override
	public void saveMenu(MenuDTO menuDTO){
		SysMenus o = new SysMenus();
		o.setMenuname(menuDTO.getMenuname());
		o.setMenucode(menuDTO.getMenucode());
		o.setMenuurl(menuDTO.getMenuurl());
		o.setIco(menuDTO.getIco());
		o.setPmId(menuDTO.getPmId());
		o.setRemark(menuDTO.getRemark());
		if(menuDTO.getMenuId()==0){
			String hql = " select s from SysMenus s where 1=1 order by s.menuId desc";
			List<SysMenus> sm= sysMenusDAO.find(hql);
			Long id = sm.get(0).getMenuId();
			o.setMenuId(Long.valueOf(id+1));
			o.setFlag("1");
			o.setCtime(new Timestamp(new Date().getTime()));
			o.setUtime(o.getCtime());
			sysMenusDAO.save(o);
		}else{
			o.setMenuId(menuDTO.getMenuId());
			o.setUtime(new Timestamp(new Date().getTime()));
			o.setCtime(menuDTO.getCtime());
			o.setFlag(menuDTO.getFlag());
			sysMenusDAO.update(o);
		}
	}
	
	@Override
	public int delMenu(Long menuId){
		String hql = " update SysMenus s set s.flag=? , s.utime=? where 1=1 and s.menuId=?";
		Object[] param = null;
		param = new Object[3];
		param[0] = "0";
		param[1] = new Timestamp(new Date().getTime());
		param[2] = menuId;
		int u = sysMenusDAO.update(hql, param);
		return u;
	}
	
}
