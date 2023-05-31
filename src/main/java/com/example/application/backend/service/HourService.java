package com.example.application.backend.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.application.backend.model.Hour;
import com.example.application.backend.repository.HourRepository;


@Service
public class HourService {

	@Autowired
	HourRepository hourRepository;

	public HourService(HourRepository hourRepository) {

		super();
		this.hourRepository = hourRepository;
	}

	public ArrayList<Hour> getAllHour() {
		ArrayList<Hour> hour = new ArrayList<>();
		hourRepository.findAll().forEach(hour::add);
		return hour;
	}

	public ArrayList<Hour> getHourlyForecast(String cityName, String date) {
		List<Hour> hour = new ArrayList<>();
		try {
			hour = hourRepository.findByCity_nameAndDate(cityName, date);
		} catch (NullPointerException ex) {

			System.out.print("Exception" + ex.getMessage());
		}
		return (ArrayList<Hour>) hour;
	}

}