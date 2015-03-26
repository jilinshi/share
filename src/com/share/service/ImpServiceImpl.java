package com.share.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.share.dao.BaseDAO;
import com.share.dto.BurialDTO;
import com.share.dto.FileDTO;
import com.share.dto.FundDTO;
import com.share.dto.HousepropertyDTO;
import com.share.dto.InsuranceDTO;
import com.share.dto.UserDTO;
import com.share.dto.VehicleDTO;
import com.share.model.CkInsurance;
import com.share.model.Impdatainfo;
import com.share.model.SysFile;
import com.share.model.VImpfile;
import com.share.util.CreatAndReadExcel;
import com.share.util.IDCard;

@Service("impService")
public class ImpServiceImpl<T> implements ImpService {
	private static Logger log = LoggerFactory.getLogger(ImpServiceImpl.class);
	@Resource
	private BaseDAO<SysFile> sysFileDAO;
	@Resource
	private BaseDAO<Impdatainfo> impdatainfoDAO;
	@Resource
	private BaseDAO<VImpfile> vImpfileDAO;
	@Resource
	private BaseDAO<CkInsurance> ckInsuranceDAO;

	private Impdatainfo impdatainfo;

	public Impdatainfo getImpdatainfo() {
		return impdatainfo;
	}

	public void setImpdatainfo(Impdatainfo impdatainfo) {
		this.impdatainfo = impdatainfo;
	}

	@Override
	public FileDTO saveFileinfo(FileDTO fileDTO, UserDTO userDTO) {
		SysFile sysFile = new SysFile();
		sysFile.setFilename(fileDTO.getFilename());
		sysFile.setRealpath(fileDTO.getRealpath());
		sysFile.setRealname(fileDTO.getRealname());
		sysFile.setFtype(fileDTO.getFtype());
		sysFile.setRemark(fileDTO.getRemark());
		sysFile.setFlag(fileDTO.getFlag());
		sysFile.setUptime(fileDTO.getOpertime());
		sysFileDAO.save(sysFile);
		log.info("上传一个文件：" + sysFile.getFileId());
		fileDTO.setFileId(new BigDecimal(sysFile.getFileId()));

		Impdatainfo o = new Impdatainfo();
		o.setFilename(fileDTO.getFilename());
		o.setFileId(fileDTO.getFileId());
		o.setOperstat("0");
		o.setRealpath(fileDTO.getRealpath());
		o.setUserId(new BigDecimal(userDTO.getUserId()));
		o.setOpertime(fileDTO.getOpertime());
		impdatainfoDAO.save(o);
		return fileDTO;
	}

	@Override
	public FileDTO removedFileinfo(FileDTO fileDTO) {
		return null;
	}

	@Override
	public List<VImpfile> queryFiles() {
		String hql = "select t from VImpfile t";
		List<VImpfile> rs = vImpfileDAO.find(hql);
		return rs;
	}

	/**
	 * 处理excel数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryFiletoData(FileDTO fileDTO) {
		List<T> list = new ArrayList<T>();
		try {
			if (null != fileDTO) {
				File file = new File(fileDTO.getRealpath());
				if (file.exists()) {
					List<List<Object>> rs = CreatAndReadExcel.readExcel(file);

					switch (fileDTO.getFtype()) {
					case "INSURANCE": // 社保
						list = (List<T>) convert1(rs);
						break;
					case "FUND": // 公积金
						list = (List<T>) convert2(rs);
						break;
					case "BURIAL": // 殡葬
						list = (List<T>) convert3(rs);
						break;
					case "HOUSEPROPERTY": // 房产
						list = (List<T>) convert4(rs);
						break;
					case "VEHICLE": // 车辆
						list = (List<T>) convert5(rs);
						break;
					default:
						System.out.println("default");
						break;
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<VehicleDTO> convert5(List<List<Object>> rs) {
		return null;
	}

	private List<HousepropertyDTO> convert4(List<List<Object>> rs) {
		return null;
	}

	private List<BurialDTO> convert3(List<List<Object>> rs) {
		return null;
	}

	private List<FundDTO> convert2(List<List<Object>> rs) {
		return null;
	}

	private List<InsuranceDTO> convert1(List<List<Object>> rs) {

		List<InsuranceDTO> g = new ArrayList<InsuranceDTO>();
		try {
			for (int i = 0; i < rs.size(); i++) {
				List<Object> row = rs.get(i);
				int cls = row.size();
				if (cls > 2) {
					String idno = (String) row.get(2);

					String iderr;

					iderr = IDCard.IDCardValidate(idno);

					if (null == iderr || "".equals(iderr)) {

						// String IN_ID = (String) row.get(0);
						// 22020108060600 郭玉芝 220202196408143649
						// 昌邑 哈达湾 炭素 郭玉芝 220202196408143649 集体续保昌邑 2014-8-18
						// 00:00:00 1418.80
						String FNO = (String) row.get(0);
						String IDNO = (String) row.get(2);
						String PNAME = (String) row.get(1);
						String OO1 = (String) row.get(3);
						String OO2 = (String) row.get(4);
						String OO3 = (String) row.get(5);
						String SBIDNO = (String) row.get(7);
						String SBNAME = (String) row.get(6);
						String COMP = (String) row.get(8);
						String TXTIME = (String) row.get(9);
						String TXMONEY = (String) row.get(10);
						InsuranceDTO e = new InsuranceDTO();
						e.setFno(FNO);
						e.setIdno(IDNO);
						e.setPname(PNAME);
						e.setOo1(OO1);
						e.setOo2(OO2);
						e.setOo3(OO3);
						e.setSbidno(SBIDNO);
						e.setSbname(SBNAME);
						e.setComp(COMP);
						e.setTxmoney(TXMONEY);
						e.setTxtime(TXTIME);
						g.add(e);
					}
				}

			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return g;
	}

	@Override
	public void saveFileGrid(FileDTO fileDTO) {

		Object[] param = new Object[1];
		param[0] = fileDTO.getFileId();
		Impdatainfo l = impdatainfoDAO.get(
				"select e from Impdatainfo e where e.fileId=?", param);
		setImpdatainfo(l);
		fileDTO.setRealpath(l.getRealpath());
		try {
			if (null != fileDTO) {
				File file = new File(fileDTO.getRealpath());
				if (file.exists()) {
					List<List<Object>> rs = CreatAndReadExcel.readExcel(file);

					switch (fileDTO.getFtype()) {
					case "INSURANCE": // 社保
						savedata1(rs);
						break;
					case "FUND": // 公积金
						savedata2(rs);
						break;
					case "BURIAL": // 殡葬
						savedata3(rs);
						break;
					case "HOUSEPROPERTY": // 房产
						savedata4(rs);
						break;
					case "VEHICLE": // 车辆
						savedata5(rs);
						break;
					default:
						System.out.println("default");
						break;
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private List<VehicleDTO> savedata5(List<List<Object>> rs) {

		return null;
	}

	private List<HousepropertyDTO> savedata4(List<List<Object>> rs) {
		return null;
	}

	private List<BurialDTO> savedata3(List<List<Object>> rs) {
		return null;
	}

	private List<FundDTO> savedata2(List<List<Object>> rs) {
		return null;
	}

	private List<InsuranceDTO> savedata1(List<List<Object>> rs) {

		ckInsuranceDAO.executeHql("delete CkInsurance t where t.impdatainfo='"
				+ this.getImpdatainfo().getInfoId() + "' ", null);
		List<InsuranceDTO> g = this.convert1(rs);
		List<CkInsurance> r = new ArrayList<CkInsurance>();
		for (InsuranceDTO s : g) {
			CkInsurance e = new CkInsurance();
			BeanUtils.copyProperties(s, e);
			e.setImpdatainfo(this.getImpdatainfo());
			r.add(e);
		}
		ckInsuranceDAO.saveBatch(r);
		return g;
	}

}
