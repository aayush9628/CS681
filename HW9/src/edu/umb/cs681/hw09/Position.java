package edu.umb.cs681.hw09;


import java.util.ArrayList;
import java.util.List;

public record Position(double latitude, double longitude, double altitude) {
    List<Double> coordinate() {
        List<Double> coord = new ArrayList<>();
        coord.add(latitude);
        coord.add(longitude);
        coord.add(altitude);
        return coord;
    }
    Position change(double newLat, double newLon, double newAlt){
        return new Position(newLat, newLon, newAlt);
    }
    boolean higherAltThan(Position anotherPosition){
        return anotherPosition.altitude > this.altitude;
    }
    boolean lowerAltThan(Position anotherPosition){
        return anotherPosition.altitude < this.altitude;
    }
    boolean northOf(Position anotherPosition){
        return anotherPosition.longitude < this.longitude;
    }
    boolean southOf(Position anotherPosition){
        return anotherPosition.longitude > this.longitude;
    }

    public static void main(String[] args) {

    }
}
