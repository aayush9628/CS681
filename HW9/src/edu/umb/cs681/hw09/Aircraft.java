package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class Aircraft implements Runnable{
    private Position position;
    private ReentrantLock lock = new ReentrantLock();

    public Aircraft(Position pos){
        lock.lock();
        this.position = pos;
        lock.unlock();
    }
    public void setPosition(double newLat, double newLon, double newAlt){
        lock.lock();
        this.position = this.position.change(newLat, newLon, newAlt);
        lock.unlock();
    }
    public Position getPosition(){
        lock.lock();
        try {
            return this.position;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("Old Position of the Aircraft:");
        System.out.println(this.getPosition().latitude()+" "+this.getPosition().longitude()+" "+this.getPosition().altitude());
        this.setPosition(this.getPosition().latitude() + 2.0, this.getPosition().longitude() + 2.0, this.getPosition().altitude() + 1000.0);
        System.out.println("New Position of the Aircraft");
        System.out.println(this.getPosition().latitude()+" "+this.getPosition().longitude()+" "+this.getPosition().altitude());
    }

    public static void main(String[] args) {
        Position pos = new Position(23.4, 79, 3400);
        Aircraft aircraft = new Aircraft(pos);
        Thread t1 = new Thread(aircraft);
        Position pos1 = new Position(22.5, 79.09, 3790);
        Aircraft aircraft1 = new Aircraft(pos1);
        Thread t2 = new Thread(aircraft1);
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
