package com.oracle.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class _4thenApplyMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
            The CompletableFuture.get() method is blocking. It waits until the Future is completed and
            returns the result after its completion.

            But, that’s not what we want right? For building asynchronous systems we should be able
            to attach a callback to the CompletableFuture which should automatically get called
            when the Future completes.

            That way, we won’t need to wait for the result, and we can write the logic that needs
            to be executed after the completion of the Future inside our callback function.

            You can attach a callback to the CompletableFuture using thenApply(),
            thenAccept() and thenRun() methods.
         */

        // Create a CompletableFuture
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "reza";
        });

        /*
            You can use thenApply() method to process and transform the result of a
            CompletableFuture when it arrives. It takes a Function<T,R> as an argument.
            Function<T,R> is a simple functional interface representing a function that accepts
            an argument of type T and produces a result of type R.
         */

        // Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFeature = completableFuture.thenApply(name -> {
            return "hello " + name;
        });

        // Block and get the result of the future.
        System.out.println(greetingFeature.get());

        /*
            You can also write a sequence of transformations on the CompletableFuture by
            attaching a series of thenApply() callback methods. The result of one thenApply()
            method is passed to the next in the series.
         */

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        }).thenApply(name -> {
            return "Hello " + name;
        }).thenApply(greeting -> {
            return greeting + ", Welcome to the CalliCoder Blog";
        });

        System.out.println(completableFuture1.get());

    }
}
