package com.share.action.report;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.MemberDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.ReportDTO;
import com.share.dto.UserDTO;
import com.share.model.Personalinfo;
import com.share.model.VAttorney;
import com.share.model.VCkburial;
import com.share.model.VCkfund;
import com.share.model.VCkhouseproperty;
import com.share.model.VCkinsurance;
import com.share.model.VCkvehicle;
import com.share.mongodb.MongoDBManager;
import com.share.service.CheckReportService;
import com.share.service.ReportService;
import com.share.util.Pager;

@Controller
public class PrintReportAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private ReportService reportService;
	@Resource
	private CheckReportService checkReportService;

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
	private String imgname;

	private List<MemberDTO> memberDTOs;

	/**
	 * 为这户家庭生成核对报告
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String printcollatingreport() {
		UserDTO user = (UserDTO) ActionContext.getContext().getSession()
				.get("user");
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
			String datastr = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			datastr = sdf.format(new Date());
			HashMap map = new HashMap();
			map.put("hdbgno", "第" + masterid + datastr + "号");
			map.put("subrep1", subrep);
			map.put("subrep2", subrep1);
			map.put("subrep3", subrep2);
			map.put("subrep4", subrep3);
			map.put("subrep5", subrep4);
			map.put("subrep6", subrep5);

			ArrayList<ReportDTO> list = new ArrayList<ReportDTO>();
			ReportDTO arg0 = new ReportDTO();

			ArrayList<Personalinfo> persons = (ArrayList) reportService
					.findPersonsByMAID(this.masterid);
			arg0.setList(new JRBeanCollectionDataSource(persons));

			ArrayList<VCkinsurance> list1 = (ArrayList<VCkinsurance>) reportService
					.findInsuranceByMAID(this.masterid);

			ArrayList<VCkburial> list2 = (ArrayList<VCkburial>) reportService
					.findBurialByMAID(this.masterid);
			ArrayList<VCkfund> list3 = (ArrayList<VCkfund>) reportService
					.findFundByMAID(this.masterid);
			ArrayList<VCkhouseproperty> list4 = (ArrayList<VCkhouseproperty>) reportService
					.findHousepropertyByMAID(this.masterid);
			ArrayList<VCkvehicle> list5 = (ArrayList<VCkvehicle>) reportService
					.findVehicleByMAID(this.masterid);

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

			/*
			 * JasperRunManager.runReportToPdfStream(inputStream, outputStream,
			 * map, new JRBeanCollectionDataSource(list));
			 */

			byte[] p = JasperRunManager.runReportToPdf(inputStream, map,
					new JRBeanCollectionDataSource(list));
			ByteArrayInputStream bais = new ByteArrayInputStream(p);

			// 每次生成核对报告 保存到数据库中
			MongoDBManager mongo = new MongoDBManager("sharefile");
			String id = ObjectId.get().toString();
			DBObject metadata = new BasicDBObject();
			metadata.put("operuser", user.getUname());
			metadata.put("operuserid", user.getUserId());
			mongo.insertFile("sharefile", "checkreport", id, this.masterid
					+ ".pdf", "application/pdf", metadata, bais);
			mongo.close();

			outputStream.write(p);
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
	 * 查询本月上传授权书的家庭
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryAttorneyrecord() {
		Pager pager = new Pager(page, rows, new Long(0));
		List<Object> param = new ArrayList<Object>();
		String jwhere = "";
		if (this.memberDTO.getOnNo() == null
				|| "".equals(this.memberDTO.getOnNo().trim())) {
		} else {
			param.add(this.memberDTO.getOnNo() + "%");
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
		List<VAttorney> rs = checkReportService.findAllAttorneyrecord(pager,
				param, jwhere);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;

	}

	/**
	 * 查询历史上传授权书的家庭
	 * 
	 * @return
	 */
	public String queryHAttorneyrecord() {
		return SUCCESS;
	}

	/**
	 * 根据户主身份证号查询所有报告 按时间倒叙排列
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryReportsByMaid() {
		MongoDBManager mongo = new MongoDBManager("sharefile");
		/*
		 * BasicDBObject sort = new BasicDBObject(); sort.put("$date", "-1");
		 */
		BasicDBObject filter = new BasicDBObject();
		filter.put("filename", this.masterid + ".pdf");
		BasicDBObject sort = new BasicDBObject();
		sort.put("uploadDate", "-1");
		List<GridFSDBFile> rs = mongo.readFiles("sharefile", "checkreport",
				filter);
		mongo.close();
		Map jsonMap = new HashMap();
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}

	/**
	 * 查询单个核对报告 mangodb 取出pdf
	 * 
	 * @return 返回pdf
	 */
	public String queryOneReport() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ServletOutputStream pw;
		try {
			request.setCharacterEncoding("utf-8");
			pw = response.getOutputStream();
			MongoDBManager mongo = new MongoDBManager("sharefile");
			BasicDBObject filter = new BasicDBObject();
			filter.put("_id", this.masterid);
			InputStream is = mongo.readFile("sharefile", "checkreport", filter);
			byte[] byteArr = new byte[1024];
			int readCount = is.read(byteArr);
			while (readCount != -1) {
				pw.write(byteArr, 0, readCount);
				readCount = is.read(byteArr);
			}
			response.setContentType("application/pdf");
			pw.flush();
			pw.close();
			mongo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public String queryOneAttorney() {
		memberDTOs = reportService.getPersonsByFNO(masterid);
		return SUCCESS;
	}

	public String queryAttorneysByMaid() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ServletOutputStream pw;
		try {
			request.setCharacterEncoding("utf-8");
			pw = response.getOutputStream();
			MongoDBManager mongo = new MongoDBManager("sharefile");
			System.out.println(imgname + ".jpg");
			InputStream is = mongo.readFile("sharefile", "attorneyfile",
					imgname + ".jpg");
			byte[] byteArr = new byte[1024];
			int readCount = is.read(byteArr);
			while (readCount != -1) {
				pw.write(byteArr, 0, readCount);
				readCount = is.read(byteArr);
			}
			response.setContentType("application/jpg");
			pw.flush();
			pw.close();
			mongo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
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

	public String getFamilyno() {
		return familyno;
	}

	public void setFamilyno(String familyno) {
		this.familyno = familyno;
	}

	public String getMasterid() {
		return masterid;
	}

	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}

	public List<MemberDTO> getMemberDTOs() {
		return memberDTOs;
	}

	public void setMemberDTOs(List<MemberDTO> memberDTOs) {
		this.memberDTOs = memberDTOs;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

}
