package com.example.application.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "temperature")
public class Temperature {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;
  @Column(name = "temp_level")
  private String tempLevel;
	@ManyToOne
	Hour hour;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

public String getTempLevel() {
	return tempLevel;
}

public void setTempLevel(String tempLevel) {
	this.tempLevel = tempLevel;
}

public Hour getHour() {
	return hour;
}

public void setHour(Hour hour) {
	this.hour = hour;
}

}
