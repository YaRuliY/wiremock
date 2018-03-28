package ua.yaroslav.rest.dto;

public class WeatherErrorDto {
    private String message;
    private int code;

    public WeatherErrorDto() {

    }

    public WeatherErrorDto(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCod() {
        return code;
    }

    public void setCod(int cod) {
        this.code = cod;
    }
}
