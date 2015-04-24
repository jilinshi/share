package com.share.action.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.MemberDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.UserDTO;
import com.share.model.Personalinfo;
import com.share.service.ReportService;
import com.share.util.Pager;

@Controller
public class ReportAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(ReportAction.class);

	@Resource
	private ReportService reportService;
	
	@SuppressWarnings("rawtypes")
	private Map map;// 返回的json
	 /** 当前页面 */ 
	private String page;  
	 /** 每页的记录数 */ 
	private String rows;
	private MemberDTO memberDTO;
	private List<OrganizationDTO> orgs;
	private String result;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryPersonalInfo(){
		Pager pager = new Pager(page, rows, new Long(0));
		List<Object> param = new ArrayList<Object>();
		String jwhere = "";
		if(this.memberDTO.getPaperid()==null||"".equals(this.memberDTO.getPaperid())){	
		}else{
			param.add(this.memberDTO.getPaperid());
			jwhere=jwhere + " and p.idno=? "; 
		}
		if(this.memberDTO.getMembername()==null||"".equals(this.memberDTO.getMembername())){
		}else{
			param.add(this.memberDTO.getMembername());
			jwhere=jwhere + " and p.pname=? ";
		}
		List<Personalinfo> rs = reportService.queryPersonalinfos(pager, param, jwhere);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getOrgList(){
		UserDTO user = (UserDTO) ActionContext.getContext().getSession()
				.get("user");
		long orgid = user.getSysOrganization().getOrgId();
		orgs = reportService.findOrganlist(orgid);
		Map jsonMap = new HashMap(); 
		jsonMap.put("orgs", orgs);
		map=jsonMap;	
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String printcollatingreport(){
		map = new HashMap<String, String>();
		memberDTO.setAddress("111");
		memberDTO.setMembername("ABC");
		memberDTO.setFamilyno("1234567890");
		map.put("add", memberDTO.getAddress());
		map.put("name", memberDTO.getMembername());
		map.put("no", memberDTO.getFamilyno());
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

	public MemberDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	public List<OrganizationDTO> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<OrganizationDTO> orgs) {
		this.orgs = orgs;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
