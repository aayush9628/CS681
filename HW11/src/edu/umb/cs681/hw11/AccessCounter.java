package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private HashMap<Path, Integer> accessCounterMap = new HashMap<>();
    private static AccessCounter singleton = null;
    private static ReentrantLock lockStatic = new ReentrantLock();
    private ReentrantLock lockNonStatic = new ReentrantLock();

    public AccessCounter getInstance(){
        lockStatic.lock();
        try{
            if(singleton == null){
                singleton = new AccessCounter();
            }
            return singleton;
        } finally {
            lockStatic.unlock();
        }
    }

    public void increment(Path path){
        lockNonStatic.lock();
        try{
            if(accessCounterMap.containsKey(path)){
                accessCounterMap.put(path, accessCounterMap.get(path) + 1);
            } else {
                accessCounterMap.put(path, 1);
            }
        } finally {
            lockNonStatic.unlock();
        }
    }

    public int getCount(Path path){
        lockNonStatic.lock();
        try {
            if (accessCounterMap.containsKey(path)) {
                return accessCounterMap.get(path);
            } else {
                return 0;
            }
        } finally {
            lockNonStatic.unlock();
        }
    }

    public static void main(String[] args) {

    }

}
