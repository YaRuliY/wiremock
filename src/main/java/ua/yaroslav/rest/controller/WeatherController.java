package ua.yaroslav.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.yaroslav.rest.dto.WeatherResponseDto;
import ua.yaroslav.rest.service.WeatherService;

import java.io.IOException;

@RestController
public class WeatherController {
    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/weather/{city}")
    public WeatherResponseDto getData(@PathVariable("city") String city) throws IOException {
        return this.service.getWeather(city);
    }
}