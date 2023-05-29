package com.example.application.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.application.backend.model.Location;
import com.example.application.backend.model.Unit;


@Repository
public interface UnitRepository extends CrudRepository<Unit, Long>  {

	
	public Unit findById(long id);
	public Unit getWeatherUnitByWeatherType(String weatherType);

	
}
