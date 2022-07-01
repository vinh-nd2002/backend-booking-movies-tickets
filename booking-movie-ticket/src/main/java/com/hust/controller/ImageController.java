package com.hust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hust.services.IImageService;

@RestController
@RequestMapping(value = "api/v1/images")
@CrossOrigin("*")
public class ImageController {
	@Autowired
	private IImageService imageService;

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteImage(@PathVariable(name = "id") int id) throws Exception {
		imageService.deleteImage(id);
		return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
	}
}
