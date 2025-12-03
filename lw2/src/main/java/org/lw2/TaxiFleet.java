package org.lw2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class TaxiFleet {
    private List<Car> cars; // Список автомобилей в парке


    public TaxiFleet() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public double calculateFleetCost() {
        return cars.stream().mapToDouble(Car::getPrice).sum();
    }

    public List<Car> sortByFuelConsumption() {
        List<Car> sortedCars = new ArrayList<>(cars);

        sortedCars.sort(Comparator.comparingDouble(Car::getFuelConsumption));

        return sortedCars;
    }

    public List<Car> findCarsBySpeed(int minSpeed, int maxSpeed) {
        if (minSpeed < 0 || maxSpeed < 0 || minSpeed > maxSpeed) {
            throw new IllegalArgumentException("Ошибка: Некорректный диапазон скоростей.");
        }

        return cars.stream().filter(car -> car.getMaxSpeed() >= minSpeed && car.getMaxSpeed() <= maxSpeed).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        if (cars.isEmpty()) {
            return "Таксопарк пуст.";
        }

        StringBuilder sb = new StringBuilder("--- Список автомобилей в Таксопарке ---\n");
        for (int i = 0; i < cars.size(); i++) {
            sb.append(i + 1).append(". ").append(cars.get(i)).append("\n");
        }
        return sb.toString();
    }
}
