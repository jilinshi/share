package com.share.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.share.dao.BaseDAO;
import com.share.dto.DistrictsDTO;
import com.share.dto.OrganizationDTO;
import com.share.model.SysDistrict;
import com.share.model.SysOrganization;
import com.share.util.PrimaryKey;
import com.share.util.StringFormat;

@Service("SysService")
public class SysServiceImpl implements SysService {

	@Resource
	private BaseDAO<SysDistrict> sysDistrictDAO;
	@Resource
	private BaseDAO<SysOrganization> sysOrganizationDAO;
	
	@Override
	public List<DistrictsDTO> querySYSDistrict(String hql, Object[] param) {
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
			e.setText(s.getDistrictsNmae());
			list.add(e);
		}
		return list;
	}
	
	public void saveSYSDistrict(DistrictsDTO districtsDTO){
		//查询
		String hql = " select sd from SysDistrict sd where 1=1 and sd.flag=? and sd.districtsId=? ";
		Object[] param = null;
		param = new Object[2];
		param[0] = "1";
		param[1] = districtsDTO.getDistrictsId();
		SysDistrict sd= sysDistrictDAO.find(hql, param).get(0);
		//
		String hql_n = " select sd from SysDistrict sd where 1=1 and sd.flag=? and sd.parentId=? order by sd.districtsId desc ";
		Object[] param_n = null;
		param_n = new Object[2];
		param_n[0] = "1";
		param_n[1] = districtsDTO.getDistrictsId();
		List<SysDistrict> sds= sysDistrictDAO.find(hql_n, param_n);
		if(sds.size()>0){
			SysDistrict s = sds.get(0);
			//保存机构表
			SysDistrict o = new SysDistrict();
			long id = Long.valueOf(s.getDistrictsId());
			o.setDistrictsId((id+1)+"");
			o.setDistrictsNmae(districtsDTO.getDistrictsNmae());
			o.setParentId(sd.getDistrictsId());
			o.setDistrictsCode(StringFormat.getformatting(o.getDistrictsId()));
			o.setFullname(sd.getFullname()+districtsDTO.getDistrictsNmae());
			o.setFlag("1");
			sysDistrictDAO.save(o);
		}else{
			SysDistrict o = new SysDistrict();
			o.setDistrictsId(sd.getDistrictsId()+"01");
			o.setDistrictsNmae(districtsDTO.getDistrictsNmae());
			o.setParentId(sd.getDistrictsId());
			o.setDistrictsCode(StringFormat.getformatting(o.getDistrictsId()));
			o.setFullname(sd.getFullname()+districtsDTO.getDistrictsNmae());
			o.setFlag("1");
			sysDistrictDAO.save(o);
		}
	}
	
	public List<DistrictsDTO> querySYSDistrict(DistrictsDTO districtsDTO){
		List<DistrictsDTO> ds = new ArrayList<DistrictsDTO>();
		String hql = " select sd from SysDistrict sd where 1=1 and sd.flag=? and sd.districtsNmae=? ";
		Object[] param = null;
		param = new Object[2];
		param[0] = "1";
		param[1] = districtsDTO.getDistrictsNmae();
		List<SysDistrict> sds= sysDistrictDAO.find(hql, param);
		for(SysDistrict e : sds){
			DistrictsDTO m = new DistrictsDTO();
			m.setDistrictsId(e.getDistrictsId());
			m.setDistrictsCode(e.getDistrictsCode());
			m.setDistrictsNmae(e.getDistrictsNmae());
			m.setFlag(e.getFlag());
			m.setFullname(e.getFullname());
			m.setParentId(e.getParentId());
			ds.add(m);
		}
		return ds;
	}
	
	public int updateSYSDistrict(String id){
		String hql = " select sd from SysDistrict sd where 1=1 and sd.flag=? and sd.parentId=? ";
		Object[] param = null;
		param = new Object[2];
		param[0] = "1";
		param[1] = id;
		List<SysDistrict> sds= sysDistrictDAO.find(hql, param);
		String hql_u = "";
		Object[] param_u = null;
		if(sds.size()>0){
			hql_u = " update SysDistrict sd set sd.flag=? where sd.districtsId=? or sd.parentId=? ";
			param_u = new Object[3];
			param_u[0] = "0";
			param_u[1] = id;
			param_u[2] = id;
		}else{
			hql_u = " update SysDistrict sd set sd.flag=? where sd.districtsId=? ";
			param_u = new Object[2];
			param_u[0] = "0";
			param_u[1] = id;
		}
		int u = sysDistrictDAO.update(hql_u, param_u);
		
		return u;
	}
	
	@Override
	public List<OrganizationDTO> querySYSOrgs(String hql, Object[] param) {
		List<OrganizationDTO> list = new ArrayList<OrganizationDTO>();
		List<SysOrganization> rs = sysOrganizationDAO.find(hql, param);
		for (SysOrganization s : rs) {
			OrganizationDTO e = new OrganizationDTO();
			e.setOrgId(s.getOrgId());
			e.setOrgCode(s.getOrgCode());
			e.setOrgName(s.getOrgName());
			e.setDistrictsId(s.getSysDistrict().getDistrictsId());
			e.setFlag(s.getFlag());
			e.setCtime(s.getCtime());
			e.setUtime(s.getUtime());
			e.setParentId(s.getParentId());
			list.add(e);
		}
		return list;
	}
	
	@Override
	public void saveSYSOrg(OrganizationDTO organizationDTO){
		//查询
		String hql = " select so from SysOrganization so where 1=1 and so.flag=? and so.parentId=? order by so.orgId desc";
		Object[] param = null;
		param = new Object[2];
		param[0] = "1";
		param[1] = organizationDTO.getOrgId()+"";
		List<SysOrganization> sos =  sysOrganizationDAO.find(hql, param);
		String preid="";
		String orgid="";
		String maxvalue="";
		String key="";
		SysOrganization o = new SysOrganization();
		SysDistrict s = new SysDistrict();
		if(sos.size()>0){
			SysOrganization so= sysOrganizationDAO.find(hql, param).get(0);
			//保存机构表
			preid = so.getParentId().toString();
			orgid = so.getOrgId()+"";
			maxvalue = orgid.substring(preid.length());
			key = PrimaryKey.nextKey(preid, maxvalue);
			
			o.setParentId(so.getParentId());
		}else{
			orgid = organizationDTO.getOrgId()+"";
			int prikey_len=orgid.length();
			if(prikey_len==2){
				maxvalue="00";
	        }else if(prikey_len==4){
	        	maxvalue="00";
	        }else if(prikey_len==6){
	        	maxvalue="000";
	        }else if(prikey_len==8||prikey_len==9){
	        	maxvalue="000";
	        }
			key =  PrimaryKey.nextKey(orgid, maxvalue);
			o.setParentId(orgid);
		}
		s.setDistrictsId(organizationDTO.getParentId());
		o.setOrgCode(StringFormat.getformatting12(key));
		o.setOrgName(organizationDTO.getOrgName());
		o.setSysDistrict(s);
		o.setOrgId(Long.valueOf(key));
		o.setFlag("1");
		sysOrganizationDAO.save(o);
	}
}
