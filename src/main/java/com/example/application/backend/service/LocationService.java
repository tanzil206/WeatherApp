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
	@Autowired
	CityRepository cityRepository;
	@Autowired
	CityService cityService;

	public LocationService(LocationRepository locationRepository) {

		super();
		this.locationRepository = locationRepository;
	}

	public ArrayList<Location> getAllLocation() {
		ArrayList<Location> location = new ArrayList<>();
		locationRepository.findAll().forEach(location::add);
		return location;
	}

	public ArrayList<Location> getDailyForecast() {
		ArrayList<Location> locationList = new ArrayList<>();
		Location location = new Location();
		List<String> distinctCity = locationRepository.findDistinctCity_name();

		for (int i = 0; i < distinctCity.size(); i++) {

			long locationLd = locationRepository.findIdByCity_name(distinctCity.get(i));

			location = locationRepository.findById(locationLd);
			locationList.add(location);
		}

		return locationList;
	}

	public void updatefavourite(String city_name) {

		City city = cityRepository.findByCity_name(city_name);

		city.setFavourite("yes");

		cityRepository.save(city);

	}

	public Location getById(long id) {
		return locationRepository.findById(id);
	}

	public void jsonValueParse() {

		ArrayList<City> cityList = cityService.getAllCity();
		ApiUtility apiUtility = new ApiUtility();
		if (cityList.size() > 0) {

			for (int c = 0; c < cityList.size(); c++) {
				Location location = new Location();
				String latitude = cityList.get(c).getLatitude();
				String longitude = cityList.get(c).getLongitude();

				String responseBody = (String) apiUtility.weatherDataRetriver(latitude, longitude);

				JSONObject obj = new JSONObject(responseBody);

				location.setCity_name(cityList.get(c).getCity_name());

				JSONObject weatherBody = obj.getJSONObject("current_weather");

				location.setPer_temperature(weatherBody.get("temperature").toString());
				location.setWind_speed(weatherBody.get("windspeed").toString());
				location.setWind_direction(weatherBody.get("winddirection").toString());
				location.setWeather_code(weatherBody.get("weathercode").toString());
				location.setTime(weatherBody.get("time").toString());
				location.setGenerationTime(obj.get("generationtime_ms").toString());

				String[] parts = weatherBody.get("time").toString().split("T");
				String date = parts[0];
				location.setDate(date);

				location.setLocationTimezone(obj.get("timezone").toString());
				location.setElevation(obj.get("elevation").toString());

				locationRepository.save(location);
				JSONObject hourBody = obj.getJSONObject("hourly");

				String[] per_hour = hourBody.getJSONArray("time").toString().split(",");
				String[] per_temp = hourBody.getJSONArray("temperature_2m").toString().split(",");
				String[] per_humidity = hourBody.getJSONArray("relativehumidity_2m").toString().split(",");
				String[] per_wind = hourBody.getJSONArray("windspeed_10m").toString().split(",");
				String[] per_rain = hourBody.getJSONArray("rain").toString().split(",");

				for (int t = 0; t < per_hour.length; t++) {
					Hour hour = new Hour();
					String time = per_hour[t].toString();
					String temp = per_temp[t].toString();
					String humidity = per_humidity[t].toString();
					String wind = per_wind[t].toString();
					String rain = per_rain[t].toString();
					String[] hourPart = time.split("T");
					String dateTime = hourPart[0];
					String hourTime = hourPart[1];
					if (t == 0) {
						dateTime = dateTime.startsWith("[") ? dateTime.substring(1) : dateTime;
						hour.setDate(dateTime.substring(1, dateTime.length() - 1));
						hour.setTime(hourTime.substring(0, hourTime.length() - 6));
						hour.setTempLevel(temp.startsWith("[") ? temp.substring(1) : temp);
						hour.setHumidityLevel(humidity.startsWith("[") ? humidity.substring(1) : humidity);
						hour.setWindSpeedLevel(wind.startsWith("[") ? wind.substring(1) : wind);
						hour.setRain(rain.startsWith("[") ? rain.substring(1) : rain);
					} else {
						hour.setCity_name(cityList.get(c).getCity_name());
						hour.setDate(dateTime.substring(1, dateTime.length() - 1));
						hour.setTime(hourTime.substring(0, hourTime.length() - 6));
						hour.setTempLevel(temp);
						hour.setHumidityLevel(humidity);
						hour.setWindSpeedLevel(wind);
						hour.setRain(rain);
						hourRepository.save(hour);
					}
				}
			}

		}

	}
}
