package com.maks.weather_service.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class WeatherResponseDto {

    private String city;
    private String country;
    private Double temperature;
    private Double feelsLike;
    private Integer humidity;
    private Integer pressure;
    private String description;
    private Double windSpeed;
    private String icon;
    private Instant lastUpdated;

    public WeatherResponseDto() {
    }

}
