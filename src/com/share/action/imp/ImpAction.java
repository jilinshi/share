package com.share.action.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.share.service.YbService;

@Controller
public class ImpAction extends ActionSupport {
	@Resource
	private YbService ybjkService;
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

	private String uptype;
	
	private String realname;
	private String realpath;
	private String fileid;

	@SuppressWarnings("rawtypes")
	private Map map;// 返回的json

	private static final String savepath = "C:\\uploadfiles\\";

	/**
	 * 单文件上传 input type=file name=single 循环逐个上传
	 * 
	 * @return
	 */
	public String upload() {
		
		String displayname="";
		String realname="";
		String realpath="";
		
		try {
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			// 取得需要上传的文件数组
			File files = this.getSingle();
			// 建立上传文件的输出流, getImageFileName()[i]
			displayname=this.getSingleFileName();
			realname=this.generateFileName(displayname);
			realpath =savepath + "" + realname;
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
			jsonMap.put("fileid", "111");// total键
			jsonMap.put("realname", realname);
			jsonMap.put("realpath", realpath);
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
		System.out.println("1111");
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
		System.out.println("2222");
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
	
	public String removedfile(){
		System.out.println(realpath);
		File file =new File(realpath);
		file.delete();
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

	public String getUptype() {
		return uptype;
	}

	public void setUptype(String uptype) {
		this.uptype = uptype;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRealpath() {
		return realpath;
	}

	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	

}
