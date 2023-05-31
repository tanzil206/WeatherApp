package com.example.application.backend.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.backend.model.Hour;
import com.example.application.backend.model.Location;
import com.example.application.backend.repository.HourRepository;
import com.example.application.backend.repository.HumidityRepository;
import com.example.application.backend.repository.LocationRepository;
import com.example.application.backend.repository.TemperatureRepository;
import com.example.application.backend.utility.ApiUtility;

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class HourService {


	@Autowired
	HourRepository hourRepository;


	public HourService(HourRepository hourRepository) {

		super();
		this.hourRepository = hourRepository;
	}

	public ArrayList<Hour> getAllHour() {
		ArrayList<Hour> hour = new ArrayList<>();
		hourRepository.findAll().forEach(hour::add);
		return hour;
	}

	public ArrayList<Hour> getHourlyForecast(String cityName,String date) {
		ArrayList<Hour> forecastList = new ArrayList<>();
		List<Hour> hour = hourRepository.findByCity_nameAndDate(cityName,date);

		return (ArrayList<Hour>) hour;
	}


}