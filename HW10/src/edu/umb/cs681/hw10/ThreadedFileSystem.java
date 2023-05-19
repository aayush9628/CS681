package edu.umb.cs681.hw10;

import java.time.LocalDateTime;

import static edu.umb.cs681.hw10.FileSystem.getFileSystem;

public class ThreadedFileSystem implements Runnable{
    private boolean done = false;
    private static FileSystem fileSystem;
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

    }

    public void setDone() {
        this.done = true;
    }

    public static void main(String[] args) {
        fileSystem = getFileSystem();
        Directory root = new Directory(null, "root", 0, LocalDateTime.now());
        Directory apps = new Directory(root, "Apps", 0, LocalDateTime.now());
        Directory bin = new Directory(root, "bin", 0, LocalDateTime.now());
        Directory home = new Directory(root, "home", 0, LocalDateTime.now());
        root.appendChild(apps);
        root.appendChild(bin);
        root.appendChild(home);

        File x = new File(root, "x", 0, LocalDateTime.now());
        apps.appendChild(x);
        File y = new File(root, "y", 0, LocalDateTime.now());
        bin.appendChild(y);

        Directory pictures = new Directory(home, "pictures", 0, LocalDateTime.now());
        File c = new File(home, "c", 0, LocalDateTime.now());
        home.appendChild(pictures);
        home.appendChild(c);
        File a = new File(pictures, "a", 0, LocalDateTime.now());
        File b = new File(pictures, "b", 0, LocalDateTime.now());
        pictures.appendChild(a);
        pictures.appendChild(b);

        Link d = new Link(root, "d", 0, LocalDateTime.now(), pictures);
        Link e = new Link(root, "e", 0, LocalDateTime.now(), x);
        root.appendChild(d);
        root.appendChild(e);

        fileSystem.appendRootDir(root);


        Thread threads[] = new Thread[11];
        ThreadedFileSystem threadedFileSystem[] = new ThreadedFileSystem[11];
        for(int i = 0; i < threads.length; i++){
            threadedFileSystem[i] = new ThreadedFileSystem();
            threads[i] = new Thread(threadedFileSystem[i], "Thread " + (i+1));
            threads[i].start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        for(int i = 0; i < threadedFileSystem.length; i++){
            threadedFileSystem[i].setDone();
        }

        for(int i = 0; i < threads.length; i++){
            threads[i].interrupt();
        }
        try{
            for(int i = 0; i < threads.length; i++){
                threads[i].join();
            }
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
