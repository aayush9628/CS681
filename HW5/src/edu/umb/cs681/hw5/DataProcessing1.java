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

public class DataProcessing1 implements Runnable {
    private List<List<Double>> csv;
    public DataProcessing1(){
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
        List<List<Double>> housesNearCharlesRiver = this.csv.stream().filter(lst -> {
            if(lst.get(3) == 1.0){
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());

        double average = housesNearCharlesRiver.stream().map(lst -> lst.get(13)).collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double high = housesNearCharlesRiver.stream().map(lst -> lst.get(13)).max(Comparator.comparing(val -> val.doubleValue())).get();
        double low = housesNearCharlesRiver.stream().map(lst -> lst.get(13)).min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("1. Average house price near charles river: " + average);
        System.out.println("1. High house price near charles river: " + high);
        System.out.println("1. Low house price near charles river: " + low);
    }
}
