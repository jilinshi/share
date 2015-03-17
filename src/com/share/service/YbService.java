package com.share.service;

import java.util.List;

import com.share.dto.BillCsDTO;
import com.share.dto.BillNcDTO;
import com.share.dto.MemberDTO;
import com.share.dto.MenuDTO;
import com.share.dto.OrganizationDTO;
import com.share.dto.UserDTO;

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
	
	public List<BillNcDTO> findALLBillNcByFNO(BillNcDTO billNcDTO);
	
	public List<MenuDTO> findMenusByUser(UserDTO userDTO);
	
	public List<UserDTO> finMemberstest(String sql, List<Object> param);
}
