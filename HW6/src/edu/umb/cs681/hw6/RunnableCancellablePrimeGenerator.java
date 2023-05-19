package edu.umb.cs681.hw6;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator{
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone() {
        lock.lock();
        try{
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimes(){
        for (long n = from; n <= to; n++) {
            lock.lock();
            try{
                if(done){
                    System.out.println("Stopped...");
                    this.primes.clear();
                    break;
                }
                if( isPrime(n) ){ this.primes.add(n); }
            }finally {
                lock.unlock();
            }
        }
    }

    public void run(){
        this.generatePrimes();
    }
}
