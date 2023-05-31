package com.example.application.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.application.backend.model.City;
import com.example.application.backend.model.Hour;

@Repository
public interface HourRepository extends CrudRepository<Hour, Long> {

	public Hour findById(long id);

	public List<Hour> findByDate(String date);

	@Query(value = "SELECT * FROM hour a WHERE a.city_name = ?1 and a.date = ?2 ", nativeQuery = true)
	public List<Hour> findByCity_nameAndDate(String city_name, String date);

}
