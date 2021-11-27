package com.collections;

import java.util.Objects;

public class Car {
    private final String model;
    private final int price;
    private final int speed;

    public Car(String model, int price, int speed) {
        this.model = model;
        this.price = price;
        this.speed = speed;
    }

    public String getmodel() {
        return model;
    }

    public int getprice() {
        return price;
    }

    public int getspeed() {
        return speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car employee = (Car) o;
        return price == employee.price && speed == employee.speed && Objects.equals(model, employee.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, price, speed);
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", speed=" + speed +
                "}\n";
    }
}
