package ua.yaroslav.rest.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ua.yaroslav.rest.dto.WeatherResponse;
import ua.yaroslav.rest.exception.WeatherException;
import ua.yaroslav.rest.service.WeatherService;

import java.io.IOException;
import java.util.HashMap;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;
    private final HashMap<String, String> vocabulary;

    @Value("${weather.url}")
    private String url;
    @Value("${weather.appid}")
    private String appid;


    public WeatherServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
        this.restTemplate = new RestTemplate();
        this.vocabulary = new HashMap<>();
        this.vocabulary.put("Snow", "Снег");
        this.vocabulary.put("Rain", "Дождь");
        this.vocabulary.put("Clear", "Ясно");
        this.vocabulary.put("Clouds", "Облачно");
        this.vocabulary.put("Kyiv", "Киев");
        this.vocabulary.put("London", "Киев");
        this.vocabulary.put("Tokio", "Токио");
    }


    @Override
    public WeatherResponse getWeather(String city) throws IOException, WeatherException {
        String weather = url + "?q=" + city + "&appid=" + appid;
        System.out.println("URL: " + weather);

        ResponseEntity<String> entity;
        try {
            entity = restTemplate.getForEntity(weather, String.class);
        } catch (HttpClientErrorException e) {
            throw mapper.readValue(e.getResponseBodyAsString(), WeatherException.class);
        }

        WeatherResponse wr = mapper.readValue(entity.getBody(), WeatherResponse.class);
        if (this.vocabulary.containsKey(wr.getName())) {
            wr.setName(this.vocabulary.get(wr.getName()));
        }
        if (this.vocabulary.containsKey(wr.getWeather()[0].getMain())) {
            wr.getWeather()[0].setMain(this.vocabulary.get(wr.getWeather()[0].getMain()));
        }
        return wr;
    }
}