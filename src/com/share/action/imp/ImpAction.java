package com.share.action.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import com.share.dto.InsuranceDTO;
import com.share.dto.UserDTO;
import com.share.model.VImpfile;
import com.share.service.ImpService;
import com.share.util.Pager;

@Controller
public class ImpAction extends ActionSupport {

	private static Logger log = LoggerFactory.getLogger(ImpAction.class);

	@Resource
	private ImpService impService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3212567422348914692L;

	private File[] ec; // 上传的文件
	private String[] ecFileName; // 文件名称
	private String[] ecContentType; // 文件类型

	private File single; // 上传的文件
	private String singleFileName; // 文件名称
	private String singleContentType; // 文件类型

	private FileDTO fileDTO;

	/** 当前页面 */
	private String page;
	/** 每页的记录数 */
	private String rows;

	@SuppressWarnings("rawtypes")
	private Map map;// 返回的json

	private static final String savepath = "C:\\uploadfiles\\";

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
	 * 多文件上传 input type=file name=ec
	 * 
	 * @return
	 */
	public String uploadmulti() {
		try {
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");

			// 取得需要上传的文件数组
			File[] files = this.getEc();
			System.out.println(files.toString());
			System.out.println(files.length);
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					// 建立上传文件的输出流, getImageFileName()[i]

					FileOutputStream fos = new FileOutputStream(savepath + ""
							+ this.generateFileName(this.getEcFileName()[i]));
					// 建立上传文件的输入流
					FileInputStream fis = new FileInputStream(files[i]);
					System.out.println(files[i].getPath());
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					fis.close();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public String addfile() {
		log.info("begin>>>>>>>>>>保存上传文件信息数据");
		UserDTO userDTO = (UserDTO) ActionContext.getContext().getSession()
				.get("user");
		if (null != userDTO) {
			fileDTO.setOpertime(new Timestamp(new Date().getTime()));
			fileDTO.setOperstat("处理中");
			// 有效记录
			fileDTO.setFlag("1");
			fileDTO.setFileId(null);
			fileDTO.setFilename(fileDTO.getDisplayname());
			fileDTO = impService.saveFileinfo(fileDTO, userDTO);

			Map jsonMap = new HashMap();
			jsonMap.put("fileid", fileDTO.getFileId().toString());// total键
			jsonMap.put("realname", fileDTO.getRealname());
			jsonMap.put("realpath", fileDTO.getRealpath());
			jsonMap.put("displayname", fileDTO.getRealpath());
			map = jsonMap;
		}
		log.info("end>>>>>>>>>>保存上传文件信息数据");
		return SUCCESS;
	}

	/**
	 * 查询上传文件列表 操作导入数据
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryFiles() {
		Pager pager = new Pager(page, rows, new Long(0));
		Object[] param =new Object[1]   ;
		param[0]=this.fileDTO.getFtype();
		List<VImpfile> rs = impService.queryFiles(pager, param);
		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}

	/**
	 * 查询单个文件 以表格方式显示
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryFiletoGrid() {
		List<InsuranceDTO> rs = impService.queryFiletoData(fileDTO);
		Map jsonMap = new HashMap();
		jsonMap.put("rows", rs);
		map = jsonMap;
		return SUCCESS;
	}

	/**
	 * 删除上传文件和文件所产生所有记录
	 * 
	 * @return
	 */
	public String removeUpFiles() {
		impService.removedFileinfo(fileDTO);
		return SUCCESS;
	}

	/**
	 * 导入数据
	 * 
	 * @return
	 */
	public String impInfos() {

		impService.saveFileGrid(fileDTO);
		return SUCCESS;
	}

	public File[] getEc() {
		return ec;
	}

	public void setEc(File[] ec) {
		this.ec = ec;
	}

	public String[] getEcFileName() {
		return ecFileName;
	}

	public void setEcFileName(String[] ecFileName) {
		this.ecFileName = ecFileName;
	}

	public String[] getEcContentType() {
		return ecContentType;
	}

	public void setEcContentType(String[] ecContentType) {
		this.ecContentType = ecContentType;
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

}
