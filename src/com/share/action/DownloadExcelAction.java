package com.share.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.share.dao.BaseDAO;
import com.share.service.SysMgrService;
import com.share.util.ExportExcel;

@Controller
@SuppressWarnings("all")
public class DownloadExcelAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(DownloadExcelAction.class);
	private String sql;
	private String hql;
	private List<Object> param;
	private LinkedHashMap<String, String> title = new LinkedHashMap<String, String>();
	private String fileName;
	private InputStream excelFile;
	
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
		title =(LinkedHashMap<String, String>) session.get("title");
		sql = (String) session.get("sql");
		hql = (String) session.get("hql");
		param = (List<Object>) session.get("param");
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		String nowdate = bartDateFormat.format(new Date());
		
		if(fileName == null ||"".equals(fileName)){
			fileName = "导出EXCEL" ;
		}
		//String fn = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
		String fn = fileName;
		String f = new String((fn+"("+nowdate+")").getBytes("gb2312"), "ISO8859-1");
		fileName = "attachment; filename=" + f + ".xls";
		return super.execute();
	}

	@SuppressWarnings("rawtypes")
	public InputStream getExcelFile() {
		List<HashMap> rs = sysMgrService.queryData(hql, param);
		ByteArrayInputStream bais = null;
		ExportExcel ee = new ExportExcel();
		ByteArrayOutputStream baos = ee.genExcelData(title, rs); //参数为  题目，结果，excel卷标
		if (null != baos) {
			byte[] ba = baos.toByteArray();
			bais = new ByteArrayInputStream(ba);
		}
		return bais;
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



}
