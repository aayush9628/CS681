package edu.umb.cs681.hw1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Car {
    private String model, make;
    private int mileage, year;
    private float price;
    private int dominationCount;

    public Car(String model, String make, int mileage, int year, float price){
        this.model = model;
        this.make = make;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDominationCount(int dominationCount){
        this.dominationCount = dominationCount;
    }

    public int getDominationCount(){
        return this.dominationCount;
    }

    public static void mileageSort(ArrayList<Car> cars, int threshold){
        ArrayList<Car> lowSortedCars = (ArrayList<Car>) cars.stream()
                .filter((Car car) -> car.getMileage() < threshold)
                .collect(Collectors.toList());
//        lowSortedCars.add(new Car("Threshold", "Threshold", -1, -1, -1.0F));
        ArrayList<Car> highSortedCars = (ArrayList<Car>) cars.stream()
                .filter((Car car) -> car.getMileage() >= threshold)
                .collect(Collectors.toList());

        ArrayList<Car> finalSortedCars = (ArrayList<Car>) Stream.of(lowSortedCars, highSortedCars)
                .flatMap((ArrayList<Car> list) -> list.stream())
                .collect(Collectors.toList());
        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        System.out.println("Mileage Sorting");
        for(Car car: finalSortedCars) {
            System.out.println(car.getModel() + " " + car.getMake() + " " + car.getMileage() + " " + car.getYear() + " " + car.getPrice());
        }

        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        int minMileage = cars.stream().min(Comparator.comparing((Car c) -> c.getMileage())).get().getMileage();
        System.out.println("Minimum Mileage: " + minMileage);
        int maxMileage = cars.stream().max(Comparator.comparing((Car c) -> c.getMileage())).get().getMileage();
        System.out.println("Maximum Mileage: " + maxMileage);
        Double avgOfMileage = cars.stream().collect(Collectors.averagingInt((Car car) -> car.getMileage()));
        System.out.println("Average of Mileage: " + avgOfMileage);
        int lowCount = (int) lowSortedCars.stream().count();
        System.out.println("Low Cars: " + lowCount);
        int highCount = (int) highSortedCars.stream().count();
        System.out.println("High Cars: " + highCount);
        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
    }
    public static void priceSort(ArrayList<Car> cars, double threshold){
        ArrayList<Car> lowSortedCars = (ArrayList<Car>) cars.stream()
                .filter((Car car) -> car.getPrice() < threshold)
                .collect(Collectors.toList());

//        lowSortedCars.add(new Car("Threshold", "Threshold", -1, -1, -1.0F));

        ArrayList<Car> highSortedCars = (ArrayList<Car>) cars.stream()
                .filter((Car car) -> car.getPrice() >= threshold)
                .collect(Collectors.toList());

        ArrayList<Car> finalSortedCars = (ArrayList<Car>) Stream.of(lowSortedCars, highSortedCars)
                .flatMap((ArrayList<Car> list) -> list.stream())
                .collect(Collectors.toList());

        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        System.out.println("Price Sorting");
        for(Car car: finalSortedCars) {
            System.out.println(car.getModel() + " " + car.getMake() + " " + car.getMileage() + " " + car.getYear() + " " + car.getPrice());
        }

        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        double minPrice = cars.stream().min(Comparator.comparing((Car c) -> c.getPrice())).get().getPrice();
        System.out.println("Minimum Price: " + minPrice);
        double maxPrice = cars.stream().max(Comparator.comparing((Car c) -> c.getPrice())).get().getPrice();
        System.out.println("Maximum Price: " + maxPrice);
        double avgOfPrices = cars.stream().collect(Collectors.averagingDouble((Car car) -> car.getPrice()));
        System.out.println("Average of prices: " + avgOfPrices);
        int lowCount = (int) lowSortedCars.stream().count();
        System.out.println("Low Cars: " + lowCount);
        int highCount = (int) highSortedCars.stream().count();
        System.out.println("High Cars: " + highCount);
        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
    }
    public static void yearSort(ArrayList<Car> cars, int threshold){
        ArrayList<Car> lowSortedCars = (ArrayList<Car>) cars.stream()
                .filter((Car car) -> car.getYear() < threshold)
                .collect(Collectors.toList());

//        lowSortedCars.add(new Car("Threshold", "Threshold", -1, -1, -1.0F));
        ArrayList<Car> highSortedCars = (ArrayList<Car>) cars.stream()
                .filter((Car car) -> car.getYear() >= threshold)
                .collect(Collectors.toList());

        ArrayList<Car> finalSortedCars = (ArrayList<Car>) Stream.of(lowSortedCars, highSortedCars)
                .flatMap((ArrayList<Car> list) -> list.stream())
                .collect(Collectors.toList());
        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        System.out.println("Year Sorting");
        for(Car car: finalSortedCars) {
            System.out.println(car.getModel() + " " + car.getMake() + " " + car.getMileage() + " " + car.getYear() + " " + car.getPrice());
        }

        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        int minYear = cars.stream().min(Comparator.comparing((Car c) -> c.getYear())).get().getYear();
        System.out.println("Minimum Year: " + minYear);
        int maxYear = cars.stream().max(Comparator.comparing((Car c) -> c.getYear())).get().getYear();
        System.out.println("Maximum Year: " + maxYear);
        Double avgOfYear = cars.stream().collect(Collectors.averagingInt((Car car) -> car.getYear()));
        System.out.println("Average of Year: " + avgOfYear);
        int lowCount = (int) lowSortedCars.stream().count();
        System.out.println("Low Cars: " + lowCount);
        int highCount = (int) highSortedCars.stream().count();
        System.out.println("High Cars: " + highCount);
        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
    }
    public static void dominationSort(ArrayList<Car> cars, int threshold){
        ArrayList<Car> lowSortedCars = (ArrayList<Car>) cars.stream()
                .filter((Car car) -> car.getDominationCount() < threshold)
                .collect(Collectors.toList());
//        lowSortedCars.add(new Car("Threshold", "Threshold", -1, -1, -1.0F));
        ArrayList<Car> highSortedCars = (ArrayList<Car>) cars.stream()
                .filter((Car car) -> car.getDominationCount() >= threshold)
                .collect(Collectors.toList());

        ArrayList<Car> finalSortedCars = (ArrayList<Car>) Stream.of(lowSortedCars, highSortedCars)
                .flatMap((ArrayList<Car> list) -> list.stream())
                .collect(Collectors.toList());

        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        System.out.println("Domination Sorting");
        for(Car car: finalSortedCars) {
            System.out.println(car.getModel() + " " + car.getMake() + " " + car.getMileage() + " " + car.getYear() + " " + car.getPrice());
        }

        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        int minDomination = cars.stream().min(Comparator.comparing((Car c) -> c.getDominationCount())).get().getDominationCount();
        System.out.println("Minimum Domination: " + minDomination);
        int maxDomination = cars.stream().max(Comparator.comparing((Car c) -> c.getDominationCount())).get().getDominationCount();
        System.out.println("Maximum Domination: " + maxDomination);
        Double avgOfDomination = cars.stream().collect(Collectors.averagingInt((Car car) -> car.getDominationCount()));
        System.out.println("Average of Domination: " + avgOfDomination);
        int lowCount = (int) lowSortedCars.stream().count();
        System.out.println("Low Cars: " + lowCount);
        int highCount = (int) highSortedCars.stream().count();
        System.out.println("High Cars: " + highCount);
        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Civic", "Honda", 20, 2002, 30000.0F));
        cars.add(new Car("Phantom", "Rolls Royce", 30, 2020, 300000.0F));
        cars.add(new Car("SLS AMG", "Mercedes", 40, 2012, 100000.0F));
        cars.add(new Car("Nano", "Tata", 10, 2008, 3000.0F));
        cars.add(new Car("Veneno", "Lamborghini", 100, 2010, 3000000.0F));
        int y = 9;
        for(Car car : cars){
            car.setDominationCount(y--);
        }
        mileageSort(cars, 30);
        priceSort(cars, 400000.0);
        yearSort(cars, 2007);
        dominationSort(cars, 6);
    }
}
