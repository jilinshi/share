package com.share.service;

import java.util.List;
import com.share.dto.FileDTO;
import com.share.dto.UserDTO;
import com.share.model.VImpfile;

public interface ImpService {
	public FileDTO saveFileinfo(FileDTO fileDTO,UserDTO userDTO);

	public FileDTO removedFileinfo(FileDTO fileDTO);

	public List<VImpfile> queryFiles();

	public <T> List<T> queryFiletoData(FileDTO fileDTO);
}
