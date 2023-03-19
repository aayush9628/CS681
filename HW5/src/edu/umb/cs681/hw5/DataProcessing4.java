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

public class DataProcessing4 implements Runnable{
    private List<List<Double>> csv;
    public DataProcessing4(){
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
        double maxHouseAge = csv.stream().map(lst -> lst.get(6)).max(Comparator.comparing(val -> val.doubleValue())).get();
        List<List<Double>> maxHouseAgeList = csv.stream().filter(val -> {
            if(maxHouseAge == val.get(6)){
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        double taxRateForMax = maxHouseAgeList.stream().map(lst -> lst.get(9)).collect(Collectors.averagingDouble(x -> x.doubleValue()));
        System.out.println("4. Average Tax rate of the house with highest house age: " + taxRateForMax);

        double minHouseAge = csv.stream().map(lst -> lst.get(6)).min(Comparator.comparing(val -> val.doubleValue())).get();
        List<List<Double>> minHouseAgeList = csv.stream().filter(val -> {
            if(minHouseAge == val.get(6)){
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        double taxRateForMin = minHouseAgeList.stream().map(lst -> lst.get(9)).collect(Collectors.averagingDouble(x -> x.doubleValue()));
        System.out.println("4. Average Tax rate of the house with lowest house age: " + taxRateForMin);
    }
}
