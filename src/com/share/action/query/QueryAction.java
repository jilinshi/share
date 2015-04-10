package com.share.action.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.HousepropertyDTO;
import com.share.dto.InsuranceDTO;
import com.share.model.VCkhouseproperty;
import com.share.model.VCkinsurance;
import com.share.service.YbService;
import com.share.util.Pager;

@Controller
public class QueryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(QueryAction.class);
	
	@Resource
	private YbService ybjkService;
	
	@SuppressWarnings("rawtypes")
	private Map map;// ���ص�json
	 /** ��ǰҳ�� */ 
	private String page;  
	 /** ÿҳ�ļ�¼�� */ 
	private String rows;
	
	private InsuranceDTO insuranceDTO;
	private HousepropertyDTO housepropertyDTO;
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryInsurances() {
		Pager pager = new Pager(page, rows, new Long(0));
		List<Object> param = new ArrayList<Object>();
		String jwhere = "";
		if(this.insuranceDTO.getIdno()==null||"".equals(this.insuranceDTO.getIdno())){	
		}else{
			param.add(this.insuranceDTO.getIdno());
			jwhere=jwhere + " and ci.idno=? "; 
		}
		if(this.insuranceDTO.getPname()==null||"".equals(this.insuranceDTO.getPname())){
		}else{
			param.add(this.insuranceDTO.getPname());
			jwhere=jwhere + " and ci.pname=? ";
		}
		List<VCkinsurance> rs = ybjkService.queryCkInsurances(pager, param, jwhere);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryInsurance() {
		List<VCkinsurance> rs = ybjkService.queryCkInsuranceById(insuranceDTO);
		Map jsonMap = new HashMap();
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryHousepropertys() {
		Pager pager = new Pager(page, rows, new Long(0));
		List<Object> param = new ArrayList<Object>();
		String jwhere = "";
		if(this.housepropertyDTO.getIdno()==null||"".equals(this.housepropertyDTO.getIdno())){	
		}else{
			param.add(this.housepropertyDTO.getIdno());
			jwhere=jwhere + " and ch.idno=? "; 
		}
		if(this.housepropertyDTO.getPname()==null||"".equals(this.housepropertyDTO.getPname())){
		}else{
			param.add(this.housepropertyDTO.getPname());
			jwhere=jwhere + " and ch.pname=? ";
		}
		List<VCkhouseproperty> rs = ybjkService.queryCkHousepropertys(pager, param, jwhere);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryHouseproperty() {
		List<VCkhouseproperty> rs = ybjkService.queryCkhousepropertyById(housepropertyDTO);
		Map jsonMap = new HashMap();
		jsonMap.put("rows", rs);
		map = jsonMap;
		System.out.println(map.toString());
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

	public InsuranceDTO getInsuranceDTO() {
		return insuranceDTO;
	}

	public void setInsuranceDTO(InsuranceDTO insuranceDTO) {
		this.insuranceDTO = insuranceDTO;
	}

	public HousepropertyDTO getHousepropertyDTO() {
		return housepropertyDTO;
	}

	public void setHousepropertyDTO(HousepropertyDTO housepropertyDTO) {
		this.housepropertyDTO = housepropertyDTO;
	}

}
