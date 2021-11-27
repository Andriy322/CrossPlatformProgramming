package com.company;
import java.util.Objects;

public class Car {
    private final String model;
    private final int price;
    private final int maxSpeed;

    public Car(String model, int price, int maxSpeed) {
        this.model = model;
        this.price = price;
        this.maxSpeed = maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

}

