package com.oracle.java8.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class _6thenAcceptMethod {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
     /*
        If you don’t want to return anything from your callback function and just want to run some
        piece of code after the completion of the Future, then you can use thenAccept() and thenRun()
        methods. These methods are consumers and are often used as the last callback in the callback chain.

        CompletableFuture.thenAccept() takes a Consumer<T> and returns CompletableFuture<Void>.
        It has access to the result of the CompletableFuture on which it is attached.
     */
        CompletableFuture.supplyAsync(() -> {
            return "reza";
        }).thenAccept(name -> {
            System.out.println("hello " + name);
        }).get();

        /*
            While thenAccept() has access to the result of the CompletableFuture on which it is attached,
            thenRun() doesn’t even have access to the Future’s result. It takes a Runnable and returns
            CompletableFuture<Void>.
         */
        CompletableFuture.supplyAsync(()-> {
            // Run some computation
            return "reza";
        }).thenRun(() -> {
            // Computation Finished.
        }).get();
    }



}
