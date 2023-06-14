package org.algo.davidyu.thread;

import java.util.concurrent.*;

public class CallableHandler {

    public static void main(String[] args){
        try {
            Callable<Result> callable1 = new SampleCallable("No.1");

//            Callable<Result> callable3 = new SampleCallable("No.3");
            Result result = null;
            ExecutorService executorService = Executors.newCachedThreadPool();
            Future<Result> future = executorService.submit(callable1);
            result = future.get(10,TimeUnit.SECONDS);
            System.out.println(result);
            Callable<Result> callable2 = new SampleCallable("No.2");
//            Callable<Result> callable2 = new SampleCallable(result.getName());
            future = executorService.submit(callable2);
            result = future.get(10,TimeUnit.SECONDS);
            System.out.println(result);
//            future = executorService.submit(callable3);
//            result = future.get(10,TimeUnit.SECONDS);
//            System.out.println(result);
            System.out.println("shutdown");
            executorService.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
