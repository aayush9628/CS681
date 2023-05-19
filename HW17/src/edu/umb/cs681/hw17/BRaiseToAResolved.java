package edu.umb.cs681.hw17;

public class BRaiseToAResolved implements Runnable{
    private PowerCalculatorResolved powerCalculatorResolved;
    public BRaiseToAResolved(PowerCalculatorResolved powerCalculatorResolved){
        this.powerCalculatorResolved = powerCalculatorResolved;
    }
    @Override
    public void run() {
        System.out.println("B raise to A: " + this.powerCalculatorResolved.bRaiseToA());
    }

    public static void main(String[] args) {

    }
}
