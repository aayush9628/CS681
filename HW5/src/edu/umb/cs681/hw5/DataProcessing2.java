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
            System.out.println("As there are no such entries with the condition of crime rate less than 10% and PT ratio less than 10% in the dataset, the high, the low and the average for all is undefined");
        }
        housesWithLowCrimeRateLowPtratio = csv.stream()
                .filter(lst -> {
                    if(lst.get(0) < 0.1){
                        return true;
                    } else {
                        return false;
                    }
                }).collect(Collectors.toList());

        double averagePrice = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(13)).collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double highPrice = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(13)).max(Comparator.comparing(val -> val.doubleValue())).get();
        double lowPrice = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(13)).min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("Average house price for houses with low crime rate: " + averagePrice);
        System.out.println("High house price for houses with low crime rate: " + highPrice);
        System.out.println("Low house price for houses with low crime rate: " + lowPrice);
        double averageNox = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(4)).collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double highNox = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(4)).max(Comparator.comparing(val -> val.doubleValue())).get();
        double lowNox = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(4)).min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("Average NOX for houses with low crime rate: " + averageNox);
        System.out.println("High NOX for houses with low crime rate: " + highNox);
        System.out.println("Low NOX for houses with low crime rate: " + lowNox);
        double averageRM = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(5)).collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double highRM = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(5)).max(Comparator.comparing(val -> val.doubleValue())).get();
        double lowRM = housesWithLowCrimeRateLowPtratio.stream().map(lst -> lst.get(5)).min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("Average rooms for houses with low crime rate: " + averageRM);
        System.out.println("High rooms for houses with low crime rate: " + highRM);
        System.out.println("Low rooms for houses with low crime rate: " + lowRM);
    }

    public static void main(String[] args) {
        
    }
}
