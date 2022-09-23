package com.veeru.uploaddownloadservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veeru.uploaddownloadservice.entities.FileEntity;

/**
 * @author Virupaksha K
 *
 */
public interface UploadDownloadRepository extends JpaRepository<FileEntity, String> {

	FileEntity findByName(String name);
}
