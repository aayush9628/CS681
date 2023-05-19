package edu.umb.cs681.hw07;

import java.time.LocalDateTime;

public class Link extends FSElement{

    private FSElement target;
    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
    }
    public FSElement getTarget(){
        return this.target;
    }
    @Override
    public boolean isDirectory() {
        return false;
    }

    public static void main(String[] args) {

    }
}
