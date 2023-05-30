package com.example.application.backend.repository;

import com.example.application.backend.model.City;
import java.util.List;


import com.example.application.backend.model.Hour;



@Repository
public interface CityRepository extends CrudRepository<City, Long>  {

	public City findById(long id);
	
	public City findByCity_name(String city_name);


	
}
