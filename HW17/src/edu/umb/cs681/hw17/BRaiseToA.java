package edu.umb.cs681.hw17;

public class BRaiseToA implements Runnable{
    private PowerCalculator powerCalculator;
    public BRaiseToA(PowerCalculator powerCalculator){
        this.powerCalculator = powerCalculator;
    }

    @Override
    public void run() {
        System.out.println("B raise to A: " + this.powerCalculator.bRaiseToA());
    }

    public static void main(String[] args) {

    }
}
