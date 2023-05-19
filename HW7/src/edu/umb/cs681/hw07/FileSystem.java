package edu.umb.cs681.hw07;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private LinkedList<Directory> rootDirs;
    private static FileSystem concurrentSingletonFileSystem = null;
    private static ReentrantLock lock = new ReentrantLock();
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
        return this.rootDirs;
    }
    public void appendRootDir(Directory root){
        this.rootDirs.add(root);
    }

    public static void main(String[] args) {

    }
}
