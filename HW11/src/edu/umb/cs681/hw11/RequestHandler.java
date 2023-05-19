package edu.umb.cs681.hw11;

import java.nio.file.Path;

public class RequestHandler implements Runnable{
    private static AccessCounter accessCounter = new AccessCounter();

    private volatile boolean done = false;

    public void setDone() {
        this.done = true;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        while(true){
            if(done){
                System.out.println("Stopped...");
                break;
            }
            Path of = Path.of("a.html");
            accessCounter.increment(of);
            System.out.println("Count of the file: " + accessCounter.getCount(of));
            try {
                Thread.sleep(1000); // runnable thread will sleep for 1 sec
            }catch(InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }
    }

    public static void main(String[] args) {
        RequestHandler requestHandler[] = new RequestHandler[11];
        Thread threads[] = new Thread[11];
        for(int i = 0; i < requestHandler.length; i++){
            requestHandler[i] = new RequestHandler();
            threads[i] = new Thread(requestHandler[i], "Thread " + (i+1));
        }
        for(int i = 0; i < threads.length; i++){
            threads[i].start();
        }
        try {
            Thread.sleep(3000); // main thread sleeps for 3 secs
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < requestHandler.length; i++){
            requestHandler[i].setDone();
        }
        for(int i = 0; i < threads.length; i++){
            threads[i].interrupt();
        }
        try {
            for(int i = 0; i < threads.length; i++){
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
