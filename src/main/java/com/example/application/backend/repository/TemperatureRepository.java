package com.example.application.backend.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.application.backend.model.Temperature;


@Repository
public interface TemperatureRepository extends CrudRepository<Temperature, Long>  {

	
	public Temperature findById(long id);

	
}
