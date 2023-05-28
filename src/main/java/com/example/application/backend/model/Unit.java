package com.example.application.backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hourly_unit")
public class Unit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "time_zone")
	private String timeZone;
	@Column(name = "temperature_unit")
	private String temperatureUnit;
	@Column(name = "humidity_unit")
	private String humidityUnit;
	@Column(name = "windspeed_unit")
	private String windspeedUnit;

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTemperatureUnit() {
		return temperatureUnit;
	}

	public void setTemperatureUnit(String temperatureUnit) {
		this.temperatureUnit = temperatureUnit;
	}

	public String getHumidityUnit() {
		return humidityUnit;
	}

	public void setHumidityUnit(String humidityUnit) {
		this.humidityUnit = humidityUnit;
	}

	public String getWindspeedUnit() {
		return windspeedUnit;
	}

	public void setWindspeedUnit(String windspeedUnit) {
		this.windspeedUnit = windspeedUnit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
