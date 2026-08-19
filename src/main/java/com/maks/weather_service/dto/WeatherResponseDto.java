package com.maks.weather_service.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class WeatherResponseDto {

    private String city;
    private String country;
    private Float temperature;
    private Float feelsLike;
    private Integer humidity;
    private Integer pressure;
    private String description;
    private Float windSpeed;
    private String icon;
    private Instant lastUpdated;

    public WeatherResponseDto() {
    }

}
