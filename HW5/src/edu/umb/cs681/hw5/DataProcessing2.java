package edu.umb.cs681.hw5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessing2 implements Runnable{
    private List<List<Double>> csv;

    public DataProcessing2(){
        this.csv = new ArrayList<>();
        Path path = Paths.get("/Users/aayushshrivastava/Downloads/bos-housing.csv");

        try(Stream<String> lines = Files.lines(path)){
            csv = lines.map(line -> {
                return Stream.of(line.split(","))
                        .map(value -> {
                            String str = value.trim().replaceAll("^\"|\"$", "");
                            if(str.charAt(0) >= 48 && str.charAt(0) <= 57){
                                return Double.valueOf(str);
                            } else {
                                return null;
                            }
                        }).collect(Collectors.toList());
            }).collect(Collectors.toList());
        } catch(IOException ex){}

        this.csv.remove(this.csv.get(0));
    }
    @Override
    public void run() {
        List<List<Double>> housesWithLowCrimeRateLowPtratio = csv.stream()
                .filter(lst -> {
                    if(lst.get(0) < 0.1 && lst.get(10) < 10){
                        return true;
                    } else {
                        return false;
                    }
                }).collect(Collectors.toList());

        if(housesWithLowCrimeRateLowPtratio.size() == 0){
            System.out.println("2. As there are no such entries in the dataset, the high, the low and the average for all is undefined");
            return;
        }

        double averagePrice = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(13)).collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double highPrice = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(13)).max(Comparator.comparing(val -> val.doubleValue())).get();
        double lowPrice = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(13)).min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("2. Average house price for houses with low crime rate and low PT ratio: " + averagePrice);
        System.out.println("2. High house price for houses with low crime rate and low PT ratio: " + highPrice);
        System.out.println("2. Low house price for houses with low crime rate and low PT ratio: " + lowPrice);
        double averageNox = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(4)).collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double highNox = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(4)).max(Comparator.comparing(val -> val.doubleValue())).get();
        double lowNox = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(4)).min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("2. Average NOX for houses with low crime rate and low PT ratio: " + averageNox);
        System.out.println("2. High NOX for houses with low crime rate and low PT ratio: " + highNox);
        System.out.println("2. Low NOX for houses with low crime rate and low PT ratio: " + lowNox);
        double averageRM = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(5)).collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double highRM = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(5)).max(Comparator.comparing(val -> val.doubleValue())).get();
        double lowRM = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(5)).min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("2. Average rooms for houses with low crime rate and low PT ratio: " + averageRM);
        System.out.println("2. High rooms for houses with low crime rate and low PT ratio: " + highRM);
        System.out.println("2. Low rooms for houses with low crime rate and low PT ratio: " + lowRM);
    }
}
