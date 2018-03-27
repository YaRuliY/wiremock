package ua.yaroslav.rest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    @Value("${weather.url}")
    private String url;
    @Value("${weather.appid}")
    private String appid;

    private final RestTemplate restTemplate;

    public WeatherController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<String> getData(@PathVariable("city") String city){
        String weather = url + "?q=" + city + "&appid=" + appid;
        System.out.println("URL: " + weather);
        return restTemplate.getForEntity(weather, String.class);
    }
}