package com.example.application.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.application.backend.model.Location;
import com.example.application.backend.model.WindSpeed;


@Repository
public interface WindSpeedRepository extends CrudRepository<WindSpeed, Long>  {

	
	public WindSpeed findById(long id);

	
}
