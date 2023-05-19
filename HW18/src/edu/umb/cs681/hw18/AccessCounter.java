package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private ConcurrentHashMap<Path, Integer> accessCounterMap = new ConcurrentHashMap<>();
    private static AccessCounter singleton = null;

    public AccessCounter getInstance(){
        if(singleton == null){
            singleton = new AccessCounter();
        }
        return singleton;
    }

    public void increment(Path path){
        if(accessCounterMap.containsKey(path)){
            accessCounterMap.put(path, accessCounterMap.get(path) + 1);
        } else {
            accessCounterMap.put(path, 1);
        }
    }

    public int getCount(Path path){
        if (accessCounterMap.containsKey(path)) {
            return accessCounterMap.get(path);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
