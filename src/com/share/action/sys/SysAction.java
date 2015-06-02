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
import com.share.dto.OrganizationDTO;
import com.share.dto.ShortcutDTO;
import com.share.dto.UserDTO;
import com.share.model.SysOrganization;
import com.share.model.SysUser;
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
	 /** 当前页面 */ 
	private String page;  
	 /** 每页的记录数 */ 
	private String rows;
	private String userId;

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
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

}
