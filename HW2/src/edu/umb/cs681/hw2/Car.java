package edu.umb.cs681.hw2;

import java.util.ArrayList;

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

        double average = cars.stream().map(Car::getPrice)
                .reduce(new CarPriceResultHolder(), (result, price) -> {
                    double val = (result.getAverage() * result.getNumCarsExamined() + price);
                    result.setNumCarsExamined(result.getNumCarsExamined() + 1);
                    result.setAverage(val / result.getNumCarsExamined());
                    return result;
                },(finalResult, intermediateResult) -> finalResult).getAverage();
        System.out.println(average);
    }
}

class CarPriceResultHolder{
    private int numCarsExamined;
    private double average;
    public CarPriceResultHolder(){
        this.numCarsExamined = 0;
        this.average = 0;
    }

    public int getNumCarsExamined() {
        return numCarsExamined;
    }

    public void setNumCarsExamined(int numCarsExamined) {
        this.numCarsExamined = numCarsExamined;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
