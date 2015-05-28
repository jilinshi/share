package com.share.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.share.dao.BaseDAO;
import com.share.service.SysMgrService;
import com.share.util.ExportExcel;
import com.share.util.XmlExcel;

@Controller
@SuppressWarnings("all")
public class DownloadExcelAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory
			.getLogger(DownloadExcelAction.class);
	private String sql;
	private String hql;
	private List<Object> param;
	private Object[] param1;
	private LinkedHashMap<String, String> title = new LinkedHashMap<String, String>();
	private String fileName;
	private InputStream excelFile;
	private InputStream excelCsv;

	@Resource
	private SysMgrService sysMgrService;
	@Resource
	private BaseDAO<HashMap> baseDAO;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
		this.setTitle(XmlExcel.getXmlexcel().getTableMap(this.fileName));
		if (title.size() > 0) {

		} else {
			this.setTitle((LinkedHashMap<String, String>) session.get("title"));
		}
		sql = (String) session.get("sql");
		hql = (String) session.get("hql");
		param = (List<Object>) session.get("param");
		param1 = (Object[]) session.get("param1");

		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd-HH-mm-ss");
		String nowdate = bartDateFormat.format(new Date());

		if (fileName == null || "".equals(fileName)) {
			fileName = "EXCEL";
		}
		// String fn = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
		String fn = fileName;
		String f = new String((fn + "(" + nowdate + ")").getBytes("gb2312"),
				"ISO8859-1");
		fileName = "attachment; filename=" + f + ".xls";
		return super.execute();
	}

	@SuppressWarnings("rawtypes")
	public InputStream getExcelFile() {
		List<HashMap> rs = null;
		if (null != param) {
			rs = sysMgrService.queryData(hql, param);
		}
		if (null != param1) {
			rs = sysMgrService.queryData(hql, param1);
		}
		ByteArrayInputStream bais = null;
		ExportExcel ee = new ExportExcel();
		ByteArrayOutputStream baos = ee.genExcelData(title, rs);
		if (null != baos) {
			byte[] ba = baos.toByteArray();
			bais = new ByteArrayInputStream(ba);
		}
		return bais;
	}

	/**
	 * 生成csv
	 * 
	 * @return
	 */
	public InputStream getExcelCsv() {
		List<HashMap> rs = null;
		if (null != param) {
			rs = sysMgrService.queryData(hql, param);
		}
		if (null != param1) {
			rs = sysMgrService.queryData(hql, param1);
		}

		return excelCsv;
	}

	public String downloadCSV() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			this.setTitle(XmlExcel.getXmlexcel().getTableMap(this.fileName));
			Map session = ActionContext.getContext().getSession();
			sql = (String) session.get("sql");
			hql = (String) session.get("hql");
			param = (List<Object>) session.get("param");
			param1 = (Object[]) session.get("param1");
			List<HashMap> rs = null;
			if (null != param) {
				rs = sysMgrService.queryData(hql, param);
			}
			if (null != param1) {
				rs = sysMgrService.queryData(hql, param1);
			}
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd-HH-mm-ss");
			String nowdate = bartDateFormat.format(new Date());
			if (fileName == null || "".equals(fileName)) {
				fileName = "CSV";
			}
			String fn = fileName;
			String f = new String(
					(fn + "(" + nowdate + ")").getBytes("gb2312"), "ISO8859-1");
			response.setContentType("application/csv");
			response.setHeader("Content-Disposition", "inline; filename=" + f+".csv");
			out = new PrintWriter(new OutputStreamWriter(
					response.getOutputStream(), "UTF-8"));
			ExportExcel ee = new ExportExcel();
			out.write(ee.genCSV(title, rs).toString());
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public LinkedHashMap<String, String> getTitle() {
		return title;
	}

	public void setTitle(LinkedHashMap<String, String> title) {
		this.title = title;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public List<Object> getParam() {
		return param;
	}

	public void setParam(List<Object> param) {
		this.param = param;
	}

	public Object[] getParam1() {
		return param1;
	}

	public void setParam1(Object[] param1) {
		this.param1 = param1;
	}

	public void setExcelCsv(InputStream excelCsv) {
		this.excelCsv = excelCsv;
	}

}
