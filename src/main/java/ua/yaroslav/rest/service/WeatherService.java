package ua.yaroslav.rest.service;

import ua.yaroslav.rest.dto.WeatherResponseDto;
import ua.yaroslav.rest.exception.WeatherException;

import java.io.IOException;

public interface WeatherService {
    WeatherResponseDto getWeather(String city) throws IOException, WeatherException;
}