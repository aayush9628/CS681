package edu.umb.cs681.hw10;

import java.time.LocalDateTime;

public class Link extends FSElement{

    private FSElement target;
    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
    }
    public FSElement getTarget(){
        lock.lock();
        try {
            return this.target;
        } finally {
            lock.unlock();
        }
    }
    @Override
    public boolean isDirectory() {
        lock.lock();
        try {
            return false;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

    }
}
