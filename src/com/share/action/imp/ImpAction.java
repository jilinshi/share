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
import com.share.model.PpInsurance;
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

	private File[] ec; // �ϴ����ļ�
	private String[] ecFileName; // �ļ�����
	private String[] ecContentType; // �ļ�����

	private File single; // �ϴ����ļ�
	private String singleFileName; // �ļ�����
	private String singleContentType; // �ļ�����

	private FileDTO fileDTO;

	/** ��ǰҳ�� */
	private String page;
	/** ÿҳ�ļ�¼�� */
	private String rows;

	private String imptype;

	private String impkind;

	private String pagetype;

	@SuppressWarnings("rawtypes")
	private Map map;// ���ص�json

	private static final String savepath = "C:\\uploadfiles\\";

	/**
	 * ���ļ��ϴ� input type=file name=single ѭ������ϴ�
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
			// ȡ����Ҫ�ϴ����ļ�����
			File files = this.getSingle();
			// �����ϴ��ļ��������, getImageFileName()[i]
			displayname = this.getSingleFileName();
			realname = this.generateFileName(displayname);
			realpath = savepath + "" + realname;
			FileOutputStream fos = new FileOutputStream(realpath);
			// �����ϴ��ļ���������
			FileInputStream fis = new FileInputStream(files);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();

			Map jsonMap = new HashMap();
			jsonMap.put("fileid", "-1");// total��
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

	public String upload1() {

		String displayname = "";
		String realname = "";
		String realpath = "";

		try {
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			// ȡ����Ҫ�ϴ����ļ�����
			File files = this.getSingle();
			// �����ϴ��ļ��������, getImageFileName()[i]
			displayname = this.getSingleFileName();
			realname = this.generateFileName(displayname);
			realpath = savepath + "" + realname;
			FileOutputStream fos = new FileOutputStream(realpath);
			// �����ϴ��ļ���������
			FileInputStream fis = new FileInputStream(files);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();

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
	 * ���ļ��ϴ� input type=file name=ec
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String uploadmulti() {
		try {
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");

			// ȡ����Ҫ�ϴ����ļ�����
			File[] files = this.getEc();
			System.out.println(files.toString());
			System.out.println(files.length);
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					// �����ϴ��ļ��������, getImageFileName()[i]

					FileOutputStream fos = new FileOutputStream(savepath + ""
							+ this.generateFileName(this.getEcFileName()[i]));
					// �����ϴ��ļ���������
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

			Map jsonMap = new HashMap();
			jsonMap.put("fileid", "-1");// total
			jsonMap.put("status", "OK");// total
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

	@SuppressWarnings("rawtypes")
	public Map getMap() {
		return map;
	}

	@SuppressWarnings("rawtypes")
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * �����ں��������ʽ���ļ��������ͻ
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
		log.info("begin>>>>>>>>>>�����ϴ��ļ���Ϣ����");
		UserDTO userDTO = (UserDTO) ActionContext.getContext().getSession()
				.get("user");
		if (null != userDTO) {
			fileDTO.setOpertime(new Timestamp(new Date().getTime()));
			fileDTO.setOperstat("������");
			// ��Ч��¼
			fileDTO.setFlag("1");
			fileDTO.setFileId(null);
			fileDTO.setFilename(fileDTO.getDisplayname());
			fileDTO = impService.saveFileinfo(fileDTO, userDTO);

			Map jsonMap = new HashMap();
			jsonMap.put("fileid", fileDTO.getFileId().toString());// total��
			jsonMap.put("realname", fileDTO.getRealname());
			jsonMap.put("realpath", fileDTO.getRealpath());
			jsonMap.put("displayname", fileDTO.getRealpath());
			map = jsonMap;
		}
		log.info("end>>>>>>>>>>�����ϴ��ļ���Ϣ����");
		return SUCCESS;
	}

	/**
	 * ��ѯ�ϴ��ļ��б� ������������
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryFiles() {
		Pager pager = new Pager(page, rows, new Long(0));
		Object[] param = new Object[1];
		param[0] = this.fileDTO.getFtype();
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
	 * ��ѯ�����ļ� �Ա��ʽ��ʾ
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
	 * ɾ���ϴ��ļ����ļ����������м�¼
	 * 
	 * @return
	 */
	public String removeUpFiles() {
		impService.removedFileinfo(fileDTO);
		return SUCCESS;
	}

	/**
	 * ��������
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String impInfos() {
		log.info("begin:" + System.currentTimeMillis() + ">>>>>�ļ�·����"
				+ fileDTO.getRealpath() + ";�ļ����ͣ�" + fileDTO.getFtype());
		String r = impService.saveFileGrid(fileDTO);

		Map jsonMap = new HashMap();
		jsonMap.put("info", r);
		map = jsonMap;

		log.info("end:" + System.currentTimeMillis() + "�ļ�·����"
				+ fileDTO.getRealpath() + ";�ļ����ͣ�" + fileDTO.getFtype());
		return SUCCESS;
	}

	/**
	 * ��ѯ����
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryCheckData() {
		log.info("begin>>>>>>>>>>queryCheckData.action");
		String hql = "";
		String temphql = "";
		Object[] param = null;
		Pager pager = new Pager(page, rows, new Long(0));

		if (null == imptype || "".equals(imptype)) {
			imptype = "1";

		}
		if (null == impkind || "".equals(impkind)) {
			impkind = "1";

		}
		if (impkind.equals("1")) {
			switch (this.getPagetype()) {
			case "PpBurial":
				temphql = " and t.hhtime is not null";
				break;
			case "PpInsurance":
				temphql = " and t.lqMoney>0";
				break;
			default:
				temphql = "";
				break;
			}

		} else {

		}

		if ("1".equals(imptype)) {
			param = new Object[1];
			param[0] = "��¼��";
			hql = "select t from " + this.getPagetype() + " t where 1=1 "
					+ temphql + " and t.remark=?";
		}
		if ("2".equals(imptype)) {

			hql = "select t from " + this.getPagetype() + " t where 1=1 "
					+ temphql + " and t.remark in ('���Ͻ����','ͣ���ָ�') ";
		}
		if ("3".equals(imptype)) {
			param = new Object[1];
			param[0] = "�ڱ���";
			hql = "select t from " + this.getPagetype() + " t where  1=1 "
					+ temphql + " and t.col3=?";
		}
		if ("4".equals(imptype)) {
			param = new Object[1];
			param[0] = "�ڱ���";
			hql = "select t from " + this.getPagetype() + " t where  1=1 "
					+ temphql + " and t.col7=?";
		}
		if ("5".equals(imptype)) {
			param = new Object[1];
			param[0] = "�ڱ���";
			hql = "select t from " + this.getPagetype() + " t where  1=1 "
					+ temphql + " and t.col4=?";
		}
		if ("6".equals(imptype)) {
			param = new Object[1];
			param[0] = "�ڱ���";
			hql = "select t from " + this.getPagetype() + " t where  1=1 "
					+ temphql + " and t.col6=?";
		}
		if ("-1".equals(imptype)) {

			hql = "select t from " + this.getPagetype() + " t where  1=1 "
					+ temphql + " and 1=1";
		}
		List rs = impService.queryCheckData(pager, hql, param,
				PpInsurance.class);

		Map jsonMap = new HashMap();
		jsonMap.put("page", page);
		jsonMap.put("total", pager.total);
		jsonMap.put("records", pager.records);
		jsonMap.put("rows", rs);
		map = jsonMap;
		log.info("end>>>>>>>>>>queryCheckData.action");
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

	public String getImptype() {
		return imptype;
	}

	public void setImptype(String imptype) {
		this.imptype = imptype;
	}

	public String getImpkind() {
		return impkind;
	}

	public void setImpkind(String impkind) {
		this.impkind = impkind;
	}

	public String getPagetype() {
		return pagetype;
	}

	public void setPagetype(String pagetype) {
		this.pagetype = pagetype;
	}

}
