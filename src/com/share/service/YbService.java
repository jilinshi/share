package com.share.service;

import java.util.List;

import com.share.dto.BillCsDTO;
import com.share.dto.BillNcDTO;
import com.share.dto.BurialDTO;
import com.share.dto.FundDTO;
import com.share.dto.HousepropertyDTO;
import com.share.dto.InsuranceDTO;
import com.share.dto.MemberDTO;
import com.share.dto.MenuDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.UserDTO;
import com.share.dto.VehicleDTO;
import com.share.model.VCkburial;
import com.share.model.VCkfund;
import com.share.model.VCkhouseproperty;
import com.share.model.VCkinsurance;
import com.share.model.VCkvehicle;
import com.share.util.Pager;

public interface YbService {
	public UserDTO findUser(UserDTO userDTO);

	public List<MemberDTO> finMembers(String sql, List<Object> param);

	public String finMembersCount(String sql, List<Object> param);

	public List<OrganizationDTO> findOrganlist(String onno);
	
	public MemberDTO findMemeber(MemberDTO memberDTO);
	
	public List<BillCsDTO> findBillCs(BillCsDTO billCsDTO);
	
	public List<BillNcDTO> findBillNc(BillNcDTO billNcDTO);
	
	public int updateMember(MemberDTO memberDTO);
	
	public List<BillCsDTO> findALLBillCsByFNO(BillCsDTO billCsDTO);
	
	public List<VCkinsurance> queryCkInsuranceById(InsuranceDTO insuranceDTO);
	
	public List<BillNcDTO> findALLBillNcByFNO(BillNcDTO billNcDTO);
	
	public List<MenuDTO> findMenusByUser(UserDTO userDTO);
	
	public List<UserDTO> finMemberstest(String sql, List<Object> param);
	
	public List<VCkinsurance> queryCkInsurances(Pager pager, List<Object> param , String jwhere);
	
	public List<VCkhouseproperty> queryCkHousepropertys(Pager pager, List<Object> param , String jwhere);
	
	public List<VCkhouseproperty> queryCkhousepropertyById(HousepropertyDTO housepropertyDTO);
	
	public List<VCkvehicle> queryCkvehicles(Pager pager, List<Object> param , String jwhere);
	
	public List<VCkvehicle> queryCkvehicleById(VehicleDTO vehicleDTO);
	
	public List<VCkburial> queryCkburials(Pager pager, List<Object> param , String jwhere);
	
	public List<VCkburial> queryCkburialById(BurialDTO burialDTO);
	
	public List<VCkfund> queryCkfunds(Pager pager, List<Object> param , String jwhere);
	
	public List<VCkfund> queryCkfundById(FundDTO fundDTO);
}
