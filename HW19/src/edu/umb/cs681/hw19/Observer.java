package edu.umb.cs681.hw19;

public interface Observer<StockEvent> {
    public void update(Observable<StockEvent> sender, StockEvent event);

    public static void main(String[] args) {

    }
}
