package com.share.service;

import java.util.List;

import com.share.dto.FileDTO;
import com.share.dto.UserDTO;
import com.share.model.VImpfile;
import com.share.util.Pager;

public interface ImpService {
	public FileDTO saveFileinfo(FileDTO fileDTO, UserDTO userDTO);

	public FileDTO removedFileinfo(FileDTO fileDTO);

	public <T> List<T> queryFiletoData(FileDTO fileDTO);

	public String saveFileGrid(FileDTO fileDTO);

	public List<VImpfile> queryFiles(Pager pager, Object[] param);

	public <T> List<T> queryCheckData(Pager pager,String hql, Object[] param, Class<T> clz);
}
