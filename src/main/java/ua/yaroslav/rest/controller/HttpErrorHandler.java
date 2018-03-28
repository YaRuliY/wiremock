package ua.yaroslav.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.ResponseErrorHandler;
import ua.yaroslav.rest.exception.WeatherException;

import java.io.IOException;

@ControllerAdvice
public class HttpErrorHandler implements ResponseErrorHandler {
    private final ObjectMapper mapper;

    public HttpErrorHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        WeatherException weatherException = mapper.readValue(response.getBody(), WeatherException.class);
        throw weatherException;
    }
}
