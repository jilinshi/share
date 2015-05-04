package com.share.action.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.FileDTO;
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
	private String familyno;
	private String masterid; 
	
	private File single; // 上传的文件
	private String singleFileName; // 文件名称
	private String singleContentType; // 文件类型
	
	private List<File> afils;
	private List<String> afilsFileName;

	private FileDTO fileDTO;
	
	private static final String savepath = "C:\\uploadfiles\\";

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

		String displayname = "";
		String realname = "";
		String realpath = "";

		try {
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			// 取得需要上传的文件数组
			File files = this.getSingle();
			// 建立上传文件的输出流, getImageFileName()[i]
			displayname = this.getSingleFileName();
			realname = this.generateFileName(displayname);
			realpath = savepath + "" + realname;
			FileOutputStream fos = new FileOutputStream(realpath);
			// 建立上传文件的输入流
			FileInputStream fis = new FileInputStream(files);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();

			Map jsonMap = new HashMap();
			jsonMap.put("fileid", "-1");// total键
			jsonMap.put("realname", realname);
			jsonMap.put("realpath", realpath);
			jsonMap.put("displayname", displayname);
			jsonMap.put("filename", displayname);
			map = jsonMap;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	/**
	 * 用日期和随机数格式化文件名避免冲突
	 * 
	 * @param fileName
	 * @return
	 */
	private String generateFileName(String fileName) {
		System.out.println(fileName);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = sf.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}
	
	public String removedfile() {
		System.out.println(fileDTO.getFilename());
		File file = new File(fileDTO.getRealpath());
		if (file.exists() && file.isFile()) {
			file.delete();
		}
		return SUCCESS;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getPInfo(){
		System.out.println(masterid);
		UserDTO user = (UserDTO) ActionContext.getContext().getSession()
				.get("user");
		String orgcode = user.getSysOrganization().getOrgCode();
		String code = orgcode.substring(0,6);
		String nowTime = new SimpleDateFormat("yyyyMMdd").format(new Date());// 当前时间
		int random = new Random().nextInt(10000);
		String wtno = code + nowTime + random;
		List<MemberDTO> memberDTOs = reportService.getPersonsByFNO(familyno);
		Map jsonMap = new HashMap();
		jsonMap.put("memberDTOs", memberDTOs);
		jsonMap.put("wtno", wtno);
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

	public File getSingle() {
		return single;
	}

	public void setSingle(File single) {
		this.single = single;
	}

	public String getSingleFileName() {
		return singleFileName;
	}

	public void setSingleFileName(String singleFileName) {
		this.singleFileName = singleFileName;
	}

	public String getSingleContentType() {
		return singleContentType;
	}

	public void setSingleContentType(String singleContentType) {
		this.singleContentType = singleContentType;
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

}
