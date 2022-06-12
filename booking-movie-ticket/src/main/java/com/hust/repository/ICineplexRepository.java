package com.hust.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hust.entity.Cineplex;

public interface ICineplexRepository extends JpaRepository<Cineplex, Short>, JpaSpecificationExecutor<Cineplex> {

}
