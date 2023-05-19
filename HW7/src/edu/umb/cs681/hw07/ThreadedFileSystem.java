package edu.umb.cs681.hw07;

import java.time.LocalDateTime;

import static edu.umb.cs681.hw07.FileSystem.getFileSystem;

public class ThreadedFileSystem implements Runnable{
    private boolean done = false;
    private static FileSystem fileSystem;
    public void setDone(){
        this.done = true;
    }
    @Override
    public void run() {
        if(!done){
            System.out.println(Thread.currentThread().getName());
            for(Directory dir : fileSystem.getRootDirs()) {
                System.out.println(dir.getName());
            }
            for(FSElement element : fileSystem.getRootDirs().get(0).getChildren()){
                System.out.println(element.getName());
            }
            System.out.println(fileSystem.getRootDirs().get(0).getTotalSize());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        fileSystem = getFileSystem();
        Directory root = new Directory(null, "root", 0, LocalDateTime.now());
        Directory folder1 = new Directory(root, "folder1", 0, LocalDateTime.now());
        root.appendChild(folder1);
        Directory folder2 = new Directory(root, "folder2", 0, LocalDateTime.now());
        root.appendChild(folder2);
        File file1 = new File(root, "file1", 0, LocalDateTime.now());
        root.appendChild(file1);
        File file2 = new File(root, "file2", 0, LocalDateTime.now());
        root.appendChild(file2);
        Directory folder3 = new Directory(folder1, "folder3", 0, LocalDateTime.now());
        folder1.appendChild(folder3);
        File file3 = new File(folder2, "file3", 0, LocalDateTime.now());
        folder2.appendChild(file3);
        fileSystem.appendRootDir(root);
        Directory home = new Directory(null, "home", 0, LocalDateTime.now());
        fileSystem.appendRootDir(home);

        ThreadedFileSystem tfs1 = new ThreadedFileSystem();
        Thread thread1 = new Thread(tfs1, "Thread 1");
        ThreadedFileSystem tfs2 = new ThreadedFileSystem();
        Thread thread2 = new Thread(tfs2, "Thread 2");
        ThreadedFileSystem tfs3 = new ThreadedFileSystem();
        Thread thread3 = new Thread(tfs3, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tfs1.setDone();
        tfs2.setDone();
        tfs3.setDone();
        try{
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
