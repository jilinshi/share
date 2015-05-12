package com.share.action.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.DistrictsDTO;
import com.share.dto.SysDictionaryDTO;
import com.share.model.SysDistrict;
import com.share.service.SysMgrService;

@Controller
public class CommonAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(CommonAction.class);
	@Resource
	private SysMgrService sysMgrService;
	private SysDictionaryDTO sysDictionaryDTO;
	@SuppressWarnings("rawtypes")
	private Map map;// ·µ»ØµÄjson
	private BigDecimal sequ;
	private Long orgId;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getOrgs() {
		String hql = " select sd from SysDistrict sd where 1=1 and sd.districtsId like ? and sd.flag='1' ";
		Object[] param = null;
		param = new Object[1];
		param[0] = orgId+"%";
		List<DistrictsDTO> districts=sysMgrService.queryDistricts(hql, param);
		Map jsonMap = new HashMap();
		jsonMap.put("districts", districts);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDictionary(){
		String hql = " select sd from SysDictionary sd where 1=1 and sd.sequ=? and sd.flag='1' ";
		Object[] param = null;
		param = new Object[1];
		param[0] = sequ;
		List<HashMap> dictionary=sysMgrService.queryData(hql, param);
		Map jsonMap = new HashMap();
		jsonMap.put("dictionary", dictionary);
		map = jsonMap;
		return SUCCESS;
	}

	public SysDictionaryDTO getSysDictionaryDTO() {
		return sysDictionaryDTO;
	}

	public void setSysDictionaryDTO(SysDictionaryDTO sysDictionaryDTO) {
		this.sysDictionaryDTO = sysDictionaryDTO;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public BigDecimal getSequ() {
		return sequ;
	}

	public void setSequ(BigDecimal sequ) {
		this.sequ = sequ;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
