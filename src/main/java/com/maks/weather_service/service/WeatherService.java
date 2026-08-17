package com.maks.weather_service.service;


import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private String city;

    public void getCity(String city){
        this.city = city;
    }
}
