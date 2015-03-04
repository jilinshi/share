package com.share.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.share.dao.BaseDAO;
import com.share.dto.SysLogDTO;
import com.share.model.SysLog;

@Service("sysMgrService")
public class SysMgrServiceImpl implements SysMgrService {

	@Resource
	private BaseDAO<SysLog> sysLogDAO;

	@Override
	public void saveSysLogs(SysLogDTO l) {
		SysLog o = new SysLog();
		o.setCtime(new Timestamp(new Date().getTime()));
		o.setIpaddr(l.getIpaddr());
		o.setLogInfo(l.getLogInfo());
		o.setUrl(l.getUrl());
		o.setUserId(l.getUserId());
		sysLogDAO.save(o);
	}

}
