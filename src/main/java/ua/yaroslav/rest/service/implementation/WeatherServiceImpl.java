package ua.yaroslav.rest.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.yaroslav.rest.dto.WeatherResponse;
import ua.yaroslav.rest.service.WeatherService;

import java.io.IOException;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;
    @Value("${weather.url}")
    private String url;
    @Value("${weather.appid}")
    private String appid;

    public WeatherServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public WeatherResponse getWeather(String city) throws IOException {
        String weather = url + "?q=" + city + "&appid=" + appid;
        System.out.println("URL: " + weather);
        String s = restTemplate.getForEntity(weather, String.class).getBody();
        return mapper.readValue(s, WeatherResponse.class);
    }
}