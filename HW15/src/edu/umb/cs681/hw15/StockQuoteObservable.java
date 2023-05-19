package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {
    private HashMap<String, Double> stockWithPrice = new HashMap<>();
    private ReentrantLock lockTQ = new ReentrantLock();

    public void changeQuote(String t, double q){
        lockTQ.lock();
        this.stockWithPrice.put(t, q);
        lockTQ.unlock();
        this.notifyObservers(new StockEvent(t, q));
    }

    public static void main(String[] args) {

    }
}
