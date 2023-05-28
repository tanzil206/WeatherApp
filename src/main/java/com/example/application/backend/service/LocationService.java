package com.example.application.backend.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.backend.model.Location;

import com.example.application.backend.repository.LocationRepository;

import com.example.application.backend.utility.ApiUtility;
import org.json.JSONObject;

@Service
public class LocationService {

	@Autowired
	LocationRepository locationRepository;

	public LocationService(LocationRepository locationRepository) {

		super();
		this.locationRepository = locationRepository;
	}

	public ArrayList<Location> getAllLocation() {
		ArrayList<Location> location = new ArrayList<>();
		locationRepository.findAll().forEach(location::add);
		return location;
	}

	public ArrayList<Location> getAllLocation(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
		ArrayList<Location> location = new ArrayList<>();
		locationRepository.findAll().forEach(location::add);
		return location;
	}

//	public long addLocation(Location location) {
//		return locationRepository.save(location).getId();
//	}

	public Location getById(long id) {
		return locationRepository.findById(id);
	}

	public long locationValueParse() {

		Location location = new Location();

		ApiUtility apiUtility = new ApiUtility();
		String responseBody = (String) apiUtility.dataRetriver();

		JSONObject obj = new JSONObject(responseBody);

		JSONObject weatherBody = obj.getJSONObject("current_weather");

		location.setPer_temperature(weatherBody.get("temperature").toString());

		location.setWind_speed(weatherBody.get("windspeed").toString());
		location.setWind_direction(weatherBody.get("winddirection").toString());

		location.setWeather_code(weatherBody.get("weathercode").toString());
		// weather.s(weatherBody.get("is_day").toString());
		location.setDay(weatherBody.get("time").toString());

		location.setLatitude(obj.get("latitude").toString());

		location.setLongitude(obj.get("longitude").toString());
		location.setGenerationTime(obj.get("generationtime_ms").toString());

		location.setLocationTimezone(obj.get("timezone").toString());
		location.setElevation(obj.get("elevation").toString());

		location.setFavourite("yes");

		long locationId = locationRepository.save(location).getId();

		return locationId;

	}
}