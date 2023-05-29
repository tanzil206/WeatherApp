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
public class LocationService {

	@Autowired
	LocationRepository locationRepository;
	@Autowired
	HourRepository hourRepository;
	@Autowired
	TemperatureRepository temperatureRepository;
	@Autowired
	HumidityRepository humidityRepository;

	public LocationService(LocationRepository locationRepository) {

		super();
		this.locationRepository = locationRepository;
	}

	public ArrayList<Location> getAllLocation() {
		ArrayList<Location> location = new ArrayList<>();
		locationRepository.findAll().forEach(location::add);
		return location;
	}

//	public ArrayList<Location> getHourlyForecast(String date) {
//		ArrayList<Location> locationList = new ArrayList<>();
//		Location location = new Location();
//		List<String> distinctTime = locationRepository.getDistinctTimeByDate(date);
//		for (int i = 0; i < distinctTime.size(); i++) {
//
//			location = locationRepository.findByTime(distinctTime.get(i));
//			locationList.add(location);
//		}
//
//		return locationList;
//	}

	public ArrayList<Location> getDailyForecast() {
		ArrayList<Location> locationList = new ArrayList<>();
		Location location = new Location();
		List<String> distinctDate = locationRepository.findDistinctDate();

		for (int i = 0; i < distinctDate.size(); i++) {

			long locationLd = locationRepository.findIdByDate(distinctDate.get(i));
			
			location =locationRepository.findById(locationLd);
			locationList.add(location);
		}

		return locationList;
	}

	public void updatefavourite(long id) {

		Location loc = locationRepository.findById(id);

		loc.setFavourite("yes");

		locationRepository.save(loc);

	}

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
		location.setTime(weatherBody.get("time").toString());

		location.setLatitude(obj.get("latitude").toString());

		location.setLongitude(obj.get("longitude").toString());
		location.setGenerationTime(obj.get("generationtime_ms").toString());

		String[] parts = weatherBody.get("time").toString().split("T");
		String date = parts[0];
		location.setDate(date);	

		location.setLocationTimezone(obj.get("timezone").toString());
		location.setElevation(obj.get("elevation").toString());

		location.setFavourite("no");

		long locationId = locationRepository.save(location).getId();
		JSONObject hourBody = obj.getJSONObject("hourly");
		
		JSONArray per_hour =hourBody.getJSONArray("time");
		JSONArray per_temp =hourBody.getJSONArray("temperature_2m");
		JSONArray per_humidity =hourBody.getJSONArray("relativehumidity_2m");
		JSONArray per_wind =hourBody.getJSONArray("windspeed_10m");
		Hour hour = new Hour();
		for(int t =0 ;t<per_hour.length();t++){
			String time =per_hour.get(t).toString();
			String temp =per_temp.get(t).toString();
			String humidity =per_humidity.get(t).toString();
			String wind =per_wind.get(t).toString();
			String[] hourPart = time.split("T");
			String dateTime = hourPart[0];
			hour.setDate(dateTime);
			hour.setTime(time);
			hour.setTempLevel(temp);
			hour.setHumidityLevel(humidity);
			hour.setWindSpeedLevel(wind);
			hourRepository.save(hour).getId();
		}
		

		return locationId;

	}
}