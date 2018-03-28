package ua.yaroslav.rest.dto;

public class WeatherError {
    private String message;
    private int cod;

    public WeatherError(){

    }

    public WeatherError(String message, int cod) {
        this.message = message;
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
