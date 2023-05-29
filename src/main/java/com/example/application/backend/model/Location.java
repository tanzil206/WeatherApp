package com.example.application.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "longitude")
	private String longitude;
	@Column(name = "temperature")
	private String per_temperature;
	@Column(name = "wind_speed")
	private String wind_speed;
	@Column(name = "wind_direction")
	private String wind_direction;
	@Column(name = "weather_code")
	private String weather_code;
	@Column(name = "is_day")
	private String dateType;
	@Column(name = "time")
	private String time;
	@Column(name = "generation_time")
	private String generationTime;
	@Column(name = "location_timezone")
	private String locationTimezone;
	@Column(name = "elevation")
	private String elevation;
	@Column(name = "favourite")
	private String favourite;
	@Column(name = "date")
	private String date;
	@ManyToOne
	Temperature temperature;
	@ManyToOne
	Humidity humidity;
	@ManyToOne
	WindSpeed windspeed;
	@ManyToOne
	Unit unit;

	public Location() {

		super();
	}

//	public Location(String latitude, String longitude, String per_temperature, String wind_speed, String wind_direction,
//			String weather_code, String dateType, String time, String generationTime, String locationTimezone,
//			String elevation, String favourite, Hour hour, Temperature temperature, Humidity humidity,
//			WindSpeed windspeed, Unit unit,String date) {
//		super();
//		this.latitude = latitude;
//		this.longitude = longitude;
//		this.per_temperature = per_temperature;
//		this.wind_speed = wind_speed;
//		this.wind_direction = wind_direction;
//		this.weather_code = weather_code;
//		this.dateType = dateType;
//		this.time = time;
//		this.generationTime = generationTime;
//		this.locationTimezone = locationTimezone;
//		this.elevation = elevation;
//		this.favourite = favourite;
//		this.hour = hour;
//		this.temperature = temperature;
//		this.humidity = humidity;
//		this.windspeed = windspeed;
//		this.unit = unit;
//		this.date=date;
//	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPer_temperature() {
		return per_temperature;
	}

	public void setPer_temperature(String per_temperature) {
		this.per_temperature = per_temperature;
	}

	public String getWind_speed() {
		return wind_speed;
	}

	public void setWind_speed(String wind_speed) {
		this.wind_speed = wind_speed;
	}

	public String getWind_direction() {
		return wind_direction;
	}

	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}

	public String getWeather_code() {
		return weather_code;
	}

	public void setWeather_code(String weather_code) {
		this.weather_code = weather_code;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFavourite() {
		return favourite;
	}

	public void setFavourite(String favourite) {
		this.favourite = favourite;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(String generationTime) {
		this.generationTime = generationTime;
	}

	public String getLocationTimezone() {
		return locationTimezone;
	}

	public void setLocationTimezone(String locationTimezone) {
		this.locationTimezone = locationTimezone;
	}

	public String getElevation() {
		return elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

//	public Hour getHour() {
//		return hour;
//	}
//
//	public void setHour(Hour hour) {
//		this.hour = hour;
//	}

	public Temperature getTemperature() {
		return temperature;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

	public Humidity getHumidity() {
		return humidity;
	}

	public void setHumidity(Humidity humidity) {
		this.humidity = humidity;
	}

	public WindSpeed getWindspeed() {
		return windspeed;
	}

	public void setWindspeed(WindSpeed windspeed) {
		this.windspeed = windspeed;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
