package edu.umb.cs681.hw15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Observable<StockEvent> {
    private List<Observer<StockEvent>> observers = new ArrayList<>();
    private ReentrantLock lockObs = new ReentrantLock();

    public void addObserver(Observer<StockEvent> o){
        this.observers.add(o);
    }
    public void removeObserver(Observer<StockEvent> o){
        this.observers.remove(o);
    }
    public int countObservers(){
        return (int) this.observers.stream().count();
    }
    public List<Observer<StockEvent>> getObservers(){
        return this.observers;
    }
    public void clearObservers(){
        this.observers = new ArrayList<>();
    }
    public void notifyObservers(StockEvent event){
        // open call for update is required
        List<Observer<StockEvent>> localCopyOfObservers;
        lockObs.lock();
        localCopyOfObservers = new ArrayList<>(observers);
        lockObs.unlock();
        localCopyOfObservers.forEach((observer) -> observer.update(this, event));
    }

    public static void main(String[] args) {

    }
}
