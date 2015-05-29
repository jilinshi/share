package com.share.service;

import java.util.List;

import com.share.dto.DistrictsDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.UserDTO;
import com.share.util.Pager;

public interface SysService {
	public List<DistrictsDTO> querySYSDistrict(String hql, Object[] param);
	public void saveSYSDistrict(DistrictsDTO districtsDTO);
	public List<DistrictsDTO> querySYSDistrict(DistrictsDTO districtsDTO);
	public int updateSYSDistrict(String id);
	public List<OrganizationDTO> querySYSOrgs(String hql, Object[] param);
	public void saveSYSOrg(OrganizationDTO organizationDTO);
	public List<UserDTO> querySYSUsers(Pager pager,List<Object> param);
}
