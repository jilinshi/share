package com.share.service;

import com.share.dto.FileDTO;

public interface ImpService {
	public FileDTO saveFileinfo(FileDTO fileDTO);

	public FileDTO removedFileinfo(FileDTO fileDTO);
}
