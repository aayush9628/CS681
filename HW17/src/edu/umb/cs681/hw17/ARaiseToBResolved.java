package edu.umb.cs681.hw17;

public class ARaiseToBResolved implements Runnable{
    private PowerCalculatorResolved powerCalculatorResolved;
    public ARaiseToBResolved(PowerCalculatorResolved powerCalculatorResolved){
        this.powerCalculatorResolved = powerCalculatorResolved;
    }
    @Override
    public void run() {
        System.out.println("A raise to B: " + this.powerCalculatorResolved.aRaiseToB());
    }

    public static void main(String[] args) {

    }
}
