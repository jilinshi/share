package com.share.action.sys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.DistrictsDTO;
import com.share.dto.FileDTO;
import com.share.dto.FunctionDTO;
import com.share.dto.MenuDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.PrivilegeDTO;
import com.share.dto.RoleDTO;
import com.share.dto.ShortcutDTO;
import com.share.dto.UserDTO;
import com.share.dto.UsergroupDTO;
import com.share.model.SysGrrelation;
import com.share.model.SysMenus;
import com.share.model.SysMprelation;
import com.share.model.SysOrganization;
import com.share.model.SysRprelation;
import com.share.model.SysUgrelation;
import com.share.model.SysUrrelation;
import com.share.model.SysVGr;
import com.share.model.SysVMp;
import com.share.model.SysVRp;
import com.share.model.SysVUg;
import com.share.model.SysVUr;
import com.share.service.SysService;
import com.share.util.Pager;

@Controller
public class SysAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(SysAction.class);

	@Resource
	private SysService sysService;

	@SuppressWarnings("rawtypes")
	private Map map;// 返回的json
	private String parentid;
	private DistrictsDTO districtsDTO;
	private String districtsId;
	private OrganizationDTO organizationDTO;
	private UserDTO userDTO;
	private String orgId;
	private MenuDTO menuDTO;
	 /** 当前页面 */ 
	private String page;  
	 /** 每页的记录数 */ 
	private String rows;
	private String userId;
	private String ogId;
	private String userIds;
	private String ugName;
	private String ugId;
	//
	private String roleName;
	private String roleId;
	
	private String usergroudIds;
	private String roleIds;
	
	private String priviname;
	private String privicode;
	private String privilegeId;
	
	private String privilegeIds;
	private String menuIds;
	private String functionIds;
	private String fileIds;
	
	private String menuId;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String findDistrictList() {
		String hql = " select sd from SysDistrict sd where 1=1 and sd.flag=? order by sd.districtsId";
		Object[] param = null;
		param = new Object[1];
		param[0] = "1";
		List<DistrictsDTO> list = sysService.querySYSDistrict(hql, param);
		Map jsonMap = new HashMap();
		jsonMap.put("districts", list);
		map = jsonMap;
		return SUCCESS;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String findDistrictListALL() {
		Map jsonMap = new HashMap();
		List<DistrictsDTO> orgs = findChildDistrictList(parentid);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray
				.fromObject(orgs);
		// System.out.println(jsonArray);
		jsonMap.put("orgs", orgs);
		map = jsonMap;
		return SUCCESS;
	}

	private List<DistrictsDTO> findChildDistrictList(String parentid) {
		List<DistrictsDTO> templist = new ArrayList<DistrictsDTO>();
		String hql = " select sd from SysDistrict sd where 1=1 and sd.parentId=? and sd.flag=? order by sd.districtsId";
		Object[] param = null;
		param = new Object[2];
		param[0] = parentid;
		param[1] = "1";
		List<DistrictsDTO> list = sysService.querySYSDistrict(hql, param);
		for (DistrictsDTO e : list) {
			templist.add(e);
			templist.addAll(findChildDistrictList(e.getDistrictsId()));
		}

		return templist;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String saveDistricts() {
		Map jsonMap = new HashMap();
		try {
			sysService.saveSYSDistrict(districtsDTO);
			jsonMap.put("msg", "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String findDistricts() {
		List<DistrictsDTO> ds = sysService.querySYSDistrict(districtsDTO);
		Map jsonMap = new HashMap();
		if(ds.size()>0){
			jsonMap.put("ds", ds.get(0));
			jsonMap.put("r", "1");
		}else{
			jsonMap.put("r", "0");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String removeDistricts() {
		int u = sysService.updateSYSDistrict(districtsId);
		Map jsonMap = new HashMap();
		if(u>0){
			jsonMap.put("msg", "删除成功！");
		}else{
			jsonMap.put("msg", "删除失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	/*	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String findOrgList(){
		String hql = " select so from SysOrganization so where 1=1 and so.flag=? and so.orgId=? order by so.orgId";
		Object[] param = null;
		param = new Object[2];
		param[0] = "1";
		param[1] = Long.valueOf(districtsId);
		List<OrganizationDTO> list = sysService.querySYSOrgs(hql, param);
		Map jsonMap = new HashMap();
		jsonMap.put("orgs", list);
		map = jsonMap;
		return SUCCESS;
	}*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String findOrgList() {
		Map jsonMap = new HashMap();
		List<OrganizationDTO> orgs = findChildOrgList(parentid);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray
				.fromObject(orgs);
		 System.out.println(jsonArray);
		jsonMap.put("orgs", orgs);
		map = jsonMap;
		return SUCCESS;
	}

	private List<OrganizationDTO> findChildOrgList(String parentid) {
		List<OrganizationDTO> templist = new ArrayList<OrganizationDTO>();
		String hql = " select so from SysOrganization so where 1=1 and so.parentId=? and so.flag=? order by so.orgId";
		Object[] param = null;
		param = new Object[2];
		param[0] = new BigDecimal(parentid);
		param[1] = "1";
		List<OrganizationDTO> list = sysService.querySYSOrgs(hql, param);
		for (OrganizationDTO e : list) {
			templist.add(e);
			templist.addAll(findChildOrgList(e.getDistrictsId()));
		}

		return templist;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findDistrictList1(){
		String hql = " select sd from SysDistrict sd where 1=1 and sd.flag=? order by sd.districtsId ";
		Object[] param = null;
		param = new Object[1];
		param[0] = "1";
		List<DistrictsDTO> list = sysService.querySYSDistrict(hql, param);
		List<ShortcutDTO> sl = new ArrayList<ShortcutDTO>();
		for(DistrictsDTO e:list){
			ShortcutDTO s = new ShortcutDTO();
			s.setId(e.getDistrictsId());
			s.setpId(e.getParentId());
			if("-1".equals(e.getParentId())){
				s.setOpen(true);
			}else{
				s.setOpen(false);
			}
			s.setName(e.getDistrictsNmae());
			sl.add(s);
		}
		Map jsonMap = new HashMap();
		jsonMap.put("districts", sl);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String findOrgList1() {
		Map jsonMap = new HashMap();
		List<OrganizationDTO> orgs = findChildOrgList1(parentid);
		List<ShortcutDTO> sl1 = new ArrayList<ShortcutDTO>();
		for(OrganizationDTO e:orgs){
			ShortcutDTO s = new ShortcutDTO();
			s.setId(e.getOrgId()+"");
			s.setpId(e.getParentId());
			s.setGenId(e.getParentId());
			if(parentid.equals(e.getDistrictsId())){
				s.setOpen(true);
			}else{
				s.setOpen(true);
			}
			s.setName(e.getOrgName());
			sl1.add(s);
		}
		jsonMap.put("orgs", sl1);
		map = jsonMap;
		return SUCCESS;
	}
	
	private List<OrganizationDTO> findChildOrgList1(String parentid) {
		String hql = " select so from SysOrganization so where 1=1 and so.orgId=? or so.parentId like ? and so.flag=? order by so.orgId";
		Object[] param = null;
		param = new Object[3];
		param[0] = Long.valueOf(parentid);
		param[1] = parentid+"%";
		param[2] = "1";
		List<OrganizationDTO> list = sysService.querySYSOrgs(hql, param);
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String saveOrg(){
		Map jsonMap = new HashMap();
		try {
			sysService.saveSYSOrg(organizationDTO);
			jsonMap.put("msg", "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryusers(){
		Pager pager = new Pager(page, rows, new Long(0));
		List<Object> param = new ArrayList<Object>();
		param.add("1");
		SysOrganization o = new SysOrganization();
		o.setOrgId(Long.valueOf(orgId));
		param.add(o);
		Map jsonMap = new HashMap();
		if("-1".equals(orgId)){
			
		}else{
			List<UserDTO> us = sysService.querySYSUsers(pager,param);
			jsonMap.put("page", page);
			jsonMap.put("total", pager.total);
			jsonMap.put("records", pager.records);
			jsonMap.put("rows", us);
			
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String saveuser(){
		Map jsonMap = new HashMap();
		try{
			sysService.saveSYSUser(userDTO);
			jsonMap.put("msg", "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryuser(){
		userDTO = sysService.querySYSUserById(Long.valueOf(userId));
		Map jsonMap = new HashMap();
		jsonMap.put("user", userDTO);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateuser(){
		Map jsonMap = new HashMap();
		try{
			sysService.updateSYSUserById(Long.valueOf(userId));
			jsonMap.put("msg", "停用成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "停用失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryUserGroup(){
		List<UsergroupDTO> ugs = sysService.querySYSUserGroupAll();
		Map jsonMap = new HashMap();
		jsonMap.put("ugs", ugs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryUser(){
		List<Object> param = new ArrayList<Object>();
		param.add("1");
		SysOrganization o = new SysOrganization();
		o.setOrgId(Long.valueOf(orgId));
		param.add(o);
		List<UserDTO> ugs = sysService.querySYSUsers(param);
		Map jsonMap = new HashMap();
		jsonMap.put("ugs", ugs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String saveUGRelation(){
		String[] us = userIds.split(",");
		List<SysUgrelation> ugrs = new ArrayList<SysUgrelation>();
		for(int i=0; i<us.length; i++){
			SysUgrelation ugr = new SysUgrelation();
			ugr.setUgId(new BigDecimal(ugId));
			ugr.setUserId(new BigDecimal(us[i]));
			ugrs.add(ugr);
		}
		Map jsonMap = new HashMap();
		try{
			sysService.saveSYSUgrelation(ugrs);
			jsonMap.put("msg", "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String addUsergroup(){
		Map jsonMap = new HashMap();
		try{
			sysService.saveSysUsergroup(ugName);
			jsonMap.put("msg", "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		}
		map = jsonMap;
		
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delUsergroup(){
		Map jsonMap = new HashMap();
		try{
			sysService.delSysUsergroup(ugId);
			jsonMap.put("msg", "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "删除失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryRole(){
		List<RoleDTO> rs = sysService.querySYSRoleAll();
		Map jsonMap = new HashMap();
		jsonMap.put("rs", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String addRole(){
		Map jsonMap = new HashMap();
		try{
			sysService.saveSysRole(roleName);
			jsonMap.put("msg", "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		}
		map = jsonMap;
		
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delRole(){
		Map jsonMap = new HashMap();
		try{
			sysService.delSysRole(roleId);
			jsonMap.put("msg", "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "删除失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryUserAll(){
		List<UserDTO> us = sysService.querySYSUserAll();
		Map jsonMap = new HashMap();
		jsonMap.put("us", us);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String saveRoleRelation(){
		Map jsonMap = new HashMap();
		if("".equals(userIds)){
			//用户组操作
			try{
				String[] ugs = usergroudIds.split(",");
				List<SysGrrelation> grrs = new ArrayList<SysGrrelation>();
				for(int ug_i=0; ug_i<ugs.length; ug_i++){
					SysGrrelation grr = new SysGrrelation();
					grr.setRoleId(new BigDecimal(roleId));
					grr.setUgId(new BigDecimal(ugs[ug_i]));;
					grrs.add(grr);
				}
				sysService.saveSYSGrrelation(grrs);
				jsonMap.put("msg", "保存成功！");
			}catch(Exception e){
				e.printStackTrace();
				jsonMap.put("msg", "保存失败！");
			}
		}else if("".equals(usergroudIds)){
			//用户操作
			try{
				String[] us = userIds.split(",");
				List<SysUrrelation> urrs = new ArrayList<SysUrrelation>();
				for(int u_i=0; u_i<us.length; u_i++){
					SysUrrelation urr = new SysUrrelation();
					urr.setRoleId(new BigDecimal(roleId));
					urr.setUserId(new BigDecimal(us[u_i]));;
					urrs.add(urr);
				}
				sysService.saveSYSUrrelation(urrs);
				jsonMap.put("msg", "保存成功！");
			}catch(Exception e){
				e.printStackTrace();
				jsonMap.put("msg", "保存失败！");
			}
		}
		
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryPrivilege(){
		List<PrivilegeDTO> ps = sysService.querySYSPrivilegeAll();
		Map jsonMap = new HashMap();
		jsonMap.put("ps", ps);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryMenus(){
		List<MenuDTO> rs = sysService.querySYSMenusAll();
		Map jsonMap = new HashMap();
		List<ShortcutDTO> sl1 = new ArrayList<ShortcutDTO>();
		for(MenuDTO e:rs){
			ShortcutDTO s = new ShortcutDTO();
			s.setId(e.getMenuId()+"");
			s.setpId(e.getPmId()+"");
			s.setGenId(e.getMenucode());
			s.setOpen(true);
			s.setName(e.getMenuname());
			s.setIco(e.getIco());
			sl1.add(s);
		}
		jsonMap.put("rs", sl1);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryFunctions(){
		List<FunctionDTO> rs = sysService.querySYSFunctionAll();
		Map jsonMap = new HashMap();
		jsonMap.put("rs", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryFiles(){
		List<FileDTO> rs = sysService.querySYSFileAll();
		Map jsonMap = new HashMap();
		jsonMap.put("rs", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String addPrivilege(){
		Map jsonMap = new HashMap();
		try{
			PrivilegeDTO pdto = new PrivilegeDTO();
			pdto.setPriviname(priviname);
			pdto.setPrivicode(privicode);
			sysService.saveSysPrivilege(pdto);
			jsonMap.put("msg", "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		}
		map = jsonMap;
		
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delPrivilege(){
		Map jsonMap = new HashMap();
		try{
			sysService.delSysPrivilege(privilegeId);
			jsonMap.put("msg", "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "删除失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String savePriRelation(){
		Map jsonMap = new HashMap();
		if(!"".equals(roleIds)){
			//角色绑定操作
			try{
				String[] rs = roleIds.split(",");
				List<SysRprelation> rrs = new ArrayList<SysRprelation>();
				for(int rr_i=0; rr_i<rs.length; rr_i++){
					SysRprelation rr = new SysRprelation();
					rr.setRoleId(new BigDecimal(rs[rr_i]));
					rr.setPrivilegeId(new BigDecimal(privilegeId));;
					rrs.add(rr);
				}
				sysService.saveSYSRprelation(rrs);
				jsonMap.put("msg", "保存成功！");
			}catch(Exception e){
				e.printStackTrace();
				jsonMap.put("msg", "保存失败！");
			}
		}else if(!"".equals(menuIds)){
			//菜单绑定操作
			try{
				String[] ms = menuIds.split(",");
				List<SysMprelation> mrs = new ArrayList<SysMprelation>();
				for(int mr_i=0; mr_i<ms.length; mr_i++){
					SysMprelation mr = new SysMprelation();
					mr.setMenuId(new BigDecimal(ms[mr_i]));
					mr.setPrivilegeId(new BigDecimal(privilegeId));;
					mrs.add(mr);
				}
				sysService.saveSYSMprelation(mrs);
				jsonMap.put("msg", "保存成功！");
			}catch(Exception e){
				e.printStackTrace();
				jsonMap.put("msg", "保存失败！");
			}
		}/*else if(!"".equals(functionIds)){
			//功能绑定操作
			String[] fs = functionIds.split(",");
			List<SysPfrelation> prs = new ArrayList<SysPfrelation>();
			for(int pr_i=0; pr_i<fs.length; pr_i++){
				SysPfrelation pr = new SysPfrelation();
				pr.setFunctionId(new BigDecimal(fs[pr_i]));
				pr.setPrivilegeId(new BigDecimal(ps[i]));;
				prs.add(pr);
			}
			try{
				sysService.saveSYSPfrelation(prs);
				jsonMap.put("msg", "保存成功！");
			}catch(Exception e){
				e.printStackTrace();
				jsonMap.put("msg", "保存失败！");
			}
		}else if(!"".equals(fileIds)){
			//文件绑定操作
			String[] files = fileIds.split(",");
			List<SysFprelation> frs = new ArrayList<SysFprelation>();
			for(int fr_i=0; fr_i<files.length; fr_i++){
				SysFprelation fr = new SysFprelation();
				fr.setFileId(new BigDecimal(files[fr_i]));
				fr.setPrivilegeId(new BigDecimal(ps[i]));;
				frs.add(fr);
			}
			try{
				sysService.saveSYSFprelation(frs);
				jsonMap.put("msg", "保存成功！");
			}catch(Exception e){
				e.printStackTrace();
				jsonMap.put("msg", "保存失败！");
			}
		}*/
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryUgChecked(){
		BigDecimal ugid = new BigDecimal(ugId);
		List<SysVUg> ugs = sysService.querySYSUgUsers(ugid);
		Map jsonMap = new HashMap();
		jsonMap.put("ugs", ugs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryGrChecked(){
		BigDecimal roleid = new BigDecimal(roleId);
		List<SysVGr> grs = sysService.querySYSVGr(roleid);
		Map jsonMap = new HashMap();
		jsonMap.put("grs", grs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryUrChecked(){
		BigDecimal roleid = new BigDecimal(roleId);
		List<SysVUr> urs = sysService.querySYSVUr(roleid);
		Map jsonMap = new HashMap();
		jsonMap.put("urs", urs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryRpChecked(){
		BigDecimal privilegeid = new BigDecimal(privilegeId);
		List<SysVRp> rps = sysService.querySYSVRp(privilegeid);
		Map jsonMap = new HashMap();
		jsonMap.put("rps", rps);
		map = jsonMap;
		return SUCCESS;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryMpChecked(){
		BigDecimal privilegeid = new BigDecimal(privilegeId);
		List<SysVMp> mps = sysService.querySYSVMp(privilegeid);
		Map jsonMap = new HashMap();
		jsonMap.put("mps", mps);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryMenuCode(){
		List<SysMenus> sms = sysService.queryMenuCodeByPmid(menuId);
		String newMenuCode = "";
		if(sms.size()>0){
			String mcode = sms.get(0).getMenucode();
			String end=mcode.substring(mcode.length()-1);
			Integer i = Integer.valueOf(end);
			newMenuCode = mcode.substring(0,mcode.length()-1)+(i+1);
			System.out.print(mcode + ":" +newMenuCode);
		}else{
			List<SysMenus> sm = sysService.queryMenuCodeById(menuId);
			newMenuCode = sm.get(0).getMenucode()+"0001";
		}
		Map jsonMap = new HashMap();
		jsonMap.put("code", newMenuCode);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String saveMenu(){
		Map jsonMap = new HashMap();
		try{
			sysService.createMenu(menuDTO);
			jsonMap.put("msg", "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String querymenubyid(){
		SysMenus sm = sysService.queryMenuCodeById(menuId).get(0);
		MenuDTO md = new MenuDTO();
		md.setMenuId(sm.getMenuId());
		md.setMenuname(sm.getMenuname());
		md.setMenucode(sm.getMenucode());
		md.setMenuurl(sm.getMenuurl());
		md.setPmId(sm.getPmId());
		md.setIco(sm.getIco());
		md.setFlag(sm.getFlag());
		md.setRemark(sm.getRemark());
		md.setCtime(sm.getCtime());
		md.setUtime(sm.getUtime());
		Map jsonMap = new HashMap();
		jsonMap.put("sm", md);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String querymenubypmid(){
		List<SysMenus> sms = sysService.queryMenuCodeByPmid(menuId);
		List<MenuDTO> ms = new ArrayList<MenuDTO>();
		for(SysMenus sm : sms){
			MenuDTO md = new MenuDTO();
			md.setMenuId(sm.getMenuId());
			md.setMenuname(sm.getMenuname());
			md.setMenucode(sm.getMenucode());
			md.setMenuurl(sm.getMenuurl());
			md.setPmId(sm.getPmId());
			md.setIco(sm.getIco());
			md.setFlag(sm.getFlag());
			md.setRemark(sm.getRemark());
			md.setCtime(sm.getCtime());
			md.setUtime(sm.getUtime());
			ms.add(md);
		}
		Map jsonMap = new HashMap();
		jsonMap.put("ms", ms);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delmenu(){
		Map jsonMap = new HashMap();
		int u = sysService.delMenu(Long.valueOf(menuId));
		if(u>0){
			jsonMap.put("msg", "删除成功！");
		}else{
			jsonMap.put("msg", "删除失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getMap() {
		return map;
	}

	@SuppressWarnings("rawtypes")
	public void setMap(Map map) {
		this.map = map;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public DistrictsDTO getDistrictsDTO() {
		return districtsDTO;
	}

	public void setDistrictsDTO(DistrictsDTO districtsDTO) {
		this.districtsDTO = districtsDTO;
	}

	public String getDistrictsId() {
		return districtsId;
	}

	public void setDistrictsId(String districtsId) {
		this.districtsId = districtsId;
	}

	public OrganizationDTO getOrganizationDTO() {
		return organizationDTO;
	}

	public void setOrganizationDTO(OrganizationDTO organizationDTO) {
		this.organizationDTO = organizationDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOgId() {
		return ogId;
	}

	public void setOgId(String ogId) {
		this.ogId = ogId;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getUgName() {
		return ugName;
	}

	public void setUgName(String ugName) {
		this.ugName = ugName;
	}

	public String getUgId() {
		return ugId;
	}

	public void setUgId(String ugId) {
		this.ugId = ugId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUsergroudIds() {
		return usergroudIds;
	}

	public void setUsergroudIds(String usergroudIds) {
		this.usergroudIds = usergroudIds;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getPriviname() {
		return priviname;
	}

	public void setPriviname(String priviname) {
		this.priviname = priviname;
	}

	public String getPrivicode() {
		return privicode;
	}

	public void setPrivicode(String privicode) {
		this.privicode = privicode;
	}

	public String getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public MenuDTO getMenuDTO() {
		return menuDTO;
	}

	public void setMenuDTO(MenuDTO menuDTO) {
		this.menuDTO = menuDTO;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
