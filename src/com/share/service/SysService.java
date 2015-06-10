package com.share.service;

import java.util.List;

import com.share.dto.DistrictsDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.RoleDTO;
import com.share.dto.UgrelationDTO;
import com.share.dto.UserDTO;
import com.share.dto.UsergroupDTO;
import com.share.model.SysUgrelation;
import com.share.util.Pager;

public interface SysService {
	public List<DistrictsDTO> querySYSDistrict(String hql, Object[] param);
	public void saveSYSDistrict(DistrictsDTO districtsDTO);
	public List<DistrictsDTO> querySYSDistrict(DistrictsDTO districtsDTO);
	public int updateSYSDistrict(String id);
	public List<OrganizationDTO> querySYSOrgs(String hql, Object[] param);
	public void saveSYSOrg(OrganizationDTO organizationDTO);
	public List<UserDTO> querySYSUsers(Pager pager,List<Object> param);
	public void saveSYSUser(UserDTO userDTO);
	public UserDTO querySYSUserById(Long userId);
	public void updateSYSUserById(Long userId);
	public List<UsergroupDTO> querySYSUserGroupAll();
	public List<UserDTO> querySYSUsers(List<Object> param);
	public void saveSYSUgrelation(List<SysUgrelation> list);
	public void saveSysUsergroup(String ugName);
	public void delSysUsergroup(String ugId);
	public List<RoleDTO> querySYSRoleAll();
	public void saveSysRole(String rolename);
	public void delSysRole(String roleId);
	public List<UserDTO> querySYSUserAll();
}
