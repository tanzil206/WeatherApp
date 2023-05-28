package com.example.application.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "wind_speed")
public class WindSpeed {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "windspeed_level")
	private String windSpeedLevel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWindSpeedLevel() {
		return windSpeedLevel;
	}

	public void setWindSpeedLevel(String windSpeedLevel) {
		this.windSpeedLevel = windSpeedLevel;
	}

}
