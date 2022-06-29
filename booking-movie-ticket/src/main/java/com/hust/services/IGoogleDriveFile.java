package com.hust.services;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.api.services.drive.model.File;

public interface IGoogleDriveFile {
	List<File> getAllFile() throws IOException, GeneralSecurityException;

	void deleteFile(String id) throws Exception;

	void downloadFile(String id, OutputStream outputStream) throws IOException, GeneralSecurityException;

	File getFileDetail(String id) throws IOException, GeneralSecurityException;

	void uploadFile(MultipartFile file);

}
