package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected Directory parent;

    protected static ReentrantLock lock = new ReentrantLock();

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime){
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public int getSize(){
        lock.lock();
        try {
            return this.size;
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        lock.lock();
        try {
            return name;
        } finally {
            lock.unlock();
        }
    }

    public void setName(String name) {
        lock.lock();
        this.name = name;
        lock.unlock();
    }

    public LocalDateTime getCreationTime() {
        lock.lock();
        try {
            return creationTime;
        } finally {
            lock.unlock();
        }
    }

    public void setCreationTime(LocalDateTime creationTime) {
        lock.lock();
        this.creationTime = creationTime;
        lock.unlock();
    }

    public Directory getParent() {
        lock.lock();
        try {
            return parent;
        } finally {
            lock.unlock();
        }
    }

    public void setParent(Directory parent) {
        lock.lock();
        this.parent = parent;
        lock.unlock();
    }

    public abstract boolean isDirectory();

    public static void main(String[] args) {

    }
}
