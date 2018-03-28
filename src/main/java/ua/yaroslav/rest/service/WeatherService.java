package ua.yaroslav.rest.service;

import ua.yaroslav.rest.dto.WeatherResponse;
import ua.yaroslav.rest.exception.WeatherException;

import java.io.IOException;

public interface WeatherService {
    WeatherResponse getWeather(String city) throws IOException, WeatherException;
}