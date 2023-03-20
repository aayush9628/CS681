package edu.umb.cs681.hw3;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DJIAWkSummaryObservable extends Observable<WkSummary> {
    public List<List<Double>> collectionSummary;

    public static boolean isDate(String str){
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yy");
        date.setLenient(false);
        try {
            date.parse(str.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public void addSummary(DSummary dSummary){
        List<Double> dSmry = new ArrayList<>();
        dSmry.add(null);
        dSmry.add(dSummary.getOpen());
        dSmry.add(dSummary.getHigh());
        dSmry.add(dSummary.getLow());
        dSmry.add(dSummary.getClose());
        this.collectionSummary.add(dSmry);
        WkSummary wkSummary = new WkSummary(0.0, 0.0, 0.0, 0.0);
        double open = this.collectionSummary.stream().map(lst -> lst.get(1)).collect(Collectors.averagingDouble(x -> x));
        double high = this.collectionSummary.stream().map(lst -> lst.get(2)).max(Comparator.comparing(x -> x.doubleValue())).get();
        double low = this.collectionSummary.stream().map(lst -> lst.get(3)).min(Comparator.comparing(x -> x.doubleValue())).get();
        double close = this.collectionSummary.stream().map(lst -> lst.get(4)).collect(Collectors.averagingDouble(x -> x));
        wkSummary.setOpen(open);
        wkSummary.setHigh(high);
        wkSummary.setLow(low);
        wkSummary.setClose(close);
        this.notifyObservers(wkSummary);
        this.collectionSummary = new ArrayList<>();
    }
    public static void main(String[] args) {
        DJIAWkSummaryObservable observable = new DJIAWkSummaryObservable();
        Path path = Paths.get("/Users/aayushshrivastava/Downloads/HistoricalPrices-2.csv");

        try(Stream<String> lines = Files.lines(path)){
            observable.collectionSummary = lines.map(line -> {
                return Stream.of(line.split(","))
                        .map(value -> {
                            if(value.trim().charAt(0) >= 48 && value.trim().charAt(0) <= 57 && value.trim().charAt(2) != '/'){
                                return Double.valueOf(value);
                            } else {
                                return null;
                            }
                        }).collect(Collectors.toList());
            }).collect(Collectors.toList());
        } catch(IOException ex){}

        observable.collectionSummary.remove(observable.collectionSummary.get(0));
        observable.addObserver(new CandleStickObserver());
        observable.addSummary(new DSummary(34400.0, 32004.0, 35005.0, 33333.0));
    }
}
