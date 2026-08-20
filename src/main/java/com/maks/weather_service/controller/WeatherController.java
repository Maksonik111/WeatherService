package com.maks.weather_service.controller;

import com.maks.weather_service.dto.WeatherResponseDto;
import com.maks.weather_service.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
        System.out.println("КОНСТРУКТОР КОНТРОЛЛЕРА ВЫЗВАН!");
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam(required = false) String city, Model model){
        System.out.println("Контроллер запущен");

        if(city != null){
            System.out.println("Пользователь ввел:" + city);
            try{
                WeatherResponseDto weatherDto = weatherService.getWeatherInCity(city);
                model.addAttribute("weather", weatherDto);
            }
            catch (Exception e){
                System.out.println("Не удалось получить погоду для города" + city);
                System.out.println("Ошибка:" + e.getMessage());
            }

        }

        return "weather";
    }


}
