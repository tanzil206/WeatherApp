package com.example.application.backend.utility;

import com.example.application.backend.model.Hour;
import com.example.application.backend.model.Location;

public class SearchFilter {
	private String searchTerm;

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public boolean checkLocation(Location location) {
		boolean matchesCity = matches(location.getCity_name(), searchTerm);
		boolean matchesTemp = matches(location.getPer_temperature(), searchTerm);
		boolean matchesWindSpeed = matches(location.getWind_speed(), searchTerm);
		return matchesCity || matchesTemp || matchesWindSpeed;
	}

	public boolean checkHour(Hour hour) {
		boolean matchesDate = matches(hour.getDate(), searchTerm);
		boolean matchesTemp = matches(hour.getTempLevel(), searchTerm);
		boolean matchesHumidity = matches(hour.getHumidityLevel(), searchTerm);
		boolean matchesWindSpeed = matches(hour.getWindSpeedLevel(), searchTerm);
		return matchesDate || matchesTemp || matchesHumidity || matchesWindSpeed;
	}

	private boolean matches(String value, String searchTerm) {
		return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
	}
}
