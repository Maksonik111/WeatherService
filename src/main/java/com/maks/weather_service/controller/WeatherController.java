package com.maks.weather_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WeatherController {

    @GetMapping("/weather")
    public String getWeather(@RequestParam(required = false) String city, Model model){

        if(city != null){
            System.out.println("Пользователь ввел:" + city);


        }

        return "weather";

    }


}
