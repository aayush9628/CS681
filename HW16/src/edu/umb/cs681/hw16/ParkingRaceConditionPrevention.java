package edu.umb.cs681.hw16;

public class ParkingRaceConditionPrevention {
    public static void main(String[] args) {
        int instances = 10;
        VehicleWithLock[] VehicleWithLocks = new VehicleWithLock[instances];
        Thread[] threads = new Thread[instances];
        for(int i = 0; i < VehicleWithLocks.length; i++){
            VehicleWithLocks[i] = new VehicleWithLock(0, "VehicleWithLock:" + (i+1));
            threads[i] = new Thread(VehicleWithLocks[i]);
        }
        System.out.println("************ Without Race Condition ************");
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
