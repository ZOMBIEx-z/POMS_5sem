package org.lw;

import java.util.Objects;

public abstract class PassengerCar extends Car {
    private int passengerCapacity; // Пассажировместимость
    private String bodyType; // Тип кузова (Седан, Универсал, Минивэн и т.д.)

    public PassengerCar(String brand, String model, double price, int maxSpeed, double fuelConsumption, int passengerCapacity, String bodyType) throws InvalidCarParameterException {
        super(brand, model, price, maxSpeed, fuelConsumption);
        this.passengerCapacity = passengerCapacity;
        this.bodyType = bodyType;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        if (passengerCapacity > 0) {
            this.passengerCapacity = passengerCapacity;
        }
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PassengerCar that = (PassengerCar) o;
        return passengerCapacity == that.passengerCapacity && Objects.equals(bodyType, that.bodyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengerCapacity, bodyType);
    }

    protected String getBaseString() {
        return "Марка: " + getBrand() + ", Модель: " + getModel() + ", Цена: " + String.format("%.2f", getPrice()) + " USD" + ", Скорость: " + getMaxSpeed() + " км/ч" + ", Расход: " + String.format("%.1f", getFuelConsumption()) + " " + Car.FUEL_UNIT + ", Места: " + passengerCapacity + ", Кузов: " + bodyType;
    }
}
