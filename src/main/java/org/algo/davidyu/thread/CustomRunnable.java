package org.algo.davidyu.thread;

public class CustomRunnable implements Runnable{

    private String name;
    static int sharedVariable = 0;          // shared variable in threads
    private int count;

    public CustomRunnable(String name){
        this.name = name;
        this.count = 0;
    }

    @Override
    public void run() {
        while(count < 11){
            System.out.println("[ThreadName-"+name+"-count]:"+count++);
            System.out.println("[ThreadName-"+name+"-sharedVariable]:"+sharedVariable++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
