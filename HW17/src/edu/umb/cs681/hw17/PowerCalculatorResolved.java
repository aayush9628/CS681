package edu.umb.cs681.hw17;

import java.util.concurrent.locks.ReentrantLock;

public class PowerCalculatorResolved {
    private static ReentrantLock lockA = new ReentrantLock();
    private static ReentrantLock lockB = new ReentrantLock();

    private int a;
    private int b;
    public PowerCalculatorResolved(int a, int b){
        this.a = a;
        this.b = b;
    }

    public double aRaiseToB(){
        if(this.a > this.b){
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
        } else {
            try {
                lockB.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Value of a: " + this.a);
                System.out.println("Need the value of b.");
                lockA.lock();
                double ans = Math.pow(this.a, this.b);
                return ans;
            } finally {
                lockA.unlock();
                lockB.unlock();
            }
        }

    }
    public double bRaiseToA(){
        if(this.a > this.b){
            try {
                lockA.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Value of b: " + this.b);
                System.out.println("Need the value of a.");
                lockB.lock();
                double ans = Math.pow(this.b, this.a);
                return ans;
            } finally {
                lockB.unlock();
                lockA.unlock();
            }
        } else {
            try {
                lockB.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Value of b: " + this.b);
                System.out.println("Need the value of a.");
                lockA.lock();
                double ans = Math.pow(this.b, this.a);
                return ans;
            } finally {
                lockA.unlock();
                lockB.unlock();
            }
        }
    }

    public static void main(String[] args) {
        PowerCalculatorResolved powerCalculatorResolved = new PowerCalculatorResolved(3, 4);
        Thread threadAtoB = new Thread(new ARaiseToBResolved(powerCalculatorResolved));
        Thread threadBtoA = new Thread(new BRaiseToAResolved(powerCalculatorResolved));
        threadAtoB.start();
        threadBtoA.start();
    }
}
