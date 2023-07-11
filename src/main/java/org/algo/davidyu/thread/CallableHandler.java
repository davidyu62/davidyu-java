package org.algo.davidyu.thread;

import java.util.concurrent.*;

public class CallableHandler {


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        /**
         * submit 하면 thread가 실행되며, here도 바로 출력된다.
         * 만약 기다리기 위해서는 get을 써야한다.
         * */
//        ExecutorService executorService = Executors.newFixedThreadPool(2);              // thread pool 이 두개
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<Result> callable1 = new SampleCallable("No.1");
        Future<Result> future = executorService.submit(callable1);                  // submit 하면 thread가 실행된다.
        System.out.println("here1");
//        executorService.shutdown();

        future = executorService.submit(callable1);                  // submit 하면 thread가 실행된다.
        Result result = future.get(12,TimeUnit.SECONDS);
        System.out.println("here2");
        executorService.shutdown();

        //
//
////            Callable<Result> callable3 = new SampleCallable("No.3");
//            Result result = null;
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            Future<Result> future = executorService.submit(callable1);
////            result = future.get(12,TimeUnit.SECONDS);
////            System.out.println(result);
////            Callable<Result> callable2 = new SampleCallable("No.2");
//////            Callable<Result> callable2 = new SampleCallable(result.getName());
////            future = executorService.submit(callable2);
////            result = future.get(12,TimeUnit.SECONDS);
////            System.out.println(result);
//////            future = executorService.submit(callable3);
//////            result = future.get(10,TimeUnit.SECONDS);
//////            System.out.println(result);
////            System.out.println("shutdown");
////            executorService.shutdown();
    }
}
