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
import com.share.dto.BurialDTO;
import com.share.dto.FundDTO;
import com.share.dto.HousepropertyDTO;
import com.share.dto.InsuranceDTO;
import com.share.dto.VehicleDTO;
import com.share.model.VCkburial;
import com.share.model.VCkfund;
import com.share.model.VCkhouseproperty;
import com.share.model.VCkinsurance;
import com.share.model.VCkvehicle;
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
	//社保信息
	private InsuranceDTO insuranceDTO;
	//房产信息
	private HousepropertyDTO housepropertyDTO;
	//车辆信息
	private VehicleDTO vehicleDTO;
	//殡葬信息
	private BurialDTO burialDTO;
	//公积金信息
	private FundDTO fundDTO;
	
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
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryVehicles() {
		Pager pager = new Pager(page, rows, new Long(0));
		List<Object> param = new ArrayList<Object>();
		String jwhere = "";
		if(this.vehicleDTO.getIdno()==null||"".equals(this.vehicleDTO.getIdno())){	
		}else{
			param.add(this.vehicleDTO.getIdno());
			jwhere=jwhere + " and cv.idno=? "; 
		}
		if(this.vehicleDTO.getPname()==null||"".equals(this.vehicleDTO.getPname())){
		}else{
			param.add(this.vehicleDTO.getPname());
			jwhere=jwhere + " and cv.pname=? ";
		}
		List<VCkvehicle> rs = ybjkService.queryCkvehicles(pager, param, jwhere);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryVehicle() {
		List<VCkvehicle> rs = ybjkService.queryCkvehicleById(vehicleDTO);
		Map jsonMap = new HashMap();
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryBurials() {
		Pager pager = new Pager(page, rows, new Long(0));
		List<Object> param = new ArrayList<Object>();
		String jwhere = "";
		if(this.burialDTO.getIdno()==null||"".equals(this.burialDTO.getIdno())){	
		}else{
			param.add(this.burialDTO.getIdno());
			jwhere=jwhere + " and cb.idno=? "; 
		}
		if(this.burialDTO.getPname()==null||"".equals(this.burialDTO.getPname())){
		}else{
			param.add(this.burialDTO.getPname());
			jwhere=jwhere + " and cb.pname=? ";
		}
		List<VCkburial> rs = ybjkService.queryCkburials(pager, param, jwhere);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryBurial() {
		List<VCkburial> rs = ybjkService.queryCkburialById(burialDTO);
		Map jsonMap = new HashMap();
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryFunds() {
		Pager pager = new Pager(page, rows, new Long(0));
		List<Object> param = new ArrayList<Object>();
		String jwhere = "";
		if(this.fundDTO.getIdno()==null||"".equals(this.fundDTO.getIdno())){	
		}else{
			param.add(this.fundDTO.getIdno());
			jwhere=jwhere + " and cf.idno=? "; 
		}
		if(this.fundDTO.getPname()==null||"".equals(this.fundDTO.getPname())){
		}else{
			param.add(this.fundDTO.getPname());
			jwhere=jwhere + " and cf.pname=? ";
		}
		List<VCkfund> rs = ybjkService.queryCkfunds(pager, param, jwhere);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryFund() {
		List<VCkfund> rs = ybjkService.queryCkfundById(fundDTO);
		Map jsonMap = new HashMap();
		jsonMap.put("rows", rs);
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

	public VehicleDTO getVehicleDTO() {
		return vehicleDTO;
	}

	public void setVehicleDTO(VehicleDTO vehicleDTO) {
		this.vehicleDTO = vehicleDTO;
	}

	public BurialDTO getBurialDTO() {
		return burialDTO;
	}

	public void setBurialDTO(BurialDTO burialDTO) {
		this.burialDTO = burialDTO;
	}

	public FundDTO getFundDTO() {
		return fundDTO;
	}

	public void setFundDTO(FundDTO fundDTO) {
		this.fundDTO = fundDTO;
	}

}
