package edu.umb.cs681.hw5;

public class DataProcessing {
    public static void main(String[] args) {
        DataProcessing1 dp1 = new DataProcessing1();
        DataProcessing2 dp2 = new DataProcessing2();
        DataProcessing3 dp3 = new DataProcessing3();
        DataProcessing4 dp4 = new DataProcessing4();
        Thread t1 = new Thread(dp1);
        Thread t2 = new Thread(dp2);
        Thread t3 = new Thread(dp3);
        Thread t4 = new Thread(dp4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e){}
    }
}
