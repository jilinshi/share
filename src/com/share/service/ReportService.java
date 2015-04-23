package com.share.service;

import java.util.List;

import com.share.model.Personalinfo;
import com.share.model.SysOrganization;
import com.share.util.Pager;

public interface ReportService {

	public List<Personalinfo> queryPersonalinfos(Pager pager,
			List<Object> param, String jwhere);
	public List<SysOrganization> findOrganlist(long orgid);
}
