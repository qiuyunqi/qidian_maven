package com.xiaohe.qd.model;

import java.io.File;
import java.util.List;

import com.xiaohe.qd.util.Constants;


public class ImageModel {
	private File imgFile;
	private List<File> files;
	private List<String> filesFileName;
	private List<String> filesFileContentType;
	private String imgFileFileName;
	private String imgFileContentType;
	private String savePath = Constants.DIR_TEMP;
	private String saveUrl;
	
	public File getImgFile() {
		return imgFile;
	}
	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public List<String> getFilesFileName() {
		return filesFileName;
	}
	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}
	public List<String> getFilesFileContentType() {
		return filesFileContentType;
	}
	public void setFilesFileContentType(List<String> filesFileContentType) {
		this.filesFileContentType = filesFileContentType;
	}
	public String getImgFileFileName() {
		return imgFileFileName;
	}
	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}
	public String getImgFileContentType() {
		return imgFileContentType;
	}
	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getSaveUrl() {
		return saveUrl;
	}
	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}
	
}
