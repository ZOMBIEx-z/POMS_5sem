// java
package org.lw;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaxiFleet {
    private Map<String, Car> cars; // Map of id -> Car

    public TaxiFleet() {
        this.cars = new HashMap<>();
    }

    public void addCar(Car car) {
        this.cars.put(car.getId(), car);
    }

    public double calculateFleetCost() {
        // method reference used
        return cars.values().stream().mapToDouble(Car::getPrice).sum();
    }

    public List<Car> sortByFuelConsumption() {
        // stream + method reference
        return cars.values().stream()
                .sorted(Comparator.comparingDouble(Car::getFuelConsumption))
                .collect(Collectors.toList());
    }

    /**
     * Generic sorting by key. Supported keys (case-insensitive):
     * "price", "fuel", "speed", "brand"
     *
     * Uses a map of comparators (method references / lambdas) for concise handling.
     */
    public List<Car> sortByKey(String key) {
        Map<String, Comparator<Car>> comparators = Map.of(
                "price", Comparator.comparingDouble(Car::getPrice),
                "fuel", Comparator.comparingDouble(Car::getFuelConsumption),
                "fuelconsumption", Comparator.comparingDouble(Car::getFuelConsumption),
                "speed", Comparator.comparingInt(Car::getMaxSpeed),
                "maxspeed", Comparator.comparingInt(Car::getMaxSpeed),
                "brand", Comparator.comparing(Car::getBrand, Comparator.nullsFirst(String::compareTo))
        );

        Comparator<Car> comp = comparators.get(key.toLowerCase());
        if (comp == null) {
            throw new IllegalArgumentException("Unsupported sort key: " + key);
        }

        return cars.values().stream()
                .sorted(comp) // comparator supplied from map (method refs / lambdas)
                .collect(Collectors.toList());
    }

    public List<Car> findCarsBySpeed(int minSpeed, int maxSpeed) {
        if (minSpeed < 0 || maxSpeed < 0 || minSpeed > maxSpeed) {
            throw new IllegalArgumentException("Ошибка: Некорректный диапазон скоростей.");
        }

        // lambda in filter
        return cars.values().stream()
                .filter(car -> car.getMaxSpeed() >= minSpeed && car.getMaxSpeed() <= maxSpeed)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        if (cars.isEmpty()) {
            return "Таксопарк пуст.";
        }

        List<Car> list = new ArrayList<>(cars.values());
        String header = "--- Список автомобилей в Таксопарке ---\n";

        // IntStream + lambda to build numbered list
        String body = IntStream.range(0, list.size())
                .mapToObj(i -> (i + 1) + ". " + list.get(i))
                .collect(Collectors.joining("\n"));

        return header + body + "\n";
    }
}