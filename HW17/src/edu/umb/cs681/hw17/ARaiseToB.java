package edu.umb.cs681.hw17;

public class ARaiseToB implements Runnable{
    private PowerCalculator powerCalculator;
    public ARaiseToB(PowerCalculator powerCalculator){
        this.powerCalculator = powerCalculator;
    }
    @Override
    public void run() {
        System.out.println("A Raise to B: " + this.powerCalculator.aRaiseToB());
    }

    public static void main(String[] args) {

    }
}
