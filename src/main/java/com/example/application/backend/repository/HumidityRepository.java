package com.example.application.backend.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.application.backend.model.Humidity;



@Repository
public interface HumidityRepository extends CrudRepository<Humidity, Long>  {

	
	public Humidity findById(long id);


	
}
