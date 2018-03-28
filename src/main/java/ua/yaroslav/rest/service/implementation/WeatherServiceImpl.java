package ua.yaroslav.rest.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.yaroslav.rest.controller.HttpErrorHandler;
import ua.yaroslav.rest.dto.WeatherResponseDto;
import ua.yaroslav.rest.json.WeatherResponse;
import ua.yaroslav.rest.exception.WeatherException;
import ua.yaroslav.rest.service.WeatherService;

import java.io.IOException;
import java.util.HashMap;

@Service
public class WeatherServiceImpl implements WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);
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
        this.restTemplate.setErrorHandler(new HttpErrorHandler(mapper));
        this.vocabulary = new HashMap<>();
        this.vocabulary.put("Snow", "Снег");
        this.vocabulary.put("Rain", "Дождь");
        this.vocabulary.put("Clear", "Ясно");
        this.vocabulary.put("Clouds", "Облачно");
        this.vocabulary.put("Kyiv", "Киев");
        this.vocabulary.put("London", "Лондон");
        this.vocabulary.put("Tokio", "Токио");
    }


    @Override
    public WeatherResponseDto getWeather(String city) throws IOException, WeatherException {
        String weather = url + "?q=" + city + "&appid=" + appid;
        logger.info("URL: " + weather);

        ResponseEntity<String> entity = restTemplate.getForEntity(weather, String.class);
        WeatherResponse wr = mapper.readValue(entity.getBody(), WeatherResponse.class);

        WeatherResponseDto responseDto = new WeatherResponseDto(wr);
        return transformResponse(responseDto);
    }

    private WeatherResponseDto transformResponse(WeatherResponseDto responseDto){
        String name = responseDto.getName();
        String description = responseDto.getDescription();

        if (this.vocabulary.containsKey(name)) {
            responseDto.setName(this.vocabulary.get(name));
        }
        if (this.vocabulary.containsKey(description)) {
            responseDto.setDescription(this.vocabulary.get(description));
        }

        responseDto.setTemperature(responseDto.getTemperature() - 271.16);
        responseDto.setTempMax(responseDto.getTempMax() - 271.16);
        responseDto.setTempMin(responseDto.getTempMin() - 271.16);
        responseDto.setPressure(responseDto.getPressure() * 1.38);
        return responseDto;
    }
}