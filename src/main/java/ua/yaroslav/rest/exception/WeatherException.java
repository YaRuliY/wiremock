package ua.yaroslav.rest.exception;

public class WeatherException extends RuntimeException{
    private String message;
    private int cod;

    public WeatherException(){

    }

    public WeatherException(String message, int cod) {
        this.message = message;
        this.cod = cod;
    }

    @Override
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

    @Override
    public String toString() {
        return "WeatherException {" + "message='" + message + '\'' + ", cod=" + cod + '}';
    }
}