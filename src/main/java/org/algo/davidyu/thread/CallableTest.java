package org.algo.davidyu.thread;

import java.util.concurrent.*;

public class CallableTest {

    public void senarioNo1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<Data> callable1 = new TransUpper("davidyu");
        Callable<Data> callable2 = new TransUpper("abcde");
        Callable<Data> callable3 = new TransUpper("fghijk");
        Future<Data> future = executorService.submit(callable1);
        future = executorService.submit(callable2);
        future = executorService.submit(callable3);
        Runnable r1 = new CheckVariable(future);
        Thread t1 = new Thread(r1);
        t1.start();
        Data data = null;
        try {
            data = future.get(12, TimeUnit.SECONDS);
            System.out.println(data);
            System.out.println("end of program");
            executorService.shutdown();
        } catch (InterruptedException e) {
            System.out.println(1);
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println(2);
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println(3);
            throw new RuntimeException(e);
        } finally{
            executorService.shutdown();
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        new CallableTest().senarioNo1();
    }
}


class CheckVariable implements Runnable{

    Future<Data> future;

    public CheckVariable(Future<Data> future){
        this.future = future;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
//            future.cancel(false);
//            future.cancel(true);
            System.out.println("callable has benn canceled");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class TransUpper implements Callable {

    String name = "";
    public TransUpper(String name){
        this.name = name;
    }

    // 10초후 이름을 대문자로 바꿈
    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        return new Data(name.toUpperCase());
    }
}

class Data{
    String name;
    public Data(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                '}';
    }
}
