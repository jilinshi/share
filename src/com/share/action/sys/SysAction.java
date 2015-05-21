package com.share.action.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.DistrictsDTO;
import com.share.service.SysService;

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String findOrgList() {
		String hql = " select sd from SysDistrict sd where 1=1 and sd.flag=? order by sd.districtsId";
		Object[] param = null;
		param = new Object[1];
		param[0] = "1";
		List<DistrictsDTO> list = sysService.querySYSDistrict(hql, param);
		Map jsonMap = new HashMap();
		jsonMap.put("orgs", list);
		map = jsonMap;
		return SUCCESS;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String findOrgListALL() {
		Map jsonMap = new HashMap();
		List<DistrictsDTO> orgs = findChildOrgList(parentid);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray
				.fromObject(orgs);
		// System.out.println(jsonArray);
		jsonMap.put("orgs", orgs);
		map = jsonMap;
		return SUCCESS;
	}

	private List<DistrictsDTO> findChildOrgList(String parentid) {
		List<DistrictsDTO> templist = new ArrayList<DistrictsDTO>();
		String hql = " select sd from SysDistrict sd where 1=1 and sd.parentId=? and sd.flag=? order by sd.districtsId";
		Object[] param = null;
		param = new Object[2];
		param[0] = parentid;
		param[1] = "1";
		List<DistrictsDTO> list = sysService.querySYSDistrict(hql, param);
		for (DistrictsDTO e : list) {
			templist.add(e);
			templist.addAll(findChildOrgList(e.getDistrictsId()));
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

}
