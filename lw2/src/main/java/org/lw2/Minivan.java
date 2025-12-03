package org.lw2;

import java.util.Objects;


public class Minivan extends PassengerCar {
    private boolean hasLuggageRack; // Наличие багажника на крыше


    public Minivan(String brand, String model, double price, int maxSpeed, double fuelConsumption, int passengerCapacity, boolean hasLuggageRack) throws InvalidCarParameterException {
        super(brand, model, price, maxSpeed, fuelConsumption, passengerCapacity, "Минивэн");
        this.hasLuggageRack = hasLuggageRack;
    }

    public boolean isHasLuggageRack() {
        return hasLuggageRack;
    }

    public void setHasLuggageRack(boolean hasLuggageRack) {
        this.hasLuggageRack = hasLuggageRack;
    }

    @Override
    public String toString() {
        return "Минивэн [" + getBaseString() + ", Багажник на крыше: " + (hasLuggageRack ? "Да" : "Нет") + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Minivan minivan = (Minivan) o;
        return hasLuggageRack == minivan.hasLuggageRack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasLuggageRack);
    }
}
