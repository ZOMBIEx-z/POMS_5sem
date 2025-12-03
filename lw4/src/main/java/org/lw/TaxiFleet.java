// java
package org.lw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaxiFleet implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Car> cars; // Map of id -> Car

    public TaxiFleet() {
        this.cars = new HashMap<>();
    }

    public void addCar(Car car) {
        this.cars.put(car.getId(), car);
    }

    public double calculateFleetCost() {
        return cars.values().stream().mapToDouble(Car::getPrice).sum();
    }

    public List<Car> sortByFuelConsumption() {
        List<Car> sortedCars = new ArrayList<>(cars.values());
        sortedCars.sort(Comparator.comparingDouble(Car::getFuelConsumption));
        return sortedCars;
    }

    /**
     * Generic sorting by key. Supported keys (case-insensitive):
     * "price", "fuel", "speed", "brand"
     */
    public List<Car> sortByKey(String key) {
        List<Car> list = new ArrayList<>(cars.values());
        switch (key.toLowerCase()) {
            case "price":
                list.sort(Comparator.comparingDouble(Car::getPrice));
                break;
            case "fuel":
            case "fuelconsumption":
                list.sort(Comparator.comparingDouble(Car::getFuelConsumption));
                break;
            case "speed":
            case "maxspeed":
                list.sort(Comparator.comparingInt(Car::getMaxSpeed));
                break;
            case "brand":
                list.sort(Comparator.comparing(Car::getBrand, Comparator.nullsFirst(String::compareTo)));
                break;
            default:
                throw new IllegalArgumentException("Unsupported sort key: " + key);
        }
        return list;
    }

    public List<Car> findCarsBySpeed(int minSpeed, int maxSpeed) {
        if (minSpeed < 0 || maxSpeed < 0 || minSpeed > maxSpeed) {
            throw new IllegalArgumentException("Ошибка: Некорректный диапазон скоростей.");
        }

        return cars.values().stream()
                .filter(car -> car.getMaxSpeed() >= minSpeed && car.getMaxSpeed() <= maxSpeed)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        if (cars.isEmpty()) {
            return "Таксопарк пуст.";
        }

        StringBuilder sb = new StringBuilder("--- Список автомобилей в Таксопарке ---\n");
        List<Car> list = new ArrayList<>(cars.values());
        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return sb.toString();
    }
}