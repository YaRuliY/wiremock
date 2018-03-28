package ua.yaroslav.rest.exception;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherException extends RuntimeException {
    private String message;
    private int code;

    public WeatherException() {

    }

    public WeatherException(String message, int cod) {
        this.message = message;
        this.code = cod;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonGetter("cod")
    public int getCode() {
        return code;
    }

    @JsonSetter("cod")
    public void setCode(int cod) {
        this.code = cod;
    }

    @Override
    public String toString() {
        return "WeatherException {" + "message='" + message + '\'' + ", code=" + code + '}';
    }
}