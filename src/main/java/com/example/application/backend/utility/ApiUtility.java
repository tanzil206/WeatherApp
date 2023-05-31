package com.example.application.backend.utility;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiUtility {

    PropertyReader propertyReader = new PropertyReader();

    public ApiUtility() {
        super();
        //    init();
    }

//    private void init() {
//        weatherDataRetriver();
//    }
    public String weatherDataRetriver(String latitude, String longitude) {

        HttpResponse<String> response = null;
        try {
            String forecastUrl = propertyReader.loadPropertiesValues("weather.forecast.api.url") + latitude + "&longitude=" + longitude + propertyReader.loadPropertiesValues("weather.forecast.api.param");
            System.out.println(forecastUrl);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(forecastUrl))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();;

            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());

        return response.body();
    }

    public String cityNameRetriver(String cityName) {

        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(propertyReader.loadPropertiesValues("https://api.api-ninjas.com/v1/geocoding?city=") + cityName)).
                    headers("X-Api-Key", propertyReader.loadPropertiesValues("city.name.api.key"))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();

            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());

        return response.body();
    }
}
