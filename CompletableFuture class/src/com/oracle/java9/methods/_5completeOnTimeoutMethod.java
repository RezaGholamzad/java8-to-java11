package com.oracle.java9.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class _5completeOnTimeoutMethod {
    public static void main(String[] args) {
        /*
            Completes the CompletableFuture normally with the specified value unless
            it is completed before the specified timeout.

            This method is essentially similar to orTimeout(). However,
            with this method you can also provide a default or static value
            if the service is taking too long to respond or there is a TimeoutException.
            This works more like a method which is capable of returning a
            fallback value when something goes wrong.
         */

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            System.out.println("in future");

            return "Result of the asynchronous computation";
        }).completeOnTimeout( "default value",3, TimeUnit.SECONDS);

        System.out.println(completableFuture.join());
    }
}
