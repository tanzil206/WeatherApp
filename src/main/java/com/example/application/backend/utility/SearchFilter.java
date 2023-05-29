package com.example.application.backend.utility;

import com.example.application.backend.model.Hour;
import com.example.application.backend.model.Location;

public class SearchFilter {
	private String searchTerm;

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public boolean test(Location location) {
        boolean matchesLocation = matches(location.getLatitude(), searchTerm);
        boolean matchesTemp = matches(location.getPer_temperature(), searchTerm);
        boolean matchesWindSpeed = matches(location.getWind_speed(), searchTerm);
        return matchesLocation || matchesTemp || matchesWindSpeed;
    }
    public boolean test(Hour hour) {
        boolean matchesDate = matches(hour.getDate(), searchTerm);
        boolean matchesTemp = matches(hour.getTempLevel(), searchTerm);
        boolean matchesHumidity = matches(hour.getHumidityLevel(), searchTerm);
        boolean matchesWindSpeed = matches(hour.getWindSpeedLevel(), searchTerm);
        return matchesDate || matchesTemp || matchesHumidity ||matchesWindSpeed;
    }

    private boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty()
                || value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
