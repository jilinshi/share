package com.share.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.share.dao.BaseDAO;
import com.share.dto.BurialDTO;
import com.share.dto.FileDTO;
import com.share.dto.FundDTO;
import com.share.dto.HousepropertyDTO;
import com.share.dto.InsuranceDTO;
import com.share.dto.ResburialDTO;
import com.share.dto.UserDTO;
import com.share.dto.VehicleDTO;
import com.share.model.CkBurial;
import com.share.model.CkFund;
import com.share.model.CkHouseproperty;
import com.share.model.CkInsurance;
import com.share.model.CkVehicle;
import com.share.model.Impdatainfo;
import com.share.model.PpBurial;
import com.share.model.PpFund;
import com.share.model.PpHouseproperty;
import com.share.model.PpInsurance;
import com.share.model.PpVehicle;
import com.share.model.ResBurial;
import com.share.model.ResInsurance;
import com.share.model.SysFile;
import com.share.model.VImpfile;
import com.share.util.BigTxt;
import com.share.util.CreatAndReadExcel;
import com.share.util.IDCard;
import com.share.util.Pager;
import com.share.util.XmlExcel;

@Service("impService")
public class ImpServiceImpl<T> implements ImpService {
	private static Logger log = LoggerFactory.getLogger(ImpServiceImpl.class);
	@Resource
	private BaseDAO<SysFile> sysFileDAO;
	@Resource
	private BaseDAO<Impdatainfo> impdatainfoDAO;
	@Resource
	private BaseDAO<VImpfile> vImpfileDAO;
	@Resource
	private BaseDAO<CkInsurance> ckInsuranceDAO;
	@Resource
	private BaseDAO<CkFund> ckFundDAO;
	@Resource
	private BaseDAO<CkBurial> ckBurialDAO;
	@Resource
	private BaseDAO<CkHouseproperty> ckHousepropertyDAO;
	@Resource
	private BaseDAO<CkVehicle> ckVehicleDAO;
	@Resource
	private BaseDAO<ResInsurance> resInsuranceDAO;
	@Resource
	private BaseDAO<ResBurial> resBurialDAO;
	@Resource
	private BaseDAO<PpInsurance> ppInsuranceDAO;
	@Resource
	private BaseDAO<PpFund> ppFundDAO;
	@Resource
	private BaseDAO<PpBurial> ppBurialDAO;
	@Resource
	private BaseDAO<PpHouseproperty> ppHousepropertyDAO;
	@Resource
	private BaseDAO<PpVehicle> ppVehicleDAO;

	private Impdatainfo impdatainfo;

	public Impdatainfo getImpdatainfo() {
		return impdatainfo;
	}

	public void setImpdatainfo(Impdatainfo impdatainfo) {
		this.impdatainfo = impdatainfo;
	}

	@Override
	public FileDTO saveFileinfo(FileDTO fileDTO, UserDTO userDTO) {
		SysFile sysFile = new SysFile();
		sysFile.setFilename(fileDTO.getFilename());
		sysFile.setRealpath(fileDTO.getRealpath());
		sysFile.setRealname(fileDTO.getRealname());
		sysFile.setFtype(fileDTO.getFtype());
		sysFile.setRemark(fileDTO.getRemark());
		sysFile.setFlag(fileDTO.getFlag());
		sysFile.setUptime(fileDTO.getOpertime());
		sysFileDAO.save(sysFile);
		log.info("上传一个文件：" + sysFile.getFileId());
		fileDTO.setFileId(new BigDecimal(sysFile.getFileId()));

		Impdatainfo o = new Impdatainfo();
		o.setFilename(fileDTO.getFilename());
		o.setFileId(fileDTO.getFileId());
		o.setOperstat("0");
		o.setRealpath(fileDTO.getRealpath());
		o.setUserId(new BigDecimal(userDTO.getUserId()));
		o.setOpertime(fileDTO.getOpertime());
		impdatainfoDAO.save(o);
		return fileDTO;
	}

	@Override
	public FileDTO removedFileinfo(FileDTO fileDTO) {

		Object[] param = new Object[1];
		param[0] = fileDTO.getFileId();
		Impdatainfo l = impdatainfoDAO.get(
				"select e from Impdatainfo e where e.fileId=?", param);
		setImpdatainfo(l);

		String hql = "update Impdatainfo m set m.operstat=? where m.infoId=?";
		Object[] params = new Object[2];
		params[0] = "0";
		params[1] = this.getImpdatainfo().getInfoId();
		impdatainfoDAO.update(hql, params);

		params = new Object[1];
		params[0] = this.getImpdatainfo();
		ckInsuranceDAO.executeHql(
				"delete CkInsurance t where t.impdatainfo= ?", params);

		params = new Object[1];
		params[0] = this.getImpdatainfo();
		ckInsuranceDAO.executeHql("delete CkFund t where t.impdatainfo= ?",
				params);

		params = new Object[1];
		params[0] = this.getImpdatainfo();
		ckInsuranceDAO.executeHql(
				"delete CkHouseproperty t where t.impdatainfo= ?", params);
		params = new Object[1];
		params[0] = this.getImpdatainfo();
		ckInsuranceDAO.executeHql("delete CkVehicle t where t.impdatainfo= ?",
				params);
		params = new Object[1];
		params[0] = this.getImpdatainfo().getFileId().toString();
		ckInsuranceDAO.executeHql("delete ResBurial t where t.fileId= ?",
				params);
		params = new Object[1];
		params[0] = this.getImpdatainfo().getFileId().toString();
		ckInsuranceDAO.executeHql("delete ResInsurance t where t.fileId= ?",
				params);

		return fileDTO;
	}

	@Override
	public List<VImpfile> queryFiles(Pager pager, Object[] param) {
		String hql = "select t from VImpfile t where t.ftype=?";
		String hqlc = "select count(*) as cnt from VImpfile t where t.ftype=?";
		Long cnt = vImpfileDAO.count(hqlc, param);
		pager.setRecords(cnt);
		List<VImpfile> rs = vImpfileDAO.find(hql, param, pager.pager,
				pager.rows);
		return rs;
	}

	/**
	 * 处理excel数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryFiletoData(FileDTO fileDTO) {
		List<T> list = new ArrayList<T>();
		try {
			if (null != fileDTO) {
				File file = new File(fileDTO.getRealpath());
				if (file.exists()) {
					List<List<Object>> rs = CreatAndReadExcel.readExcel(file);

					switch (fileDTO.getFtype()) {
					case "INSURANCE": // 社保
						list = (List<T>) convert1(rs);
						break;
					case "FUND": // 公积金
						list = (List<T>) convert2(rs);
						break;
					case "BURIAL": // 殡葬
						list = (List<T>) convert3(rs);
						break;
					case "HOUSEPROPERTY": // 房产
						list = (List<T>) convert4(rs);
						break;
					case "VEHICLE": // 车辆
						list = (List<T>) convert5(rs);
						break;
					default:
						System.out.println("default");
						break;
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<ResburialDTO> convert7(List<List<Object>> rs) {
		List<ResburialDTO> g = new ArrayList<ResburialDTO>();
		try {
			for (int i = 0; i < rs.size(); i++) {
				List<Object> row = rs.get(i);
				int cls = row.size();
				if (cls > 2) {
					String idno = (String) row.get(4);
					String iderr;
					iderr = IDCard.IDCardValidate(idno);
					if (null == iderr || "".equals(iderr)) {
						// 家庭编号 姓名 身份证号（18位） 区 街道 社区 归档日期 受理业务
						// 房产证号 所有人 所有人身份证号 坐落 面积 办理日期 类型
						String PNAME = (String) row.get(0);
						String PSEX = (String) row.get(1);
						String PAGE = (String) row.get(2);
						String ADDRESS = (String) row.get(3);
						String IDNO = (String) row.get(4);
						String DEATHREASON = (String) row.get(5);
						String TELEPHONE = (String) row.get(6);
						String HHTIME = (String) row.get(7);

						ResburialDTO e = new ResburialDTO();
						e.setPname(PNAME);
						e.setPsex(PSEX);
						e.setPage(PAGE);
						e.setAddress(ADDRESS);
						e.setIdno(IDNO);
						e.setDeathreason(DEATHREASON);
						e.setTelephone(TELEPHONE);
						e.setHhtime(HHTIME);
						g.add(e);
					}

				}
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return g;
	}

	private List<VehicleDTO> convert5(List<List<Object>> rs) {
		List<VehicleDTO> g = new ArrayList<VehicleDTO>();
		try {
			for (int i = 0; i < rs.size(); i++) {
				List<Object> row = rs.get(i);
				int cls = row.size();
				if (cls > 2) {
					String idno = (String) row.get(2);

					String iderr;

					iderr = IDCard.IDCardValidate(idno);

					if (null == iderr || "".equals(iderr)) {
						// 家庭编号 姓名 身份证号（18位） 区 街道 社区 归档日期 受理业务
						// 房产证号 所有人 所有人身份证号 坐落 面积 办理日期 类型

						String FNO = (String) row.get(0);
						String PNAME = (String) row.get(1);
						String IDNO = (String) row.get(2);
						String OO1 = (String) row.get(3);
						String OO2 = (String) row.get(4);
						String OO3 = (String) row.get(5);
						String VEHICAL_NO = (String) row.get(6);
						String BRAND = (String) row.get(7);
						String MOTO_NO = (String) row.get(8);
						String BUYTIME = (String) row.get(9);
						String VTYPE = (String) row.get(10);

						VehicleDTO e = new VehicleDTO();

						e.setFno(FNO);
						e.setPname(PNAME);
						e.setIdno(IDNO);
						e.setOo1(OO1);
						e.setOo2(OO2);
						e.setOo3(OO3);
						e.setVehicalNo(VEHICAL_NO);
						e.setBrand(BRAND);
						e.setMotoNo(MOTO_NO);
						e.setBuytime(BUYTIME);
						e.setVtype(VTYPE);

						g.add(e);
					}
				}

			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return g;
	}

	private List<HousepropertyDTO> convert4(List<List<Object>> rs) {
		List<HousepropertyDTO> g = new ArrayList<HousepropertyDTO>();
		try {
			for (int i = 0; i < rs.size(); i++) {
				List<Object> row = rs.get(i);
				int cls = row.size();
				if (cls > 2) {
					String idno = (String) row.get(2);

					String iderr;

					iderr = IDCard.IDCardValidate(idno);

					if (null == iderr || "".equals(iderr)) {
						// 家庭编号 姓名 身份证号（18位） 区 街道 社区 归档日期 受理业务
						// 房产证号 所有人 所有人身份证号 坐落 面积 办理日期 类型

						String IDNO = (String) row.get(2);
						String PNAME = (String) row.get(1);
						String OO1 = (String) row.get(3);
						String OO2 = (String) row.get(4);
						String OO3 = (String) row.get(5);
						String GGTIME = (String) row.get(6);
						String SLYW = (String) row.get(7);
						String FCZH = (String) row.get(8);
						String BNAME = (String) row.get(9);
						String BIDNO = (String) row.get(10);
						String ZUOLUO = (String) row.get(11);
						String MIANJI = (String) row.get(12);
						String BLTIME = (String) row.get(14);
						String SUBJECT = (String) row.get(15);
						String REMARK = (String) row.get(13);
						String COL1 = (String) row.get(0);

						HousepropertyDTO e = new HousepropertyDTO();

						e.setIdno(IDNO);
						e.setPname(PNAME);
						e.setOo1(OO1);
						e.setOo2(OO2);
						e.setOo3(OO3);
						e.setGgtime(GGTIME);
						e.setSlyw(SLYW);
						e.setFczh(FCZH);
						e.setBname(BNAME);
						e.setBidno(BIDNO);
						e.setZuoluo(ZUOLUO);
						e.setMianji(MIANJI);
						e.setBltime(BLTIME);
						e.setSubject(SUBJECT);
						e.setRemark(REMARK);
						e.setCol1(COL1);

						g.add(e);
					}
				}

			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return g;
	}

	private List<BurialDTO> convert3(List<List<Object>> rs) {
		List<BurialDTO> g = new ArrayList<BurialDTO>();
		try {
			for (int i = 0; i < rs.size(); i++) {
				List<Object> row = rs.get(i);
				int cls = row.size();
				if (cls > 2) {
					String idno = (String) row.get(2);

					String iderr;

					iderr = IDCard.IDCardValidate(idno);

					if (null == iderr || "".equals(iderr)) {
						// 家庭编号 低保成员姓名 低保身份证号码
						// 区 街道 社区 姓名_殡葬 身份证_殡葬 火化时间
						// String FNO = (String) row.get(0);
						String IDNO = (String) row.get(2);
						String PNAME = (String) row.get(1);
						String OO1 = (String) row.get(3);
						String OO2 = (String) row.get(4);
						String OO3 = (String) row.get(5);
						String BIDNO = (String) row.get(7);
						String BNAME = (String) row.get(6);
						String HHTIME = (String) row.get(8);
						String COL1 = (String) row.get(0);

						BurialDTO e = new BurialDTO();
						e.setIdno(IDNO);
						e.setPname(PNAME);
						e.setOo1(OO1);
						e.setOo2(OO2);
						e.setOo3(OO3);
						e.setBidno(BIDNO);
						e.setBname(BNAME);
						e.setHhtime(HHTIME);
						e.setCol1(COL1);
						g.add(e);
					}
				}

			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return g;
	}

	private List<FundDTO> convert2(List<List<Object>> rs) {

		List<FundDTO> g = new ArrayList<FundDTO>();
		try {
			for (int i = 0; i < rs.size(); i++) {
				List<Object> row = rs.get(i);
				int cls = row.size();
				if (cls > 2) {
					String idno = (String) row.get(1);

					String iderr;

					iderr = IDCard.IDCardValidate(idno);

					if (null == iderr || "".equals(iderr)) {
						// 姓名 身份证号 公积金账号 所有人姓名 所有人身份证号
						// 缴存基数 最后缴款日 账户余额 单位名称 状态 开户日期 区

						String IDNO = (String) row.get(1);
						String PNAME = (String) row.get(0);
						String BIDNO = (String) row.get(2);
						String BNAME = (String) row.get(3);
						String GJJACCOUNT = (String) row.get(4);
						String CARDINAL = (String) row.get(5);
						String LASTTIME = (String) row.get(6);
						String BANLANCE = (String) row.get(7);
						String UNITNAME = (String) row.get(8);
						String STATE = (String) row.get(9);
						String BEGINTIME = (String) row.get(10);
						String AREA = (String) row.get(11);

						FundDTO e = new FundDTO();
						e.setIdno(IDNO);
						e.setPname(PNAME);
						e.setBidno(BIDNO);
						e.setBname(BNAME);
						e.setGjjaccount(GJJACCOUNT);
						e.setCardinal(new BigDecimal(CARDINAL));
						e.setLasttime(LASTTIME);
						e.setBanlance(new BigDecimal(BANLANCE));
						e.setUnitname(UNITNAME);
						e.setState(STATE);
						e.setBegintime(BEGINTIME);
						e.setArea(AREA);

						g.add(e);
					}
				}

			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return g;
	}

	private List<InsuranceDTO> convert1(List<List<Object>> rs) {

		List<InsuranceDTO> g = new ArrayList<InsuranceDTO>();
		try {
			for (int i = 0; i < rs.size(); i++) {
				List<Object> row = rs.get(i);
				int cls = row.size();
				if (cls > 2) {
					String idno = (String) row.get(2);

					String iderr;

					iderr = IDCard.IDCardValidate(idno);

					if (null == iderr || "".equals(iderr)) {

						// String IN_ID = (String) row.get(0);
						// 22020108060600 郭玉芝 220202196408143649
						// 昌邑 哈达湾 炭素 郭玉芝 220202196408143649 集体续保昌邑 2014-8-18
						// 00:00:00 1418.80
						String FNO = (String) row.get(0);
						String IDNO = (String) row.get(2);
						String PNAME = (String) row.get(1);
						String OO1 = (String) row.get(3);
						String OO2 = (String) row.get(4);
						String OO3 = (String) row.get(5);
						String SBIDNO = (String) row.get(7);
						String SBNAME = (String) row.get(6);
						String COMP = (String) row.get(8);
						String TXTIME = (String) row.get(9);
						String TXMONEY = (String) row.get(10);
						InsuranceDTO e = new InsuranceDTO();
						e.setFno(FNO);
						e.setIdno(IDNO);
						e.setPname(PNAME);
						e.setOo1(OO1);
						e.setOo2(OO2);
						e.setOo3(OO3);
						e.setSbidno(SBIDNO);
						e.setSbname(SBNAME);
						e.setComp(COMP);
						e.setTxmoney(TXMONEY);
						e.setTxtime(TXTIME);
						g.add(e);
					}
				}

			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return g;
	}

	@Override
	public String saveFileGrid(FileDTO fileDTO) {
		String result = "导入文件成功";
		log.info("begin saveFileGrid");
		Object[] param = new Object[1];
		param[0] = fileDTO.getFileId();
		Impdatainfo l = impdatainfoDAO.get(
				"select e from Impdatainfo e where e.fileId=?", param);
		setImpdatainfo(l);
		fileDTO.setRealpath(l.getRealpath());
		try {
			if (null != fileDTO) {
				File file = new File(fileDTO.getRealpath());
				if (file.exists()) {
					String name = file.getName();
					if (name.trim().toLowerCase().endsWith(".txt")) {
						log.info("txt  " + fileDTO.getFtype() + " is starting");
						BigTxt bt = new BigTxt();
						List<String> rs = bt.readFile(file.getAbsolutePath());
						switch (fileDTO.getFtype()) {

						case "RESINSURANCE": // 车辆
							savedata6(rs);
							l.setOperstat("1");
							impdatainfoDAO.update(l);
							break;
						default:
							l.setOperstat("0");
							impdatainfoDAO.update(l);
							result = "不支持此数据文件";
							break;
						}
					} else {
						log.info("excel " + fileDTO.getFtype() + " is starting");
						// List<List<Object>> rs = CreatAndReadExcel
						// .readExcel(file);
						// System.out.println(rs.size());
						switch (fileDTO.getFtype()) {
						case "INSURANCE": // 社保
							savedata1(CreatAndReadExcel.readExcel(file));
							l.setOperstat("1");
							impdatainfoDAO.update(l);
							break;
						case "FUND": // 公积金
							savedata2(CreatAndReadExcel.readExcel(file));
							l.setOperstat("1");
							impdatainfoDAO.update(l);
							break;
						case "BURIAL": // 殡葬
							savedata3(CreatAndReadExcel.readExcel(file));
							l.setOperstat("1");
							impdatainfoDAO.update(l);
							break;
						case "HOUSEPROPERTY": // 房产
							savedata4(CreatAndReadExcel.readExcel(file));
							l.setOperstat("1");
							impdatainfoDAO.update(l);
							break;
						case "VEHICLE": // 车辆
							savedata5(CreatAndReadExcel.readExcel(file));
							l.setOperstat("1");
							impdatainfoDAO.update(l);
							break;
						case "RESBURIAL": // 车辆
							savedata7(CreatAndReadExcel.readExcel(file));
							l.setOperstat("1");
							impdatainfoDAO.update(l);
							break;
						default:
							// System.out.println("default");
							l.setOperstat("0");
							impdatainfoDAO.update(l);
							result = "不支持此数据文件";
							break;
						}

					}
				} else {
					result = "数据文件不存在，请重新上传文件。";
					log.info("文件不存在");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;

	}

	private List<String> savedata6(List<String> rs) {
		List<ResInsurance> r = new ArrayList<ResInsurance>();
		resInsuranceDAO.executeHql("delete ResInsurance t", null);
		for (String s : rs) {
			String[] o = s.split("	");
			// 220204194908081249 王桂仙 204023 吉林远东药业集团股份有限公司 1949-8-8 00:00:00
			// 1968-11-1 00:00:00 2001-1-8 00:00:00 1633.28
			ResInsurance e = new ResInsurance();
			e.setIdno(o[0]);
			e.setPname(o[1]);
			e.setInno(o[2]);
			e.setDanwei(o[3]);
			e.setBirthday(o[4]);
			e.setJfBegin(o[5]);
			e.setLqBegin(o[6]);
			e.setLqMoney(o[7]);
			e.setFileId(this.getImpdatainfo().getFileId().toString());
			r.add(e);
		}
		resInsuranceDAO.saveBatch(r);
		return rs;
	}

	private List<ResburialDTO> savedata7(List<List<Object>> rs) {
		Object[] params = new Object[1];
		params[0] = this.getImpdatainfo().getFileId().toString();
		resBurialDAO.executeHql("delete ResBurial t where t.fileId= ?", params);
		List<ResburialDTO> g = this.convert7(rs);
		List<ResBurial> r = new ArrayList<ResBurial>();
		for (ResburialDTO s : g) {
			ResBurial e = new ResBurial();
			BeanUtils.copyProperties(s, e);
			e.setFileId(this.getImpdatainfo().getFileId().toString());
			r.add(e);
		}
		resBurialDAO.saveBatch(r);
		return g;
	}

	private List<VehicleDTO> savedata5(List<List<Object>> rs) {
		Object[] params = new Object[1];
		params[0] = this.getImpdatainfo();
		ckVehicleDAO.executeHql("delete CkVehicle t where t.impdatainfo= ?",
				params);
		List<VehicleDTO> g = this.convert5(rs);
		List<CkVehicle> r = new ArrayList<CkVehicle>();
		for (VehicleDTO s : g) {
			CkVehicle e = new CkVehicle();
			BeanUtils.copyProperties(s, e);
			e.setImpdatainfo(this.getImpdatainfo());
			r.add(e);
		}
		ckVehicleDAO.saveBatch(r);
		return g;
	}

	private List<HousepropertyDTO> savedata4(List<List<Object>> rs) {
		Object[] params = new Object[1];
		params[0] = this.getImpdatainfo();
		ckHousepropertyDAO.executeHql(
				"delete CkHouseproperty t where t.impdatainfo= ?", params);
		List<HousepropertyDTO> g = this.convert4(rs);
		List<CkHouseproperty> r = new ArrayList<CkHouseproperty>();
		for (HousepropertyDTO s : g) {
			CkHouseproperty e = new CkHouseproperty();
			BeanUtils.copyProperties(s, e);
			e.setImpdatainfo(this.getImpdatainfo());
			r.add(e);
		}
		ckHousepropertyDAO.saveBatch(r);
		return g;
	}

	private List<BurialDTO> savedata3(List<List<Object>> rs) {
		Object[] params = new Object[1];
		params[0] = this.getImpdatainfo();
		ckBurialDAO.executeHql("delete CkBurial t where t.impdatainfo= ?",
				params);
		List<BurialDTO> g = this.convert3(rs);
		List<CkBurial> r = new ArrayList<CkBurial>();
		for (BurialDTO s : g) {
			CkBurial e = new CkBurial();
			BeanUtils.copyProperties(s, e);
			e.setImpdatainfo(this.getImpdatainfo());
			r.add(e);
		}
		ckBurialDAO.saveBatch(r);
		return g;
	}

	private List<FundDTO> savedata2(List<List<Object>> rs) {
		Object[] params = new Object[1];
		params[0] = this.getImpdatainfo();
		ckInsuranceDAO.executeHql("delete CkFund t where t.impdatainfo= ?",
				params);
		List<FundDTO> g = this.convert2(rs);
		List<CkFund> r = new ArrayList<CkFund>();
		for (FundDTO s : g) {
			CkFund e = new CkFund();
			BeanUtils.copyProperties(s, e);
			e.setImpdatainfo(this.getImpdatainfo());
			r.add(e);
		}
		ckFundDAO.saveBatch(r);
		return g;
	}

	private List<InsuranceDTO> savedata1(List<List<Object>> rs) {
		Object[] params = new Object[1];
		params[0] = this.getImpdatainfo();
		ckInsuranceDAO.executeHql(
				"delete CkInsurance t where t.impdatainfo= ?", params);
		List<InsuranceDTO> g = this.convert1(rs);
		List<CkInsurance> r = new ArrayList<CkInsurance>();
		for (InsuranceDTO s : g) {
			CkInsurance e = new CkInsurance();
			BeanUtils.copyProperties(s, e);
			e.setImpdatainfo(this.getImpdatainfo());
			r.add(e);
		}
		ckInsuranceDAO.saveBatch(r);
		return g;
	}

	@SuppressWarnings({ "hiding", "unchecked", "rawtypes" })
	@Override
	public <T> List<T> queryCheckData(Pager pager, String hql, Object[] param,
			String clz) {
		List<T> list = new ArrayList<T>();
		String table = clz;
		log.info("查询>>>>>" + table);
		if ("PpInsurance".equals(table)) {

			int end = hql.indexOf("from");
			String hqlc = "select count(*) as cnt  " + hql.substring(end);

			log.info("查询>>>>>" + hql);
			log.info("查询>>>>>" + hqlc);
			Long cnt = ppInsuranceDAO.count(hqlc, param);
			pager.setRecords(cnt);
			list = (List<T>) ppInsuranceDAO.find(hql, param, pager.pager,
					pager.rows);
			Map session = ActionContext.getContext().getSession();
			LinkedHashMap<String, String> title = XmlExcel.getXmlexcel()
					.getTableMap("ppinsurance");

			session.put("hql", hql);
			session.put("param1", param);
			session.put("param", null);
			session.put("title", title);

		}
		if ("PpFund".equals(table)) {

			int end = hql.indexOf("from");
			String hqlc = "select count(*) as cnt  " + hql.substring(end);

			log.info("查询>>>>>" + hql);
			log.info("查询>>>>>" + hqlc);
			Long cnt = ppFundDAO.count(hqlc, param);
			pager.setRecords(cnt);
			list = (List<T>) ppFundDAO
					.find(hql, param, pager.pager, pager.rows);
			Map session = ActionContext.getContext().getSession();
			LinkedHashMap<String, String> title = XmlExcel.getXmlexcel()
					.getTableMap("ppfund");
			session.put("hql", hql);
			session.put("param1", param);
			session.put("param", null);
			session.put("title", title);

		}
		if ("PpBurial".equals(table)) {

			int end = hql.indexOf("from");
			String hqlc = "select count(*) as cnt  " + hql.substring(end);

			log.info("查询>>>>>" + hql);
			log.info("查询>>>>>" + hqlc);
			Long cnt = ppBurialDAO.count(hqlc, param);
			pager.setRecords(cnt);
			list = (List<T>) ppBurialDAO.find(hql, param, pager.pager,
					pager.rows);
			Map session = ActionContext.getContext().getSession();
			LinkedHashMap<String, String> title = XmlExcel.getXmlexcel()
					.getTableMap("ppburial");

			session.put("hql", hql);
			session.put("param1", param);
			session.put("param", null);
			session.put("title", title);

		}
		if ("PpHouseproperty".equals(table)) {

			int end = hql.indexOf("from");
			String hqlc = "select count(*) as cnt  " + hql.substring(end);

			log.info("查询>>>>>" + hql);
			log.info("查询>>>>>" + hqlc);
			Long cnt = ppHousepropertyDAO.count(hqlc, param);
			pager.setRecords(cnt);
			list = (List<T>) ppHousepropertyDAO.find(hql, param, pager.pager,
					pager.rows);
			Map session = ActionContext.getContext().getSession();
			LinkedHashMap<String, String> title = XmlExcel.getXmlexcel()
					.getTableMap("pphouseproperty");

			session.put("hql", hql);
			session.put("param1", param);
			session.put("param", null);
			session.put("title", title);

		}
		if ("PpVehicle".equals(table)) {

			int end = hql.indexOf("from");
			String hqlc = "select count(*) as cnt  " + hql.substring(end);

			log.info("查询>>>>>" + hql);
			log.info("查询>>>>>" + hqlc);
			Long cnt = ppVehicleDAO.count(hqlc, param);
			pager.setRecords(cnt);
			list = (List<T>) ppVehicleDAO.find(hql, param, pager.pager,
					pager.rows);
			Map session = ActionContext.getContext().getSession();
			LinkedHashMap<String, String> title = XmlExcel.getXmlexcel()
					.getTableMap("ppvehicle");

			session.put("hql", hql);
			session.put("param1", param);
			session.put("param", null);
			session.put("title", title);

		}

		return list;

	}

}
