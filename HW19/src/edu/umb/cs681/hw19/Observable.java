package edu.umb.cs681.hw19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Observable<StockEvent> {
    private ConcurrentLinkedQueue<Observer<StockEvent>> observers = new ConcurrentLinkedQueue<>();

    public void addObserver(Observer<StockEvent> o){
        this.observers.add(o);
    }
    public void removeObserver(Observer<StockEvent> o){
        this.observers.remove(o);
    }
    public int countObservers(){
        return (int) this.observers.stream().count();
    }
    public ConcurrentLinkedQueue<Observer<StockEvent>> getObservers(){
        return this.observers;
    }
    public void clearObservers(){
        this.observers = new ConcurrentLinkedQueue<>();
    }
    public void notifyObservers(StockEvent event){
        // open call for update is required
        ConcurrentLinkedQueue<Observer<StockEvent>> localCopyOfObservers;
        localCopyOfObservers = new ConcurrentLinkedQueue<>(observers);
        localCopyOfObservers.forEach((observer) -> observer.update(this, event));
    }

    public static void main(String[] args) {

    }
}
