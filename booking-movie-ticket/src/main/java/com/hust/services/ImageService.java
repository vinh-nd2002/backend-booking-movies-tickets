package com.hust.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.services.drive.model.File;
import com.hust.entity.Image;
import com.hust.repository.IImageRepository;

@Service
@Transactional
public class ImageService implements IImageService {

	private static final String IMAGE_URL_PREFIX = "https://drive.google.com/uc?id=";

	@Autowired
	private IGoogleDriveFile googleDriveFile;

	@Autowired
	private IImageRepository imageRepository;

	@Override
	public Image convertFileToImage(String imgName) throws IOException, GeneralSecurityException {
		List<File> files = googleDriveFile.getAllFile();
		Image image = new Image();
		File fileItem = files.stream().filter(item -> imgName.equals(item.getName())).findAny().orElse(null);
		image.setImgName(fileItem.getName());
		image.setDriveFileId(fileItem.getId());
		image.setImgUrl(IMAGE_URL_PREFIX + fileItem.getId());
		return image;
	}

	@Override
	public void createImage(Image image) {
		imageRepository.save(image);

	}

	@Override
	public Image getImageById(int imageId) {
		return imageRepository.getById(imageId);
	}

	@Override
	public void deleteImage(int id) {
//		Image image = imageRepository.getById(id);
//		imageRepository.delete(image);
		imageRepository.costumDeleteById(id);
	}

}
