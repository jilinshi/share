package com.share.service;

import java.util.List;

import com.share.dto.DistrictsDTO;

public interface SysService {
	public List<DistrictsDTO> querySYSDistrict(String hql, Object[] param);
	public void saveSYSDistrict(DistrictsDTO districtsDTO);
	public List<DistrictsDTO> querySYSDistrict(DistrictsDTO districtsDTO);
}
