package com.share.action.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.mongodb.DBObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.AttorneyrecordDTO;
import com.share.dto.FileDTO;
import com.share.dto.MemberDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.UserDTO;
import com.share.model.Personalinfo;
import com.share.mongodb.MongoDBManager;
import com.share.service.ReportService;
import com.share.util.IDCard;
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
	private String familyno;
	private String masterid;
	private String mastername;
	private String idcard;
	private AttorneyrecordDTO attorneyrecordDTO;
	
	private List<File> afils;
	private List<String> afilsFileName;
	private List<String> afilenames;

	private FileDTO fileDTO;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryPersonalInfo() {
		Pager pager = new Pager(page, rows, new Long(0));
		List<Object> param = new ArrayList<Object>();
		String jwhere = "";
		if (this.memberDTO.getOnNo() == null
				|| "".equals(this.memberDTO.getOnNo().trim())) {
		} else {
			param.add(this.memberDTO.getOnNo()+"%");
			jwhere = jwhere + " and p.col1 like ? ";
		}
		if (this.memberDTO.getPaperid() == null
				|| "".equals(this.memberDTO.getPaperid())) {
		} else {
			param.add(this.memberDTO.getPaperid());
			jwhere = jwhere + " and p.idno=? ";
		}
		if (this.memberDTO.getMembername() == null
				|| "".equals(this.memberDTO.getMembername())) {
		} else {
			param.add(this.memberDTO.getMembername());
			jwhere = jwhere + " and p.pname=? ";
		}
		List<Personalinfo> rs = reportService.queryPersonalinfos(pager, param,
				jwhere);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getOrgList() {
		UserDTO user = (UserDTO) ActionContext.getContext().getSession()
				.get("user");
		long orgid = user.getSysOrganization().getOrgId();
		orgs = reportService.findOrganlist(orgid);
		Map jsonMap = new HashMap();
		jsonMap.put("orgs", orgs);
		map = jsonMap;
		return SUCCESS;
	}
	
	/**
	 * 单文件上传 input type=file name=single 循环逐个上传
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String upload() {
		Map jsonMap = new HashMap();
		UserDTO user = (UserDTO) ActionContext.getContext().getSession()
				.get("user");
		long orgid = user.getSysOrganization().getOrgId();
		String displayname = "";
		String realname = "";
		String type = ".jpg";
		try {
			// 委托数据保存
			String code = masterid.substring(0,6);
			String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// 当前时间
			String wtno = code + nowTime;
			String ckmonth=nowTime.substring(0,6);
			attorneyrecordDTO.setMasteridno(masterid);
			attorneyrecordDTO.setMastername(mastername);
			attorneyrecordDTO.setCkmonth(ckmonth);
			attorneyrecordDTO.setAttorneyId(wtno);
			attorneyrecordDTO.setAttorney("第"+wtno+"号");
			attorneyrecordDTO.setCkcontent("社保、公积金、殡葬、房产、驾管");
			attorneyrecordDTO.setUpoper(orgid+"");
			reportService.saveAttorneyRecord(attorneyrecordDTO);
			// 附件保存到数据库中
			MongoDBManager mongo = new MongoDBManager("sharefile");
			for(int i=0;i<afils.size();i++){
				ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
				// 取得需要上传的文件数组
				File files = afils.get(i);
				displayname = this.getAfilenames().get(i);
				realname = displayname + type;
				// 建立上传文件的输入流
				FileInputStream fis = new FileInputStream(files);
				//
				String id = ObjectId.get().toString();
				DBObject metadata = null;
				mongo.insertFile("sharefile", "attorneyfile", id, realname, "application/jpg", metadata, fis);
			}
			mongo.close();
			jsonMap.put("msg", "保存成功！");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		}
		map = jsonMap;

		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getPInfo(){
		List<MemberDTO> memberDTOs = reportService.getPersonsByFNO(familyno);
		String mastername = memberDTOs.get(0).getMasterName();
		String masterpaperid = memberDTOs.get(0).getMasetPaperid();
		Map jsonMap = new HashMap();
		jsonMap.put("memberDTOs", memberDTOs);
		jsonMap.put("mastername", mastername);
		jsonMap.put("masterpaperid", masterpaperid);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getCount(){
		String nowMonth = new SimpleDateFormat("yyyyMM").format(new Date());// 当前月份
		AttorneyrecordDTO ardto = new AttorneyrecordDTO();
		ardto.setCkmonth(nowMonth);
		ardto.setMasteridno(masterid);
		ardto.setFlag("1");
		Long count = reportService.queryAttorneyRecordCount(ardto);
		Map jsonMap = new HashMap();
		jsonMap.put("count", count);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryPersonals(){
		Pager pager = new Pager(page, rows, new Long(0));
		List<Object> param = new ArrayList<Object>();
		String jwhere = "";
		if (this.memberDTO.getPaperid() == null
				|| "".equals(this.memberDTO.getPaperid())) {
		} else {
			param.add(this.memberDTO.getPaperid());
			jwhere = jwhere + " and p.idno=? ";
		}	
		List<Personalinfo> rs = reportService.queryPersonalinfoAll(pager, param , jwhere);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String savePInfo(){
		Map jsonMap = new HashMap();
		try{
			reportService.savePersonalinfo(memberDTO);
			jsonMap.put("msg", "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("msg", "保存失败！");
		}
		map = jsonMap;
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String valPeperid(){
		Map jsonMap = new HashMap();
		try {
			String msg = IDCard.IDCardValidate(idcard);
			if("".equals(msg)){
				List<MemberDTO> mems = reportService.queryPersonByCard(idcard);
				if(mems.size()>0){
					jsonMap.put("mems", mems.get(0));
					jsonMap.put("result", "1");
				}else{
					jsonMap.put("result", "2");
				}
			}else{
				jsonMap.put("result", "3");
				jsonMap.put("msg", msg);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			jsonMap.put("msg", "系统错误！");
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

	public FileDTO getFileDTO() {
		return fileDTO;
	}

	public void setFileDTO(FileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}

	public String getFamilyno() {
		return familyno;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public List<File> getAfils() {
		return afils;
	}

	public void setAfils(List<File> afils) {
		this.afils = afils;
	}

	public List<String> getAfilsFileName() {
		return afilsFileName;
	}

	public void setAfilsFileName(List<String> afilsFileName) {
		this.afilsFileName = afilsFileName;
	}

	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public AttorneyrecordDTO getAttorneyrecordDTO() {
		return attorneyrecordDTO;
	}

	public void setAttorneyrecordDTO(AttorneyrecordDTO attorneyrecordDTO) {
		this.attorneyrecordDTO = attorneyrecordDTO;
	}

	public List<String> getAfilenames() {
		return afilenames;
	}

	public void setAfilenames(List<String> afilenames) {
		this.afilenames = afilenames;
	}

	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

}
