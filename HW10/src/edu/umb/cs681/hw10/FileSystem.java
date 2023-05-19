package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem{
    private LinkedList<Directory> rootDirs;
    private static FileSystem concurrentSingletonFileSystem = null;
    protected static ReentrantLock lock = new ReentrantLock();
    private FileSystem(){}
    public static FileSystem getFileSystem(){
        lock.lock();
        try{
            if(concurrentSingletonFileSystem == null){
                concurrentSingletonFileSystem = new FileSystem();
                concurrentSingletonFileSystem.rootDirs = new LinkedList<>();
            }
            return concurrentSingletonFileSystem;
        } finally {
            lock.unlock();
        }
    }
    public LinkedList<Directory> getRootDirs(){
        lock.lock();
        try {
            return this.rootDirs;
        } finally {
            lock.unlock();
        }
    }
    public void appendRootDir(Directory root){
        lock.lock();
        this.rootDirs.add(root);
        lock.unlock();
    }

    public static void main(String[] args) {

    }
}
