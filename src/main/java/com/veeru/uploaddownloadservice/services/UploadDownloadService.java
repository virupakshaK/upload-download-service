package com.veeru.uploaddownloadservice.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.veeru.uploaddownloadservice.entities.FileEntity;
import com.veeru.uploaddownloadservice.repositories.UploadDownloadRepository;
import com.veeru.uploaddownloadservice.utilities.FileCompressDecompressUtil;

/**
 * @author Virupaksha K
 *
 */
@Service
public class UploadDownloadService {

	@Autowired
	UploadDownloadRepository repository;

	public FileEntity uploadFile(MultipartFile file) throws IOException {
		FileEntity fileBean = new FileEntity();
		fileBean.setName(file.getOriginalFilename());
		fileBean.setType(file.getContentType());
		fileBean.setData(FileCompressDecompressUtil.compressed(file.getBytes()));
		return repository.save(fileBean);

	}

	public FileEntity downloadFile(String fileId) throws Exception {

		FileEntity entity = null;

		Optional<FileEntity> fileBean = repository.findById(fileId);

		if (fileBean.isPresent()) {
			entity = fileBean.get();
			entity.setData(FileCompressDecompressUtil.decompressed(entity.getData()));

		} else {
			throw new Exception("File not Found Exception, file Id:" + fileId);
		}

		return entity;

	}
}
