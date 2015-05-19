package com.share.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.share.dao.BaseDAO;
import com.share.dto.DistrictsDTO;
import com.share.model.SysDistrict;
import com.share.util.StringFormat;

@Service("SysService")
public class SysServiceImpl implements SysService {

	@Resource
	private BaseDAO<SysDistrict> sysDistrictDAO;
	
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
}
