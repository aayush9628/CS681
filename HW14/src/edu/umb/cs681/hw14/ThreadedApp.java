package edu.umb.cs681.hw14;

public class ThreadedApp {
    public static void main(String[] args) {
        int instances = 10;
        EntranceHandler[] entranceHandler = new EntranceHandler[instances];
        ExitHandler[] exitHandler = new ExitHandler[instances];
        StatsHandler[] statsHandler = new StatsHandler[instances];

        for(int i = 0; i < instances; i++){
            entranceHandler[i] = new EntranceHandler();
            exitHandler[i] = new ExitHandler();
            statsHandler[i] = new StatsHandler();
        }

        Thread[] threadEntrance = new Thread[instances];
        Thread[] threadExit = new Thread[instances];
        Thread[] threadStats = new Thread[instances];
        for(int i = 0; i < instances; i++){
            threadEntrance[i] = new Thread(entranceHandler[i]);
            threadExit[i] = new Thread(exitHandler[i]);
            threadStats[i] = new Thread(statsHandler[i]);
        }

        for(int i = 0; i < instances; i++){
            threadEntrance[i].start();
            threadExit[i].start();
            threadStats[i].start();
        }

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }

        for(int i = 0; i < instances; i++) {
            entranceHandler[i].setDone();
            exitHandler[i].setDone();
            statsHandler[i].setDone();
        }

        for(int i = 0; i < instances; i++) {
            threadEntrance[i].interrupt();
            threadExit[i].interrupt();
            threadStats[i].interrupt();
        }

        try{
            for(int i = 0; i < instances; i++) {
                threadEntrance[i].join();
                threadExit[i].join();
                threadStats[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
