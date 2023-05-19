package edu.umb.cs681.hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{
    private LinkedList<FSElement> children;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        this.children = new LinkedList<>();
    }

    public LinkedList<FSElement> getChildren(){
        lock.lock();
        try {
            return this.children;
        } finally {
            lock.unlock();
        }
    }

    public void appendChild(FSElement child){
        lock.lock();
        this.children.add(child);
        lock.unlock();
    }

    public int countChildren(){
        lock.lock();
        try {
            return this.children.size();
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectory(){
        LinkedList<Directory> dirs = new LinkedList<>();
        lock.lock();
        try{
            for(FSElement element : this.children){
                if(element.isDirectory()){
                    dirs.add((Directory) element);
                }
            }
            return dirs;
        }finally {
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles(){
        LinkedList<File> files = new LinkedList<>();
        lock.lock();
        try{
            for(FSElement element : this.children){
                if(!element.isDirectory()){
                    files.add((File) element);
                }
            }
            return files;
        }finally {
            lock.unlock();
        }
    }

    public int getTotalSize(){
        lock.lock();
        try{
            if(this.getChildren() == null){
                return 0;
            }
            LinkedList<Directory> subDirectories = this.getSubDirectory();
            int count = this.getChildren().size();
            for(Directory dir : subDirectories){
                count += dir.getTotalSize();
            }
            return count;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isDirectory() {
        lock.lock();
        try {
            return true;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

    }
}

