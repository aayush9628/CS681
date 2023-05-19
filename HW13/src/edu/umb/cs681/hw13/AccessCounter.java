package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {
    private HashMap<Path, Integer> accessCounterMap = new HashMap<>();
    private static AccessCounter singleton = null;
    private static ReentrantLock lockStatic = new ReentrantLock();
    private ReentrantReadWriteLock lockNonStatic = new ReentrantReadWriteLock();

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
        lockNonStatic.writeLock().lock();
        try{
            if(accessCounterMap.containsKey(path)){
                accessCounterMap.put(path, accessCounterMap.get(path) + 1);
            } else {
                accessCounterMap.put(path, 1);
            }
        } finally {
            lockNonStatic.writeLock().unlock();
        }
    }

    public int getCount(Path path){
        lockNonStatic.readLock().lock();
        try {
            if (accessCounterMap.containsKey(path)) {
                return accessCounterMap.get(path);
            } else {
                return 0;
            }
        } finally {
            lockNonStatic.readLock().unlock();
        }
    }

    public static void main(String[] args) {

    }

}
