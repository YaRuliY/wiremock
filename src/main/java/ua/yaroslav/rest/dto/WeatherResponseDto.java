package ua.yaroslav.rest.dto;


import ua.yaroslav.rest.json.WeatherResponse;

public class WeatherResponseDto {
    private String name;
    private String description;
    private double temperature;
    private double tempMax;
    private double tempMin;
    private double pressure;
    private double windSpeed;

    public WeatherResponseDto() {

    }

    public WeatherResponseDto(String name, int temperature, int pressure, int tempMax, int tempMin, int windSpeed, String description) {
        this.name = name;
        this.temperature = temperature;
        this.pressure = pressure;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.windSpeed = windSpeed;
        this.description = description;
    }

    public WeatherResponseDto(WeatherResponse response) {
        this.name = response.getName();
        this.temperature = response.getMain().getTemp();
        this.pressure = response.getMain().getPressure();
        this.tempMax = response.getMain().getTemp_max();
        this.tempMin = response.getMain().getTemp_min();
        this.windSpeed = response.getWind().getSpeed();
        this.description = response.getWeather()[0].getMain();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WeatherResponseDto {" + "name='" + name + '\''
                + ", description='" + description + '\''
                + ", temperature=" + temperature
                + ", tempMax=" + tempMax + ", tempMin=" + tempMin
                + ", pressure=" + pressure + ", windSpeed=" + windSpeed + '}';
    }
}