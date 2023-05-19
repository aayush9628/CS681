package edu.umb.cs681.hw19;

public class LineChartObserver implements Observer<StockEvent>{

    @Override
    public void update(Observable<StockEvent> sender, StockEvent stockEvent) {
        System.out.println("Line Chart Observer: " + stockEvent.ticker() + " " + stockEvent.quote());
    }

    public static void main(String[] args) {

    }
}
