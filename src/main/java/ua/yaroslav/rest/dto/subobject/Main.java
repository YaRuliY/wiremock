package ua.yaroslav.rest.dto.subobject;

public class Main {
    private double temp;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;

    public Main(){

    }

    public Main(double temp, double pressure, double humidity, double temp_min, double temp_max) {
        this.temp = temp - 273.16;
        this.pressure = pressure / 1.38;
        this.humidity = humidity;
        this.temp_min = temp_min - 273.16;
        this.temp_max = temp_max - 273.16;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = Math.round(temp - 273.16);
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = Math.round(pressure / 1.38);
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = Math.round(temp_min - 273.16);
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = Math.round(temp_max - 273.16);
    }
}