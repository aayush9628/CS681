package edu.umb.cs681.hw15;

public class ThreeDObserver implements Observer<StockEvent>{

    @Override
    public void update(Observable<StockEvent> sender, StockEvent stockEvent) {
        System.out.println("Three dimension Observer: " + stockEvent.ticker() + " " + stockEvent.quote());
    }

    public static void main(String[] args) {

    }
}
