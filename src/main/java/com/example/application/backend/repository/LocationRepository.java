package com.example.application.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.application.backend.model.Location;


@Repository
public interface LocationRepository extends CrudRepository<Location, Long>  {

	
	public Location findById(long id);
	public List<Location> findAll();

	
}
