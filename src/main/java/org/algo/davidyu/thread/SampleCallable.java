package org.algo.davidyu.thread;

import java.util.concurrent.Callable;

public class SampleCallable implements Callable {

    String name;
    public SampleCallable(String name){
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
        Thread.sleep(1000);
        Result result = new Result();
        result.setName(name);
        result.setResult(true);
        result.setText("clear");
        System.out.println("result:"+result);
        return result;
    }
}

class Result{
    String name;
    boolean result;
    String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", result=" + result +
                ", text='" + text + '\'' +
                '}';
    }
}
