package com.oracle.java8.exceptions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class _1exceptionallyMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
            The exceptionally() callback gives you a chance to recover from errors generated
            from the original Future. You can log the exception here and return a default value.

            Note that, the error will not be propagated further in the callback chain
            if you handle it once.
         */
        int age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync( ()->{
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }

            if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(throwable -> {
            System.out.println("Oops! We have an exception - " + throwable.getMessage());
            return "Unknown!";
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }
}
