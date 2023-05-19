package edu.umb.cs681.hw16;

import java.util.concurrent.locks.ReentrantLock;

public class VehicleWithLock implements Runnable{
    private int spotNumber;
    private String nameOfVehicle;
    private static ReentrantLock lock = new ReentrantLock();

    public VehicleWithLock(int spotNumber, String nameOfVehicle) {
        this.spotNumber = spotNumber;
        this.nameOfVehicle = nameOfVehicle;
    }
    private static int spot = 1;
    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName());
        assignParkingSpot(this);
    }
    public void assignParkingSpot(VehicleWithLock vehicle){
        lock.lock();
        vehicle.setSpotNumber(spot);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
        spot++;
        lock.unlock();
        System.out.println(vehicle.getnameOfVehicle() + " assigned the parking spot -> " + vehicle.getSpotNumber());
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getnameOfVehicle() {
        return nameOfVehicle;
    }

    public void setnameOfVehicle(String nameOfVehicle) {
        this.nameOfVehicle = nameOfVehicle;
    }

    public static void main(String[] args) {

    }
}
