package com.hust.services;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.hust.entity.Image;

public interface IImageService {
	Image convertFileToImage(String imgName) throws IOException, GeneralSecurityException;

	void createImage(Image image);

	Image getImageById(int imageId);

	void deleteImage(int id);
}
