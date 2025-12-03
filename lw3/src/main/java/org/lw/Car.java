// java
package org.lw;

import java.util.Objects;
import java.util.UUID;

public abstract class Car {
    private final String id;
    private String brand; // Марка
    private String model; // Модель
    private double price; // Цена в USD
    private int maxSpeed; // Максимальная скорость в км/ч
    private double fuelConsumption; // Расход топлива в л/100км

    public static final String FUEL_UNIT = "л/100км";

    public Car(String brand, String model, double price, int maxSpeed, double fuelConsumption) throws InvalidCarParameterException {
        this.id = UUID.randomUUID().toString();
        this.setBrand(brand);
        this.setModel(model);
        this.setPrice(price);
        this.setMaxSpeed(maxSpeed);
        this.setFuelConsumption(fuelConsumption);
    }

    public String getId() {
        return id;
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

    public void setPrice(double price) throws InvalidCarParameterException {
        if (price > 0) {
            this.price = price;
        } else {
            throw new InvalidCarParameterException("Price must be positive.");
        }
    }

    public void setMaxSpeed(int maxSpeed) throws InvalidCarParameterException {
        if (maxSpeed > 0) {
            this.maxSpeed = maxSpeed;
        } else {
            throw new InvalidCarParameterException("Max speed must be positive.");
        }
    }

    public void setFuelConsumption(double fuelConsumption) throws InvalidCarParameterException {
        if (fuelConsumption >= 0) {
            this.fuelConsumption = fuelConsumption;
        } else {
            throw new InvalidCarParameterException("Fuel consumption cannot be negative.");
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