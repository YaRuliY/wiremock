package ua.yaroslav.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.yaroslav.rest.dto.WeatherError;
import ua.yaroslav.rest.exception.WeatherException;

@ControllerAdvice
public class WeatherExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(WeatherExceptionHandler.class);

    @ExceptionHandler(WeatherException.class)
    public ResponseEntity<?> handleException(WeatherException we) {
        logger.error(we.toString());
        return ResponseEntity.badRequest().body(new WeatherError(we.getMessage(), we.getCod()));
    }
}