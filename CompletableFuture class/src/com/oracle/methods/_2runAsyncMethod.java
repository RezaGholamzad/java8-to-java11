package com.oracle.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class _2runAsyncMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*
            If you want to run some background task asynchronously and don’t want to return
            anything from the task, then you can use CompletableFuture.runAsync() method.
            It takes a Runnable object and returns CompletableFuture<Void>.
         */
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(new Runnable(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                System.out.println("I'll run in a separate thread than the main thread.");
            }
        });

        // Block and wait for the future to complete
        completableFuture.get();

        //******************************************************************

        //pass the Runnable object in the form of a lambda expression.

        //Using Lambda Expression :

        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });

        // Block and wait for the future to complete
        completableFuture.get();

    }
}
