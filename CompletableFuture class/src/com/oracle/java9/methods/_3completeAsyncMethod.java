package com.oracle.java9.methods;

import java.util.concurrent.CompletableFuture;

public class _3completeAsyncMethod {
    public static void main(String[] args) {
        /*
            Completes this CompletableFuture with the result of the given Supplier
            function invoked from an asynchronous task using the default executor.

            CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor)
            CompletableFuture<T> completeAsync(Supplier<? extends T> supplier)

            The difference between this two overloaded methods is the existence of the second argument,
            where the Executor running the task can be specified. If none is provided,
            the default executor (returned by the defaultExecutor method) will be used.
         */

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        CompletableFuture<String> nameFuture = completableFuture.completeAsync(() -> "reza");

        System.out.println(nameFuture.join());
     }
}
