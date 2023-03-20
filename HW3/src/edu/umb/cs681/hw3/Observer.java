package edu.umb.cs681.hw3;

public interface Observer<WkSummary> {
    public void update(Observable<WkSummary> sender, WkSummary event);

    public static void main(String[] args) {

    }
}
