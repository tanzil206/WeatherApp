package com.example.application.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hour")
public class Hour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "time")
    private String time;
    @Column(name = "date")
    private String date;
    @Column(name = "city_name")
	private String city_name;
    @Column(name = "humidity_level")
    private String humidityLevel;
    @Column(name = "temp_level")
    private String tempLevel;
    @Column(name = "windspeed_level")
    private String windSpeedLevel;
    @Column(name = "rain")
    private String rain;

    public String getHumidityLevel() {
        return humidityLevel;
    }

    public void setHumidityLevel(String humidityLevel) {
        this.humidityLevel = humidityLevel;
    }

    public String getTempLevel() {
        return tempLevel;
    }

    public void setTempLevel(String tempLevel) {
        this.tempLevel = tempLevel;
    }

    public String getWindSpeedLevel() {
        return windSpeedLevel;
    }

    public void setWindSpeedLevel(String windSpeedLevel) {
        this.windSpeedLevel = windSpeedLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

}
