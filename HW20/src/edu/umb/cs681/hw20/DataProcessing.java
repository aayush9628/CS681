package edu.umb.cs681.hw20;

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

public class DataProcessing {
    public static void dataProcessing1(List<List<Double>> csv){
        List<List<Double>> housesNearCharlesRiver = csv.stream().parallel().filter(lst -> {
            if(lst.get(3) == 1.0){
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());

        double average = housesNearCharlesRiver
                .stream()
                .parallel()
                .map(lst -> lst.get(13))
                .collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double high = housesNearCharlesRiver
                .stream()
                .parallel()
                .map(lst -> lst.get(13))
                .max(Comparator.comparing(val -> val.doubleValue())).get();
        double low = housesNearCharlesRiver
                .stream()
                .parallel()
                .map(lst -> lst.get(13))
                .min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("Average house price near charles river: " + average);
        System.out.println("High house price near charles river: " + high);
        System.out.println("Low house price near charles river: " + low);
    }
    public static void dataProcessing2(List<List<Double>> csv){
        List<List<Double>> housesWithLowCrimeRateLowPtratio = csv.stream().parallel()
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
        housesWithLowCrimeRateLowPtratio = csv.stream().parallel()
                .filter(lst -> {
                    if(lst.get(0) < 0.1){
                        return true;
                    } else {
                        return false;
                    }
                }).collect(Collectors.toList());

        double averagePrice = housesWithLowCrimeRateLowPtratio
                .stream()
                .parallel()
                .map(lst -> lst.get(13))
                .collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double highPrice = housesWithLowCrimeRateLowPtratio
                .stream()
                .parallel()
                .map(lst -> lst.get(13))
                .max(Comparator.comparing(val -> val.doubleValue())).get();
        double lowPrice = housesWithLowCrimeRateLowPtratio
                .stream()
                .parallel()
                .map(lst -> lst.get(13))
                .min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("Average house price for houses with low crime rate: " + averagePrice);
        System.out.println("High house price for houses with low crime rate: " + highPrice);
        System.out.println("Low house price for houses with low crime rate: " + lowPrice);
        double averageNox = housesWithLowCrimeRateLowPtratio
                .stream()
                .parallel()
                .map(lst -> lst.get(4))
                .collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double highNox = housesWithLowCrimeRateLowPtratio
                .stream()
                .parallel()
                .map(lst -> lst.get(4))
                .max(Comparator.comparing(val -> val.doubleValue())).get();
        double lowNox = housesWithLowCrimeRateLowPtratio
                .stream()
                .parallel()
                .map(lst -> lst.get(4))
                .min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("Average NOX for houses with low crime rate: " + averageNox);
        System.out.println("High NOX for houses with low crime rate: " + highNox);
        System.out.println("Low NOX for houses with low crime rate: " + lowNox);
        double averageRM = housesWithLowCrimeRateLowPtratio
                .stream()
                .parallel()
                .map(lst -> lst.get(5))
                .collect(Collectors.averagingDouble(val -> val.doubleValue()));
        double highRM = housesWithLowCrimeRateLowPtratio
                .stream()
                .parallel()
                .map(lst -> lst.get(5))
                .max(Comparator.comparing(val -> val.doubleValue())).get();
        double lowRM = housesWithLowCrimeRateLowPtratio
                .stream()
                .parallel()
                .map(lst -> lst.get(5))
                .min(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("Average rooms for houses with low crime rate: " + averageRM);
        System.out.println("High rooms for houses with low crime rate: " + highRM);
        System.out.println("Low rooms for houses with low crime rate: " + lowRM);
    }

    public static void dataProcessing3(List<List<Double>> csv){
        // average crime rate in top 50 cheapest houses and price of the house with the most rooms
        Collections.sort(csv, Comparator.comparing(lst -> lst.get(13)));

        double averageCR = csv.subList(0, 49)
                .stream()
                .parallel()
                .map(lst -> lst.get(0))
                .collect(Collectors.averagingDouble(val -> val.doubleValue()));
        System.out.println("Average crime rate in top 50 cheapest places: " + averageCR);
        double maxRooms = csv.subList(0,49)
                .stream()
                .parallel()
                .map(lst -> lst.get(5))
                .max(Comparator.comparing(val -> val.doubleValue())).get();
        List<List<Double>> priceOfHouseList = csv.subList(0,49).stream().parallel().filter(val -> {
            if(maxRooms == val.get(5)){
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        double price = priceOfHouseList
                .stream()
                .parallel()
                .map(lst -> lst.get(13))
                .max(Comparator.comparing(val -> val.doubleValue())).get();
        System.out.println("Price of the house with highest room in the top 50 cheapest houses: " + price);
    }
    public static void dataProcessing4(List<List<Double>> csv){
        // average tax rate of house with highest and lowest house age

        double maxHouseAge = csv.stream().parallel().map(lst -> lst.get(6))
                .max(Comparator.comparing(val -> val.doubleValue())).get();
        List<List<Double>> maxHouseAgeList = csv.stream().parallel().filter(val -> {
            if(maxHouseAge == val.get(6)){
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        double taxRateForMax = maxHouseAgeList.stream().parallel().map(lst -> lst.get(9))
                .collect(Collectors.averagingDouble(x -> x.doubleValue()));
        System.out.println("Average Tax rate of the house with highest house age: " + taxRateForMax);

        double minHouseAge = csv.stream().parallel().map(lst -> lst.get(6))
                .min(Comparator.comparing(val -> val.doubleValue())).get();
        List<List<Double>> minHouseAgeList = csv.stream().parallel().filter(val -> {
            if(minHouseAge == val.get(6)){
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
        double taxRateForMin = minHouseAgeList.stream().parallel().map(lst -> lst.get(9))
                .collect(Collectors.averagingDouble(x -> x.doubleValue()));
        System.out.println("Average Tax rate of the house with lowest house age: " + taxRateForMin);
    }
    public static void main(String[] args) {
        List<List<Double>> csv = new ArrayList<>();
        Path path = Paths.get("bos-housing.csv");

        try(Stream<String> lines = Files.lines(path)){
            csv = lines.map(line -> {
                return Stream.of(line.split(",")).parallel()
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

        csv.remove(csv.get(0));

        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        dataProcessing1(csv);
        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        dataProcessing2(csv);
        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        dataProcessing3(csv);
        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
        System.out.println();
        dataProcessing4(csv);
        for(int i = 0; i < 50; i++){
            System.out.print("*");
        }
    }
}
