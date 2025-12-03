// java
package org.lw;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final TaxiFleet fleet = new TaxiFleet();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeFleet();
        displayMenu();
    }

    private static void initializeFleet() {
        try {
            fleet.addCar(new Sedan("Toyota", "Camry", 25000.00, 210, 8.5, 4, false));
            fleet.addCar(new Sedan("BMW", "5 Series", 55000.00, 250, 9.8, 4, true));
            fleet.addCar(new Minivan("Kia", "Carnival", 38000.00, 190, 10.2, 7, true));
            fleet.addCar(new Minivan("Honda", "Odyssey", 35000.00, 180, 11.5, 7, false));
            fleet.addCar(new Sedan("Hyundai", "Solaris", 18000.00, 195, 6.5, 4, false));
            fleet.addCar(new Sedan("Mercedes-Benz", "E-Class", 60000.00, 240, 9.0, 4, true));
        } catch (InvalidCarParameterException e) {
            System.out.println("Error initializing fleet: " + e.getMessage());
        }

        try {
            Car car = new Sedan("Test", "Invalid", 10000, 200, 7.5, 4, false);
            car.setPrice(-100); // Example usage
            fleet.addCar(car);
        } catch (InvalidCarParameterException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            fleet.addCar(new Minivan("Test", "Invalid", 15000, -55, 8.0, 7, true));
        } catch (InvalidCarParameterException e) {
            System.out.println("Error: " + e.getMessage());
        }


        System.out.println("Таксопарк инициализирован. Добавлено " + fleet.calculateFleetCost() + " USD.");
    }

    private static void displayMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== МЕНЮ ТАКСОПАРКА =====");
            System.out.println("1. Показать список автомобилей");
            System.out.println("2. Подсчитать общую стоимость автопарка");
            System.out.println("3. Провести сортировку по расходу топлива");
            System.out.println("4. Найти автомобиль по диапазону скорости");
            System.out.println("5. Сортировка по ключевому параметру (price, fuel, speed, brand)");
            System.out.println("0. Выход");
            System.out.print("Введите номер пункта: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера после nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: Пожалуйста, введите число.");
                scanner.nextLine(); // Очистка некорректного ввода
                continue;
            }

            System.out.println();

            switch (choice) {
                case 1:
                    showAllCars();
                    break;
                case 2:
                    calculateCost();
                    break;
                case 3:
                    sortCars();
                    break;
                case 4:
                    findCarBySpeedRange();
                    break;
                case 5:
                    sortCarsByKey();
                    break;
                case 0:
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    private static void showAllCars() {
        System.out.println(fleet);
    }

    private static void calculateCost() {
        double totalCost = fleet.calculateFleetCost();
        System.out.printf("Общая стоимость автопарка: %.2f USD\n", totalCost);
    }

    private static void sortCars() {
        List<Car> sortedCars = fleet.sortByFuelConsumption();

        System.out.println("\n--- Автомобили, отсортированные по расходу топлива (возрастание) ---");
        sortedCars.forEach(car -> System.out.printf("%s, Расход: %.1f %s\n", car.getBrand() + " " + car.getModel(), car.getFuelConsumption(), Car.FUEL_UNIT));
        System.out.println("Сортировка завершена.");
    }

    private static void sortCarsByKey() {
        System.out.print("Введите ключ сортировки (price, fuel, speed, brand): ");
        String key = scanner.nextLine().trim();

        try {
            List<Car> sorted = fleet.sortByKey(key);
            System.out.println("\n--- Результаты сортировки по ключу: " + key + " ---");
            sorted.forEach(car -> System.out.println(car));
            System.out.println("Сортировка завершена.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void findCarBySpeedRange() {
        int minSpeed = -1;
        int maxSpeed = -1;

        try {
            System.out.print("Введите минимальную скорость (км/ч): ");
            minSpeed = scanner.nextInt();
            System.out.print("Введите максимальную скорость (км/ч): ");
            maxSpeed = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера
        } catch (InputMismatchException e) {
            System.err.println("Ошибка ввода: Скорость должна быть целым числом.");
            scanner.nextLine(); // Очистка некорректного ввода
            return;
        }

        try {
            List<Car> foundCars = fleet.findCarsBySpeed(minSpeed, maxSpeed);

            System.out.println("\n--- Результаты поиска (Скорость от " + minSpeed + " до " + maxSpeed + " км/ч) ---");
            if (foundCars.isEmpty()) {
                System.out.println("Автомобили в заданном диапазоне не найдены.");
            } else {
                foundCars.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}