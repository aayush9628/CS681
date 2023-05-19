package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AdmissionMonitor {
    private static int currentVisitor = 0;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantLock instanceLock = new ReentrantLock();
    Condition reduceVisitors = lock.writeLock().newCondition();
    Condition sufficientVisitors = lock.writeLock().newCondition();

    private static AdmissionMonitor singleton = null;

    public AdmissionMonitor getInstance(){
        instanceLock.lock();
        try {
            if (singleton == null) {
                singleton = new AdmissionMonitor();
            }
            return singleton;
        } finally {
            instanceLock.unlock();
        }
    }

    public void enter(){
        lock.writeLock().lock();
        try {
            while (currentVisitor > 10) {
                System.out.println("Too many visitors inside the building, Please wait.");
                try {
                    reduceVisitors.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentVisitor++;
            sufficientVisitors.signalAll();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void exit(){
        lock.writeLock().lock();
        try {
            while (currentVisitor <= 0) {
                System.out.println("Letting in the visitors, kindly keep coming.");
                try {
                    sufficientVisitors.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentVisitor--;
            reduceVisitors.signalAll();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int countCurrentVisitors(){
        try{
            lock.readLock().lock();
            return currentVisitor;
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {

    }
}
