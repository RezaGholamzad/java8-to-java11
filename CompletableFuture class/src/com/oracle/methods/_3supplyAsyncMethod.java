package com.oracle.methods;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class _3supplyAsyncMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
            CompletableFuture.runAsync() is useful for tasks that don’t return anything.
            But what if you want to return some result from your background task?

            Well, CompletableFuture.supplyAsync() is your companion.
            It takes a Supplier<T> and returns CompletableFuture<T> where T is the type
            of the value obtained by calling the given supplier.

            A Supplier<T> is a simple functional interface which represents a supplier of results.
            It has a single get() method where you can write your background task and return the result.
         */
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Result of the asynchronous computation";
            }
        });

        // Block and get the result of the Future
        String result = completableFuture.get();
        System.out.println(result);

        //**************************************************************

        //Using Lambda Expression :

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });

        // Block and get the result of the Future
        result = completableFuture.get();
        System.out.println(result);

        //***************************************************************

        /*
            You might be wondering that - Well, I know that the runAsync() and supplyAsync() methods
            execute their tasks in a separate thread. But, we never created a thread right?

            Yes! CompletableFuture executes these tasks in a thread obtained from the global
            ForkJoinPool.commonPool().

            But hey, you can also create a Thread Pool and pass it to runAsync() and supplyAsync()
            methods to let them execute their tasks in a thread obtained from your thread pool.

            All the methods in the CompletableFuture API has two variants - One which accepts
            an Executor as an argument and one which doesn’t.

            Variations of runAsync() and supplyAsync() methods :

            static CompletableFuture<Void>  runAsync(Runnable runnable)
            static CompletableFuture<Void>  runAsync(Runnable runnable, Executor executor)
            static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
            static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
         */

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        }, executorService);

    }
}
