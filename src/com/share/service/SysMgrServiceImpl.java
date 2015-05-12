package com.share.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.share.dao.BaseDAO;
import com.share.dto.DistrictsDTO;
import com.share.dto.SysLogDTO;
import com.share.model.SysDistrict;
import com.share.model.SysLog;

@Service("sysMgrService")
public class SysMgrServiceImpl implements SysMgrService {

	@Resource
	private BaseDAO<SysLog> sysLogDAO;
	@SuppressWarnings("rawtypes")
	@Resource
	private BaseDAO<HashMap> baseDAO;

	@Resource
	private BaseDAO<SysDistrict> sysDistrictDAO;

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
	public List<HashMap> queryData(String hql, Object[] param) {
		List<HashMap> rs = baseDAO.find(hql, param);
		return rs;
	}

	@Override
	public List<DistrictsDTO> queryDistricts(String hql, Object[] param) {
		List<DistrictsDTO> list = new ArrayList<DistrictsDTO>();
		List<SysDistrict> rs = sysDistrictDAO.find(hql, param);
		for (SysDistrict s : rs) {
			DistrictsDTO e = new DistrictsDTO();
			e.setCol1(s.getCol1());
			e.setCol2(s.getCol2());
			e.setCol3(s.getCol3());
			e.setDistrictsCode(s.getDistrictsCode());
			e.setDistrictsId(s.getDistrictsId());
			e.setDistrictsNmae(s.getDistrictsNmae());
			e.setFlag(s.getFlag());
			e.setFullname(s.getFullname());
			e.setOrd(s.getOrd());
			e.setParentId(s.getParentId());
			//System.out.println(s.getSysOrganizations().size());
			list.add(e);
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<HashMap> queryData(String hql, List<Object> param) {
		List<HashMap> rs = baseDAO.find(hql, param);
		return rs;
	}

}
