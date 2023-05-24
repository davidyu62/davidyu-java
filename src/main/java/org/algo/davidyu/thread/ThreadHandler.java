package org.algo.davidyu.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadHandler {
    public void start(){
        Runnable r1 = new CustomRunnable("A");
        Runnable r2 = new CustomRunnable("B");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        try {
            t1.start();
            t1.join();
            t2.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void executorServiceStart(){
        Runnable r1 = new CustomRunnable("A");
        Runnable r2 = new CustomRunnable("B");
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(r1);
        executorService.submit(r2);
    }
}
