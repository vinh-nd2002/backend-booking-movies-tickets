package com.hust.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hust.entity.Cineplex;
import com.hust.entity.Image;
//import com.hust.form.update.CineplexForm;

import com.hust.repository.ICineplexRepository;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class CineplexService implements ICineplexService {

	@Autowired
	private ICineplexRepository cineplexRepository;

	@Autowired
	private IImageService imageService;

	@Autowired
	private IGoogleDriveFile googleDriveFile;

	@Override
	public Cineplex getCineplexById(short id) {
		return cineplexRepository.getById(id);
	}

	@Override
	public List<Cineplex> getAllCineplexs() {
		return cineplexRepository.findAll();
	}

	@Override
	public void createCineplex(MultipartFile imageForm, String cineplexCode, String cineplexName) {
		String imgName = StringUtils.cleanPath(imageForm.getOriginalFilename());

		try {
			if (imgName.contains("..")) {
				throw new Exception("Filename contains invalid path sequence " + imgName);
			}

			googleDriveFile.uploadFile(imageForm);

			Image image = imageService.convertFileToImage(imgName);
			image.setImgType(imageForm.getContentType());
			Cineplex cineplex = new Cineplex(cineplexCode, cineplexName);

			cineplex.setCineplexLogo(image);

			image.setImgOfCineplex(cineplex);

			cineplexRepository.save(cineplex);

			imageService.createImage(image);

		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

//	@Override
//	public void updateCineplex(CineplexForm cineplexForm) {
//		Cineplex cineplex = cineplexRepository.getById(cineplexForm.getCineplexId());
//
////		List<Cinema> cinemas = cineplexForm.getCinemas();
//
//	}

}
