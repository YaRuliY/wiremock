package ua.yaroslav.rest.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ua.yaroslav.rest.json.subobject.Main;
import ua.yaroslav.rest.json.subobject.Weather;
import ua.yaroslav.rest.json.subobject.Wind;

@JsonIgnoreProperties(value = { "coord", "base", "clouds", "dt", "sys", "id", "cod"})
public class WeatherResponse {
    private String name;
    private String visibility;
    private Main main;
    private Wind wind;
    private Weather[] weather;

    public WeatherResponse(){

    }

    public WeatherResponse(String name, String visibility, Main main, Wind wind, Weather[] weather) {
        this.name = name;
        this.visibility = visibility;
        this.main = main;
        this.wind = wind;
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}