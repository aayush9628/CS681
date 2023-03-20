package edu.umb.cs681.hw3;

import java.util.ArrayList;
import java.util.List;

public class Observable<WkSummary> {
    private List<Observer<WkSummary>> observers = new ArrayList<>();
    public void addObserver(Observer<WkSummary> o){
        this.observers.add(o);
    }
    public void removeObserver(Observer<WkSummary> o){
        this.observers.remove(o);
    }
    public int countObservers(){
        return (int) this.observers.stream().count();
    }
    public List<Observer<WkSummary>> getObservers(){
        return this.observers;
    }
    public void clearObservers(){
        this.observers = new ArrayList<>();
    }
    public void notifyObservers(WkSummary event){
        observers.forEach((observer) -> observer.update(this, event));
    }

    public static void main(String[] args) {

    }
}
