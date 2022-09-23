/**
 * 
 */
package com.veeru.uploaddownloadservice.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.veeru.uploaddownloadservice.entities.ResponseDto;
import com.veeru.uploaddownloadservice.entities.FileEntity;
import com.veeru.uploaddownloadservice.services.UploadDownloadService;

/**
 * @author Virupaksha K
 *
 */
@RestController
@RequestMapping("file")
public class UploadDownloadFileController {

	@Autowired
	UploadDownloadService service;

	@GetMapping("/test")
	public String test() {
		return "Test method access";
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("uploadFile") MultipartFile file) throws IOException {

		FileEntity result = service.uploadFile(file);
		ResponseDto responseDto = null;

		if (result != null) {
			responseDto = new ResponseDto();
			String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/download/")
					.path(result.getId()).toUriString();

			responseDto.setFileName(result.getName());
			responseDto.setDownloadUrl(downloadUrl);
			responseDto.setFileType(result.getType());
			responseDto.setSize(file.getSize());
		} else {
			return ResponseEntity.internalServerError().body(new RuntimeException("Something went wrong"));
		}

		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> donwloadImage(@PathVariable String fileName) throws Exception {
		FileEntity fileBean = service.downloadFile(fileName);

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.parseMediaType(fileBean.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachement; fileName=\"" + fileBean.getName() + "\"")
				.body(new ByteArrayResource(fileBean.getData()));
	}
}
