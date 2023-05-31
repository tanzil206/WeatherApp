package com.example.application.backend.service;

import com.example.application.backend.model.City;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.backend.model.Hour;
import com.example.application.backend.model.Location;
import com.example.application.backend.repository.CityRepository;
import com.example.application.backend.repository.HourRepository;
import com.example.application.backend.repository.HumidityRepository;
import com.example.application.backend.repository.LocationRepository;
import com.example.application.backend.repository.TemperatureRepository;
import com.example.application.backend.utility.ApiUtility;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class CityService {

	@Autowired
	CityRepository cityRepository;

	public ArrayList<City> getAllCity() {
		ArrayList<City> city = new ArrayList<>();
		cityRepository.findAll().forEach(city::add);
		return city;
	}

	public City getByCity(String city_name) {
		City city = cityRepository.findByCity_name(city_name);

		return city;
	}

}