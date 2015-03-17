package com.share.action.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.share.dto.UserDTO;
import com.share.service.YbService;
import com.share.util.Pager;

@Controller
public class QueryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(QueryAction.class);
	
	@SuppressWarnings("rawtypes")
	private Map map;// 返回的json
	 /** 当前页面 */ 
	private String page;  
	   /** 每页的记录数 */ 
	private String rows;
	
	@Resource
	private YbService ybjkService;
	
	/**
	 * 
	 * @return
	 */
	public String queryInit() {
		Pager pager = new Pager(page, rows);
		int start = pager.start;
		int end = pager.end;
		List<Object> param = new ArrayList<Object>();
		String sqlwhere = "";
		param.add(end);
		param.add(start);
		String sql = " SELECT *   FROM (SELECT a.*, ROWNUM rn FROM ( select user_id, org_id, uaccount, upwds, uname, mobilephone, idno, flag, ctime, utime from sys_users "
				+ "where 1=1 "
				+ sqlwhere
				+ " ) a WHERE ROWNUM <= ?)  WHERE rn >= ?";

		String sqlcount = " SELECT count(1) as cnt FROM sys_users t  where 1=1 ";

		List<UserDTO> list = ybjkService.finMemberstest(sql, param);

		param.remove(param.size() - 1);
		param.remove(param.size() - 1);
		String cnt = ybjkService.finMembersCount(sqlcount, param);

		Map jsonMap = new HashMap();
		jsonMap.put("total", cnt);// total键
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		// result = JSONObject.fromObject(jsonMap);// 格式化result 一定要是JSONObject
		map = jsonMap;
/*	            String json = "{ 'page': 1, 'total': 2, 'records': 15, 'rows': [{'cell':['aaa']},{'cell':['bbb']},{'cell':['vvcc']}]}";
	            Map jsonMap = new HashMap();
				jsonMap.put("page", "1");// total键
				jsonMap.put("total", "1");
				jsonMap.put("records", "10");
				jsonMap.put("rows", "{'cell'=['aaa']},{'cell'=['bbb']},{'cell'=['vvcc']}");
				map = jsonMap;	*/	
		System.out.println(map.toString());
		return SUCCESS;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}

}
