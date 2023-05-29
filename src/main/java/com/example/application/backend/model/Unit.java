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
	@Column(name = "weather_type")
	private String weatherType;
	@Column(name = "weather_unit")
	private String weatherUnit;




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}

	public String getWeatherUnit() {
		return weatherUnit;
	}

	public void setWeatherUnit(String weatherUnit) {
		this.weatherUnit = weatherUnit;
	}
	
	
}
