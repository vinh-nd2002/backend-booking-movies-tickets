package com.hust.services;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.services.drive.model.File;

@Service
public class GoogleDriveFileService implements IGoogleDriveFile {
	private static final String IMAGE_FOLDER = "image";

	private static final String TYPE = "anyone";

	private static final String ROLE = "reader";

	@Autowired
	GoogleFileManager googleFileManager;

	@Override
	public List<File> getAllFile() throws IOException, GeneralSecurityException {
		List<File> files = googleFileManager.listEverything();
		return files;
	}

	@Override
	public void deleteFile(String id) throws Exception {
		googleFileManager.deleteFileOrFolder(id);
	}

	@Override
	public void uploadFile(MultipartFile file) {
		googleFileManager.uploadFile(file, IMAGE_FOLDER, TYPE, ROLE);
	}

	@Override
	public void downloadFile(String id, OutputStream outputStream) throws IOException, GeneralSecurityException {
		googleFileManager.downloadFile(id, outputStream);
	}

	@Override
	public File getFileDetail(String id) throws IOException, GeneralSecurityException {
		return googleFileManager.getFileDetail(id);
	}

}