package com.share.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.share.dao.BaseDAO;
import com.share.dto.FileDTO;
import com.share.model.Impdatainfo;
import com.share.model.SysFile;

@Service("impService")
public class ImpServiceImpl implements ImpService {
	private static Logger log = LoggerFactory.getLogger(ImpServiceImpl.class);
	@Resource
	private BaseDAO<SysFile> sysFileDAO;
	@Resource
	private BaseDAO<Impdatainfo> impdatainfoDAO;

	@Override
	public FileDTO saveFileinfo(FileDTO fileDTO) {
		SysFile sysFile = new SysFile();
		sysFile.setFilename(fileDTO.getFilename());
		sysFile.setRealpath(fileDTO.getRealpath());
		sysFile.setRealname(fileDTO.getRealname());
		sysFile.setFtype(fileDTO.getFtype());
		sysFile.setRemark(fileDTO.getRemark());
		sysFile.setFlag(fileDTO.getFlag());
		sysFile.setUptime(fileDTO.getOpertime());
		sysFileDAO.save(sysFile);
		log.info("上传一个文件："+sysFile.getFileId());
		fileDTO.setFileId(new BigDecimal(sysFile.getFileId()));
		return fileDTO;
	}

	@Override
	public FileDTO removedFileinfo(FileDTO fileDTO) {
		return null;
	}

}
