/**
 * 
 */
package com.veeru.uploaddownloadservice.entities;

/**
 * @author Virupaksha K
 *
 */

public class ResponseDto {

	private String fileName;
	private long size;
	private String downloadUrl;
	private String fileType;

	public ResponseDto() {
	}

	public ResponseDto(String fileName, long size, String downloadUrl, String fileType) {
		super();
		this.fileName = fileName;
		this.size = size;
		this.downloadUrl = downloadUrl;
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public long getSize() {
		return size;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
