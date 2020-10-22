package com.oracle.methods;

import java.util.concurrent.*;

public class _5thenApplyAsyncMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
            // thenApply() variants :
            <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
            <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
            <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)

            These async callback variations help you further parallelize your computations by executing
            the callback tasks in a separate thread.
         */

        CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println(Thread.currentThread());
            return "Some Result";

        }).thenApply(result -> {
            /*
              Executed in the same thread where the supplyAsync() task is executed
              or in the main thread If the supplyAsync() task completes immediately
              (Remove sleep() call to verify)
            */
            System.out.println(Thread.currentThread());
            return "Processed Result";

        }).get();

        System.out.println("***************************************");

        /*
            To have more control over the thread that executes the callback task,
            you can use async callbacks. If you use thenApplyAsync() callback,
            then it will be executed in a different thread obtained from ForkJoinPool.commonPool()
         */
        CompletableFuture.supplyAsync(()-> {

            System.out.println(Thread.currentThread());
            return "Some Result";

        }).thenApplyAsync(result -> {

            // Executed in a different thread from ForkJoinPool.commonPool()
            System.out.println(Thread.currentThread());
            return "Processed Result";

        }).get();

        System.out.println("***************************************");

        /*
            Moreover, If you pass an Executor to the thenApplyAsync() callback then the task
            will be executed in a thread obtained from the Executor’s thread pool.
         */

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture.supplyAsync(() -> {

            System.out.println(Thread.currentThread());
            return "Some result";

        }).thenApplyAsync(result -> {

            // Executed in a thread obtained from the executor
            System.out.println(Thread.currentThread());
            return "Processed Result";

        }, executorService).get();
    }
}
