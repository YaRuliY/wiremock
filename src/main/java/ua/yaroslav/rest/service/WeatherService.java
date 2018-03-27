package ua.yaroslav.rest.service;

import ua.yaroslav.rest.dto.WeatherResponse;

import java.io.IOException;

public interface WeatherService {
    WeatherResponse getWeather(String city) throws IOException;
}