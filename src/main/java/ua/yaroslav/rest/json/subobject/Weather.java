package ua.yaroslav.rest.json.subobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "icon", "id", "description" })
public class Weather {
    private String main;

    public Weather(){

    }

    public Weather(int id, String main, String description) {
        this.main = main;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}