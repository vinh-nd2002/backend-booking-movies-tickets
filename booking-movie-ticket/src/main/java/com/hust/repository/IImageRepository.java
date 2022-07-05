package com.hust.repository;

import com.hust.entity.Image;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IImageRepository extends JpaRepository<Image, Integer> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Image where img_id = ?1")
	void costumDeleteById(int id);
}
