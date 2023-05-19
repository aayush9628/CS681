package edu.umb.cs681.hw07;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{
    private LinkedList<FSElement> children;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        this.children = new LinkedList<>();
    }

    public LinkedList<FSElement> getChildren(){
        return this.children;
    }

    public void appendChild(FSElement child){
        this.children.add(child);
    }

    public int countChildren(){
        return this.children.size();
    }

    public LinkedList<Directory> getSubDirectory(){
        LinkedList<Directory> dirs = new LinkedList<>();
        for(FSElement element : this.children){
            if(element.isDirectory()){
                dirs.add((Directory) element);
            }
        }
        return dirs;
    }

    public LinkedList<File> getFiles(){
        LinkedList<File> files = new LinkedList<>();
        for(FSElement element : this.children){
            if(!element.isDirectory()){
                files.add((File) element);
            }
        }
        return files;
    }

    public int getTotalSize(){
        if(this.getChildren() == null){
            return 0;
        }
        LinkedList<Directory> subDirectories = this.getSubDirectory();
        int count = this.getChildren().size();
        for(Directory dir : subDirectories){
            count += dir.getTotalSize();
        }
        return count;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public static void main(String[] args) {

    }
}

