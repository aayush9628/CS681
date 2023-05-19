package edu.umb.cs681.hw15;

public class DataHandler implements Runnable{
    private static StockQuoteObservable stockQuoteObservable = new StockQuoteObservable();
    private volatile boolean done = false;
    public void setDone(){
        this.done = true;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        int i = 1;
        while (true) {
            if(done){
                System.out.println("Stopping the execution of the changeQuote function....");
                break;
            }
            String t = "TIKR" + i;
            stockQuoteObservable.changeQuote(t, Double.valueOf(i));
            i++;
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        stockQuoteObservable.addObserver(new LineChartObserver());
        stockQuoteObservable.addObserver(new TableObserver());
        stockQuoteObservable.addObserver(new ThreeDObserver());

        int instances = 11;
        DataHandler dataHandlers[] = new DataHandler[instances];
        Thread threads[] = new Thread[instances];
        for(int i = 0; i < instances; i++){
            dataHandlers[i] = new DataHandler();
            threads[i] = new Thread(dataHandlers[i]);
        }
        for(int i = 0; i < instances; i++){
            threads[i].start();
        }

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }

        for(int i = 0; i < instances; i++){
            dataHandlers[i].setDone();
        }
        for(int i = 0; i < instances; i++){
            threads[i].interrupt();
        }

        try{
            for(int i = 0; i < instances; i++){
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
