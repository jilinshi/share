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
import com.share.dto.InsuranceDTO;
import com.share.model.CkInsurance;
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
	private Map map;// 返回的json
	 /** 当前页面 */ 
	private String page;  
	 /** 每页的记录数 */ 
	private String rows;
	
	private InsuranceDTO insuranceDTO;
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryInit() {
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
		List<InsuranceDTO> rs = ybjkService.queryCkInsurances(pager, param, jwhere);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
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

}
