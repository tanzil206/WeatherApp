package com.example.application.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.application.backend.model.Hour;
import com.example.application.backend.model.Humidity;
import com.example.application.backend.model.Location;


@Repository
public interface HourRepository extends CrudRepository<Hour, Long>  {

	public Hour findById(long id);
	
	public List<Hour> findByDate(String date);


	
}
