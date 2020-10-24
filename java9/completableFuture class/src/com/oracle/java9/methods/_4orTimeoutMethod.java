package com.oracle.java9.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _4orTimeoutMethod {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        /*
            Resolves the CompletableFuture exceptionally with TimeoutException,
            unless it is completed before the specified timeout.

             the get() method where we can define the Time Units and
             if the future call is not completed in that particular time,
             it will raise the timeout exception but this method will block the thread
             and as a result it will not be non-blocking anymore.
             In order to deal with this, Java9 introduced orTimeout() which will
             provide us the timeout functionality and keep the calls non-blocking
             at the same time i.e the thread will not be blocked for that point
             of time and you can still have the timeout feature for your future calls.
         */
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            System.out.println("in future");

            return "Result of the asynchronous computation";
        }).orTimeout(2, TimeUnit.SECONDS);

        System.out.println("out future(thread will not block)");

        System.out.println(completableFuture.get());

        System.out.println("******************************************");

        // if use of get :
        String future_with_get = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            System.out.println("in future with get");

            return "Result of the asynchronous computation";
        }).get(2, TimeUnit.SECONDS);

        System.out.println("out future with get");

    }
}
