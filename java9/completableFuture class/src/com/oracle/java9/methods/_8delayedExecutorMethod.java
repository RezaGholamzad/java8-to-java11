package com.oracle.java9.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class _8delayedExecutorMethod {
    public static void main(String[] args) {
        /*
            Returns a new Executor that submits the task to the given base executor
            after a given delay (no delay if non-positive). Each delay begins when
            the execute method of the returned executor is called. If no executor
            is specified, the default executor (ForkJoinPool.commonPool()) will be used.

            Executor delayedExecutor(long delay, TimeUnit unit, Executor executor)
            Executor delayedExecutor(long delay, TimeUnit unit)
         */
        Executor executor = CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()-> "reza", executor);

        System.out.println(completableFuture.join());
    }
}
