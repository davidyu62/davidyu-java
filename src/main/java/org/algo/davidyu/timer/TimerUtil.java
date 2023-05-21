package org.algo.davidyu.timer;

public class TimerUtil {

    public void getElapsedTime(){
        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long finishTime = System.currentTimeMillis();
        System.out.println("elapsed time is " + (finishTime - startTime));
    }

    public static void main(String[] args){
        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getElapsedTime();
    }
}
