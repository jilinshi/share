package com.share.service;

import java.util.HashMap;
import java.util.List;

import com.share.dto.DistrictsDTO;
import com.share.dto.SysLogDTO;

public interface SysMgrService {
	public void saveSysLogs(SysLogDTO l);

	public List<DistrictsDTO> queryDistricts(String hql, Object[] param);

	@SuppressWarnings("rawtypes")
	public List<HashMap> queryData(String hql, Object[] param);

	@SuppressWarnings("rawtypes")
	public List<HashMap> queryData(String hql, List<Object> param);
}
