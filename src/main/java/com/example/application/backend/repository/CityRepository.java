package com.example.application.backend.repository;

import java.util.List;


import com.example.application.backend.model.Hour;



@Repository
public interface CityRepository extends CrudRepository<City, Long>  {

	public City findById(long id);
	
	public List<City> findByCity_name(String city_name);


	
}
