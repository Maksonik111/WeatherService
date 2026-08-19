package com.maks.weather_service.service;


import com.maks.weather_service.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${openweather.api.url}")
    private String apiURL;

    @Value("${openweather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public WeatherResponseDto getWeatherInCity(String city){

        String url = UriComponentsBuilder
                .fromUriString(apiURL)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .queryParam("lang", "ru")
                .build()
                .toUriString();

        ResponseEntity<Map> result = restTemplate.getForEntity(url, Map.class);

        HttpStatus status = (HttpStatus) result.getStatusCode();

        if(!status.is2xxSuccessful()){
            throw new RuntimeException("Возникла ошибка:" + status);
        }

        Map body = result.getBody();
        if(body.isEmpty()){
            throw new RuntimeException("Тело ответа пустое");
        }

        return convertToDto(body, city);

    }

    public WeatherResponseDto convertToDto(Map body, String city){
        WeatherResponseDto dtoObj = new WeatherResponseDto();

        Map<String, Object> main = (Map<String, Object>) body.get("main");
        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) body.get("weather");
        Map<String, Object> weather = weatherList.get(0);
        Map<String, Object> wind = (Map<String, Object>) body.get("wind");
        Map<String, Object> sys = (Map<String, Object>) body.get("sys");

        dtoObj.setCity(city);
        dtoObj.setCountry((String) sys.get("country"));
        dtoObj.setTemperature((float) main.get("temp"));
        dtoObj.setFeelsLike((float) main.get("feels_like"));
        dtoObj.setHumidity((Integer) main.get("humidity"));
        dtoObj.setPressure((Integer) main.get("pressure"));
        dtoObj.setDescription((String) weather.get("description"));
        dtoObj.setWindSpeed((float) wind.get("speed"));
        dtoObj.setIcon((String) weather.get("icon"));
        dtoObj.setLastUpdated(Instant.now());


        return dtoObj;
    }

    
}
