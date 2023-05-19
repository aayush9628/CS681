package edu.umb.cs681.hw19;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {
    private ConcurrentHashMap<String, Double> stockWithPrice = new ConcurrentHashMap<>();

    public void changeQuote(String t, double q){
        this.stockWithPrice.put(t, q);
        this.notifyObservers(new StockEvent(t, q));
    }

    public static void main(String[] args) {

    }
}
