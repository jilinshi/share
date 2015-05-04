package com.share.action.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.FileDTO;
import com.share.dto.MemberDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.ReportDTO;
import com.share.dto.UserDTO;
import com.share.model.Personalinfo;
import com.share.model.VCkburial;
import com.share.model.VCkfund;
import com.share.model.VCkhouseproperty;
import com.share.model.VCkinsurance;
import com.share.model.VCkvehicle;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String printcollatingreport() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			String path = ServletActionContext.getServletContext().getRealPath(
					"/")
					+ "\\page\\html\\content\\report\\collating_report.jasper";
			String subPath = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ "\\page\\html\\content\\report\\collating_report_subreport1.jasper";
			String subPath1 = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ "\\page\\html\\content\\report\\report1.jasper";
			String subPath2 = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ "\\page\\html\\content\\report\\report2.jasper";
			String subPath3 = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ "\\page\\html\\content\\report\\report3.jasper";
			String subPath4 = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ "\\page\\html\\content\\report\\report4.jasper";
			String subPath5 = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ "\\page\\html\\content\\report\\report5.jasper";
			// System.out.println("subPath:" + subPath);
			// 获得输出流
			ServletOutputStream outputStream = response.getOutputStream();
			// 获得输入流
			InputStream inputStream = new FileInputStream(new File(path));
			// 生成网页的PDF文件

			JasperReport subrep = (JasperReport) JRLoader.loadObject(new File(
					subPath));
			JasperReport subrep1 = (JasperReport) JRLoader.loadObject(new File(
					subPath1));
			JasperReport subrep2 = (JasperReport) JRLoader.loadObject(new File(
					subPath2));
			JasperReport subrep3 = (JasperReport) JRLoader.loadObject(new File(
					subPath3));
			JasperReport subrep4 = (JasperReport) JRLoader.loadObject(new File(
					subPath4));
			JasperReport subrep5 = (JasperReport) JRLoader.loadObject(new File(
					subPath5));

			// 放入子报表
			HashMap map = new HashMap();
			map.put("hdbgno", "第213213216546321321654号");
			map.put("subrep1", subrep);
			map.put("subrep2", subrep1);
			map.put("subrep3", subrep2);
			map.put("subrep4", subrep3);
			map.put("subrep5", subrep4);
			map.put("subrep6", subrep5);

			ArrayList<ReportDTO> list = new ArrayList<ReportDTO>();
			ReportDTO arg0 = new ReportDTO();

			ArrayList<Personalinfo> persons = (ArrayList) reportService
					.findPersonsByMAID("120221195506211810");
			arg0.setList(new JRBeanCollectionDataSource(persons));

			ArrayList<VCkinsurance> list1 = (ArrayList<VCkinsurance>) reportService
					.findInsuranceByMAID("220202196406193626");

			ArrayList<VCkburial> list2 = (ArrayList<VCkburial>) reportService
					.findBurialByMAID("222405196707221020");
			ArrayList<VCkfund> list3 = (ArrayList<VCkfund>) reportService
					.findFundByMAID("220204195907232118");
			ArrayList<VCkhouseproperty> list4 = (ArrayList<VCkhouseproperty>) reportService
					.findHousepropertyByMAID("220204196304042715");
			ArrayList<VCkvehicle> list5 = (ArrayList<VCkvehicle>) reportService
					.findVehicleByMAID("220202195001044812");

			arg0.setCol1("1.社保信息");
			arg0.setList1(new JRBeanCollectionDataSource(list1));
			arg0.setCol2("2.殡葬信息");
			arg0.setList2(new JRBeanCollectionDataSource(list2));
			arg0.setCol3("3.公积金信息");
			arg0.setList3(new JRBeanCollectionDataSource(list3));
			arg0.setCol4("4.房产信息");
			arg0.setList4(new JRBeanCollectionDataSource(list4));
			arg0.setCol5("5.车辆信息");
			arg0.setList5(new JRBeanCollectionDataSource(list5));
			list.add(arg0);

			JasperRunManager.runReportToPdfStream(inputStream, outputStream,
					map, new JRBeanCollectionDataSource(list));
			// 设置PDF格式
			response.setContentType("application/pdf");
			outputStream.flush();
			outputStream.close();
		} catch (JRException e) {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			response.setContentType("text/plain");
			response.setCharacterEncoding("ISO 8859-1");
			try {
				response.getOutputStream().print(stringWriter.toString());
				// response.getWriter().print(stringWriter.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
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
