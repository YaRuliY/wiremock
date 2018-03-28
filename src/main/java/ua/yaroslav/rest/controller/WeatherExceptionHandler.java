package ua.yaroslav.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.yaroslav.rest.dto.WeatherErrorDto;
import ua.yaroslav.rest.exception.WeatherException;

import java.io.IOException;

@ControllerAdvice
public class WeatherExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(WeatherExceptionHandler.class);

    @ExceptionHandler(WeatherException.class)
    public ResponseEntity<?> handleException(WeatherException we) {
        logger.error(we.toString());
        return ResponseEntity.badRequest().body(new WeatherErrorDto(we.getMessage(), we.getCode()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleException(IOException ioe) {
        logger.error(ioe.toString());
        return ResponseEntity.badRequest().body(new WeatherErrorDto(ioe.getMessage(), 400));
    }
}