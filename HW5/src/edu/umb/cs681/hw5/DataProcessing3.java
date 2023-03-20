package edu.umb.cs681.hw5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessing3 implements Runnable{
    private List<List<Double>> csv;
    public DataProcessing3(){
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
        Collections.sort(csv, Comparator.comparing(lst -> lst.get(13)));

        double averageCR = csv.subList(0, 49).stream().map(lst -> lst.get(0)).collect(Collectors.averagingDouble(val -> val.doubleValue()));
        System.out.println("3. Average crime rate in top 50 cheapest places: " + averageCR);
        double maxRooms = csv.subList(0,49).stream().map(lst -> lst.get(5)).max(Comparator.comparing(val -> val.doubleValue())).get();
        List<List<Double>> priceOfHouseList = csv.subList(0,49).stream().filter(val -> {
            if(maxRooms == val.get(5)){
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        double price = priceOfHouseList.stream().map(lst -> lst.get(13)).max(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("3. Price of the house with highest room in the top 50 cheapest houses: " + price);
    }

    public static void main(String[] args) {

    }
}
