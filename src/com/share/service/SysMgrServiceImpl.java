package com.share.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.share.dao.BaseDAO;
import com.share.dto.SysLogDTO;
import com.share.model.SysLog;
import com.share.model.SysMenus;

@Service("sysMgrService")
public class SysMgrServiceImpl implements SysMgrService {

	@Resource
	private BaseDAO<SysLog> sysLogDAO;
	@SuppressWarnings("rawtypes")
	@Resource
	private BaseDAO<HashMap> baseDAO;

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
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<HashMap> queryData(String hql,List<Object> param){
		List<HashMap> rs = baseDAO.find(hql, param);
		return rs;
	}
	
	 
	@SuppressWarnings("rawtypes")
	@Override
	public List<HashMap> queryData(String hql,Object[] param){
		List<HashMap> rs = baseDAO.find(hql, param);
		return rs;
	}
	
}
