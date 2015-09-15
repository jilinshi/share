package com.share.action.check;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.BillCsDTO;
import com.share.dto.BillNcDTO;
import com.share.dto.MemberDTO;
import com.share.dto.OrganizationDTO;
import com.share.service.YbService;
import com.share.util.DateJsonValueProcessor;
import com.share.util.Pager;

@Controller
@Scope(BeanDefinition.SCOPE_PROTOTYPE) 
public class CheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(CheckAction.class);
	@Resource
	private YbService ybjkService;

	private JSONObject result;// ���ص�json

	@SuppressWarnings("rawtypes")
	private Map map;// ���ص�json

	private String rows;// ÿҳ��ʾ�ļ�¼��

	private String page;// ��ǰ�ڼ�ҳ

	private MemberDTO memberDTO;

	private MemberDTO mdto;

	private List<OrganizationDTO> orgs;

	private String key;

	private String u_result;

	public String queryMemberInfoInit() {
		/*log.info("������Ա��Ϣ�˶�ҳ��---begin---queryMemberInfoInit");
		Map session = ActionContext.getContext().getSession();
		UserDTO user = (UserDTO) session.get("user");
		//String orgno = user.getOrganizationId();
		orgs = ybjkService.findOrganlist(orgno);
		log.info("������Ա��Ϣ�˶�ҳ��---end---queryMemberInfoInit");*/
		return SUCCESS;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryMemberInfo() {
		log.info("������Ա��Ϣ�˶�ҳ��---begin---queryMemberInfo");
		Pager pager = new Pager(page, rows);
		int start = pager.start;
		int end = pager.end;
		List<Object> param = new ArrayList<Object>();
		String sqlwhere = "";
		String orderwhere = " order by t.area";

		if (null != memberDTO) {

			sqlwhere = " and t.area like ?";
			param.add(memberDTO.getOnNo() + "%");

			if (!"".equals(memberDTO.getFamilyno())) {
				sqlwhere = sqlwhere + " and t.familyno=? ";
				param.add(memberDTO.getFamilyno());

			}
			if (!"".equals(memberDTO.getSsn())) {
				sqlwhere = sqlwhere + " and t.ssn=? ";
				param.add(memberDTO.getSsn());

			}
			if (!"".equals(memberDTO.getMembername())) {
				sqlwhere = sqlwhere + " and t.membername like ? ";
				param.add("%" + memberDTO.getMembername() + "%");

			}
			if (!"".equals(memberDTO.getPaperid())) {
				sqlwhere = sqlwhere + " and t.paperid=? ";
				param.add(memberDTO.getPaperid());
			}

		}
		param.add(end);
		param.add(start);
		String sql = " SELECT *   FROM (SELECT a.*, ROWNUM rn FROM ( SELECT t.member_id,t.ds,t.familyno,t.membername,t.paperid,t.ssn,t.personstate,t.assist_type,t.asort "
				+ " FROM member_baseinfo t where 1=1 "
				+ sqlwhere
				+ orderwhere
				+ " ) a WHERE ROWNUM <= ?)  WHERE rn >= ?";

		String sqlcount = " SELECT count(1) as cnt FROM member_baseinfo t  where 1=1 "
				+ sqlwhere;

		List<MemberDTO> list = ybjkService.finMembers(sql, param);

		param.remove(param.size() - 1);
		param.remove(param.size() - 1);
		String cnt = ybjkService.finMembersCount(sqlcount, param);

		Map jsonMap = new HashMap();
		jsonMap.put("total", cnt);// total��
		jsonMap.put("rows", list);// rows�� ���ÿҳ��¼ list
		// result = JSONObject.fromObject(jsonMap);// ��ʽ��result һ��Ҫ��JSONObject
		map = jsonMap;
		log.info("������Ա��Ϣ�˶�ҳ��---end---queryMemberInfo");
		return SUCCESS;
	}

	public String checkSSNInit() {
		MemberDTO m = new MemberDTO();
		m.setMemberId(key.substring(0, key.length() - 1));
		m.setDs(key.substring(key.length() - 1));
		MemberDTO mdto = ybjkService.findMemeber(m);
		/*
		 * Map jsonMap = new HashMap(); jsonMap.put("mdto", mdto); map =
		 * jsonMap;
		 */
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));
		result = JSONObject.fromObject(mdto, config);
		return SUCCESS;
	}

	public String changStatusInit() {
		// ��ȡ��ǰ����״̬
		String ds = key.split("-")[1];
		String memberid = key.split("-")[2];
		MemberDTO m = new MemberDTO();
		m.setMemberId(memberid);
		m.setDs(ds);
		MemberDTO mdto = ybjkService.findMemeber(m);
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));
		result = JSONObject.fromObject(mdto, config);
		return SUCCESS;
	}

	public String changStatus() {
		// �޸ĵ�ǰ״̬
		String memberid = key.split("-")[0];
		String ds = key.split("-")[1];
		String assistType = key.split("-")[2];
		String asort = key.split("-")[3];
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(memberid);
		memberDTO.setDs(ds);
		memberDTO.setAssistType(assistType);
		memberDTO.setAsort(new BigDecimal(asort));
		int u = ybjkService.updateMember(memberDTO);
		JSONObject json = new JSONObject();
		json.put("u", u);
		u_result = json.toString();
		return SUCCESS;
	}

	// ��ȡǰ�ĸ��µľ�����������̶�����
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getGuarantee() {
		log.info("������Ա����״̬���ҳ��---begin---getGuarantee");
		String familyno = key.split("-")[0];
		String ds = key.split("-")[1];
		Map jsonMap = new HashMap();
		if ("1".equals(ds)) {
			BillCsDTO billCsDTO = new BillCsDTO();
			billCsDTO.setBarFamilyno(familyno);
			billCsDTO.setStId("1");
			List<BillCsDTO> billcs = ybjkService.findBillCs(billCsDTO);
			jsonMap.put("rows", billcs);
		} else if ("2".equals(ds)) {
			BillNcDTO billNcDTO = new BillNcDTO();
			billNcDTO.setBarFamilyno(familyno);
			billNcDTO.setStId("1");
			List<BillNcDTO> billnc = ybjkService.findBillNc(billNcDTO);
			jsonMap.put("rows", billnc);
		}
		map = jsonMap;
		log.info("������Ա����״̬���ҳ��---end---getGuarantee");
		return SUCCESS;
	}

	// ��ȡǰ�ĸ��µľ�����������ٱ���
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getToGuarantee() {
		log.info("������Ա����״̬���ҳ��---begin---getToGuarantee");
		String familyno = key.split("-")[0];
		String ds = key.split("-")[1];
		Map jsonMap = new HashMap();
		if ("1".equals(ds)) {
			BillCsDTO billCsDTO = new BillCsDTO();
			billCsDTO.setBarFamilyno(familyno);
			billCsDTO.setStId("31");
			List<BillCsDTO> billcs = ybjkService.findBillCs(billCsDTO);
			jsonMap.put("rows", billcs);
		}
		map = jsonMap;
		log.info("������Ա����״̬���ҳ��---end---getToGuarantee");
		return SUCCESS;
	}

	// ��ȡȫ���ľ�����������̶�����
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getAllGuarantee() {
		log.info("�����ѯ������¼ҳ��---begin---getAllGuarantee");
		String familyno = key.split("-")[0];
		String ds = key.split("-")[1];
		Map jsonMap = new HashMap();
		if ("1".equals(ds)) {
			BillCsDTO billCsDTO = new BillCsDTO();
			billCsDTO.setBarFamilyno(familyno);
			billCsDTO.setStId("1");
			List<BillCsDTO> billcs = ybjkService.findALLBillCsByFNO(billCsDTO);
			jsonMap.put("rows", billcs);
		} else if ("2".equals(ds)) {
			BillNcDTO billNcDTO = new BillNcDTO();
			billNcDTO.setBarFamilyno(familyno);
			billNcDTO.setStId("1");
			List<BillNcDTO> billnc = ybjkService.findALLBillNcByFNO(billNcDTO);
			jsonMap.put("rows", billnc);
		}
		map = jsonMap;
		log.info("�����ѯ������¼ҳ��---end---getAllGuarantee");
		return SUCCESS;
	}

	// ��ȡȫ���ľ�����������ٱ���
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getAllToGuarantee() {
		log.info("�����ѯ������¼ҳ��---begin---getToGuarantee");
		String familyno = key.split("-")[0];
		String ds = key.split("-")[1];
		Map jsonMap = new HashMap();
		if ("1".equals(ds)) {
			BillCsDTO billCsDTO = new BillCsDTO();
			billCsDTO.setBarFamilyno(familyno);
			billCsDTO.setStId("31");
			List<BillCsDTO> billcs = ybjkService.findALLBillCsByFNO(billCsDTO);
			jsonMap.put("rows", billcs);
		}
		map = jsonMap;
		log.info("�����ѯ������¼ҳ��---end---getToGuarantee");
		return SUCCESS;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@SuppressWarnings("rawtypes")
	public Map getMap() {
		return map;
	}

	@SuppressWarnings("rawtypes")
	public void setMap(Map map) {
		this.map = map;
	}

	public MemberDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	public List<OrganizationDTO> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<OrganizationDTO> orgs) {
		this.orgs = orgs;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public MemberDTO getMdto() {
		return mdto;
	}

	public void setMdto(MemberDTO mdto) {
		this.mdto = mdto;
	}

	public String getU_result() {
		return u_result;
	}

	public void setU_result(String u_result) {
		this.u_result = u_result;
	}
}
