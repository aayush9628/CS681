package edu.umb.cs681.hw17;

import java.util.concurrent.locks.ReentrantLock;

public class PowerCalculator {
    private static ReentrantLock lockA = new ReentrantLock();
    private static ReentrantLock lockB = new ReentrantLock();

    private int a;
    private int b;
    public PowerCalculator(int a, int b){
        this.a = a;
        this.b = b;
    }

    public double aRaiseToB(){
        try {
            lockA.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Value of a: " + this.a);
            System.out.println("Need the value of b.");
            lockB.lock();
            double ans = Math.pow(this.a, this.b);
            return ans;
        } finally {
            lockB.unlock();
            lockA.unlock();
        }
    }
    public double bRaiseToA(){
        try {
            lockB.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Value of b: " + this.b);
            System.out.println("Need the value of b.");
            lockA.lock();
            double ans = Math.pow(this.b, this.a);
            return ans;
        } finally {
            lockA.unlock();
            lockB.unlock();
        }
    }

    public static void main(String[] args) {
//        PowerCalculator powerCalculator = new PowerCalculator(3, 4);
//        Thread threadAtoB = new Thread(new ARaiseToB(powerCalculator));
//        Thread threadBtoA = new Thread(new BRaiseToA(powerCalculator));
//        threadAtoB.start();
//        threadBtoA.start();
    }
}
