package org.lw2;

import java.util.Objects;

public class Sedan extends PassengerCar {
    private boolean hasPremiumInterior; // Наличие премиум-салона

    public Sedan(String brand, String model, double price, int maxSpeed, double fuelConsumption, int passengerCapacity, boolean hasPremiumInterior) throws InvalidCarParameterException {
        super(brand, model, price, maxSpeed, fuelConsumption, passengerCapacity, "Седан");
        this.hasPremiumInterior = hasPremiumInterior;
    }

    public boolean isHasPremiumInterior() {
        return hasPremiumInterior;
    }

    public void setHasPremiumInterior(boolean hasPremiumInterior) {
        this.hasPremiumInterior = hasPremiumInterior;
    }

    @Override
    public String toString() {
        return "Седан [" + getBaseString() + ", Премиум-салон: " + (hasPremiumInterior ? "Да" : "Нет") + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sedan sedan = (Sedan) o;
        return hasPremiumInterior == sedan.hasPremiumInterior;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasPremiumInterior);
    }
}
