package com.hust.repository;

import com.hust.entity.Image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<Image, Integer> {

}
