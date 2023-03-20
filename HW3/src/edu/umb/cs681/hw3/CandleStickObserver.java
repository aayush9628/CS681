package edu.umb.cs681.hw3;

import java.util.List;

public class CandleStickObserver implements Observer<WkSummary> {
    @Override
    public void update(Observable<WkSummary> sender, WkSummary event) {
        System.out.println("Opening Price: " + event.getOpen());
        System.out.println("Closing Price: " + event.getClose());
        System.out.println("High Price: " + event.getHigh());
        System.out.println("Low Price: " + event.getLow());
        System.out.println("Sender: " + sender);
    }

    public static void main(String[] args) {

    }
}
