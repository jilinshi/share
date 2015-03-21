package com.share.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.share.dao.BaseDAO;
import com.share.dto.FileDTO;
import com.share.dto.InsuranceDTO;
import com.share.dto.UserDTO;
import com.share.model.Impdatainfo;
import com.share.model.SysFile;
import com.share.model.VImpfile;
import com.share.util.CreatAndReadExcel;

@Service("impService")
public class ImpServiceImpl<T> implements ImpService {
	private static Logger log = LoggerFactory.getLogger(ImpServiceImpl.class);
	@Resource
	private BaseDAO<SysFile> sysFileDAO;
	@Resource
	private BaseDAO<Impdatainfo> impdatainfoDAO;
	@Resource
	private BaseDAO<VImpfile> vImpfileDAO;

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

	private List<T> convert5(List<List<Object>> rs) {
		return null;
	}

	private List<T> convert4(List<List<Object>> rs) {
		return null;
	}

	private List<T> convert3(List<List<Object>> rs) {
		return null;
	}

	private List<T> convert2(List<List<Object>> rs) {
		return null;
	}

	private List<InsuranceDTO> convert1(List<List<Object>> rs) {
		List<InsuranceDTO> g = new ArrayList<InsuranceDTO>();
		for (int i = 0; i < rs.size(); i++) {
			
			List<Object> row = rs.get(i);
			System.out.println(row.size());
			/*if (null == row.get(2) || "".equals(row.get(2))) {

			} else {
				String idno = (String) row.get(2);
				InsuranceDTO e = new InsuranceDTO();
				e.setIdno(idno);
				g.add(e);
			}*/

		}

		return g;
	}

}
