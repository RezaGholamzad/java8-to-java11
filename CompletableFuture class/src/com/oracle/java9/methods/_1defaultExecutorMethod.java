package com.oracle.java9.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class _1defaultExecutorMethod {
    public static void main(String[] args) {
        /*
            This method essentially returns the default executor for futures
            which do not specify an executor.
         */
        Executor executor = new CompletableFuture<>().defaultExecutor();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "reza", executor);

        System.out.println(completableFuture.join());
    }
}
