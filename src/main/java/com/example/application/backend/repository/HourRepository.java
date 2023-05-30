package com.example.application.backend.repository;

import java.util.List;


import com.example.application.backend.model.Hour;



@Repository
public interface HourRepository extends CrudRepository<Hour, Long>  {

	public Hour findById(long id);
	
	public List<Hour> findByDate(String date);


	
}
