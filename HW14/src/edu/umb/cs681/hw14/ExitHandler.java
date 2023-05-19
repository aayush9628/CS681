package edu.umb.cs681.hw14;

public class ExitHandler extends AdmissionMonitor implements Runnable{
    private AdmissionMonitor monitor = getInstance();
    private volatile boolean done = false;
    public void setDone() {
        this.done = true;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        while(true) {
            if(done){
                System.out.println("Stopping the exit...");
                break;
            }
            monitor.exit();
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                System.out.println("Interrupted...");
            }
        }
    }

    public static void main(String[] args) {

    }
}
