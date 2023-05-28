package com.example.application.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "humidity")
public class Humidity {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;
  @Column(name = "humidity_level")
  private String humidityLevel;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

public String getHumidityLevel() {
	return humidityLevel;
}

public void setHumidityLevel(String humidityLevel) {
	this.humidityLevel = humidityLevel;
}
}
