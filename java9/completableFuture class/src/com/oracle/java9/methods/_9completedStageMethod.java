package com.oracle.java9.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class _9completedStageMethod {
    public static void main(String[] args) {
        /*
            This utility methods return already resolved CompletionStage instances,
            either completed normally with a value (completedStage) or
            completed exceptionally (failedStage) with the given exception.
         */
        CompletableFuture<String> completableFuture =
                CompletableFuture.completedStage("reza").toCompletableFuture();
         /*
            CompletableFuture.completedFuture("reza") ==
            CompletableFuture.completedStage("reza").toCompletableFuture();
         */

        completableFuture.thenAccept(name -> {
            System.out.println("hello " + name);
        });
        System.out.println(completableFuture.join());

        System.out.println("*******************************************");

        CompletableFuture<Object> completableFuture1 =
                CompletableFuture.failedStage(new RuntimeException("exception"))
                        .toCompletableFuture();

        completableFuture1.thenAccept(name-> {
            System.out.println("hello " + name);
        });

        System.out.println(completableFuture1.join());

    }
}
