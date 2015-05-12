package com.share.service;

import java.util.List;

import com.share.dto.AttorneyrecordDTO;
import com.share.dto.MemberDTO;
import com.share.dto.OrganizationDTO;
import com.share.model.Personalinfo;
import com.share.model.VCkburial;
import com.share.model.VCkfund;
import com.share.model.VCkhouseproperty;
import com.share.model.VCkinsurance;
import com.share.model.VCkvehicle;
import com.share.util.Pager;

public interface ReportService {

	public List<Personalinfo> queryPersonalinfos(Pager pager,
			List<Object> param, String jwhere);

	public List<OrganizationDTO> findOrganlist(long orgid);

	public List<Personalinfo> findPersonsByMAID(String masterid);
	
	public List<VCkinsurance> findInsuranceByMAID(String masterid);
	public List<VCkfund> findFundByMAID(String masterid);
	public List<VCkhouseproperty> findHousepropertyByMAID(String masterid);
	public List<VCkvehicle> findVehicleByMAID(String masterid);
	public List<VCkburial> findBurialByMAID(String masterid);
	public Long getPcountByFNO(String familyno);
	public List<MemberDTO> getPersonsByFNO(String familyno);
	public void saveAttorneyRecord(AttorneyrecordDTO ardto);
	public Long queryAttorneyRecordCount(AttorneyrecordDTO ardto);
	public List<Personalinfo> queryPersonalinfoAll(Pager pager,
			List<Object> param, String jwhere);
}
