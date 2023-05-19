package edu.umb.cs681.hw16;


public class Parking{
    public static void main(String[] args) {
        int instances = 10;
        Vehicle[] vehicles = new Vehicle[instances];
        Thread[] threads = new Thread[instances];
        for(int i = 0; i < vehicles.length; i++){
            vehicles[i] = new Vehicle(0, "Vehicle:" + (i+1));
            threads[i] = new Thread(vehicles[i]);
        }
        System.out.println("************ With Race Condition ************");
        for(int i = 0; i < instances; i++){
            threads[i].start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
        try{
            for(int i = 0; i < instances; i++){
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
