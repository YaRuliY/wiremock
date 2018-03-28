package ua.yaroslav.rest.json.subobject;

public class Wind {
    private int speed;
    private int deg;

    public Wind(){

    }

    public Wind(int speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}