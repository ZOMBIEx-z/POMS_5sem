package org.lw1;

import java.util.Objects;

public abstract class Car {
    private String brand; // Марка
    private String model; // Модель
    private double price; // Цена в USD
    private int maxSpeed; // Максимальная скорость в км/ч
    private double fuelConsumption; // Расход топлива в л/100км

    public static final String FUEL_UNIT = "л/100км";

    public Car(String brand, String model, double price, int maxSpeed, double fuelConsumption) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.maxSpeed = maxSpeed;
        this.fuelConsumption = fuelConsumption;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    // Методы Setters (установка значений)
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public void setMaxSpeed(int maxSpeed) {
        if (maxSpeed > 0) {
            this.maxSpeed = maxSpeed;
        }
    }

    public void setFuelConsumption(double fuelConsumption) {
        if (fuelConsumption >= 0) {
            this.fuelConsumption = fuelConsumption;
        }
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 && maxSpeed == car.maxSpeed && Double.compare(car.fuelConsumption, fuelConsumption) == 0 && Objects.equals(brand, car.brand) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, price, maxSpeed, fuelConsumption);
    }
}
