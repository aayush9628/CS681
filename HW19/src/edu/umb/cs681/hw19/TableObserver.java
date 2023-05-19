package edu.umb.cs681.hw19;

public class TableObserver implements Observer<StockEvent>{

    @Override
    public void update(Observable<StockEvent> sender, StockEvent stockEvent) {
        System.out.println("Table Chart Observer: " + stockEvent.ticker() + " " + stockEvent.quote());
    }

    public static void main(String[] args) {

    }
}
