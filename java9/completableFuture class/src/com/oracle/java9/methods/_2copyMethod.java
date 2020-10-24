package com.oracle.java9.methods;

import java.util.concurrent.CompletableFuture;

public class _2copyMethod {
    public static void main(String[] args) {
        /*
            returns an immutable copy of the existing CompletableFuture instance,
            since the copy is immutable it would not affect the original instance in any way.

            When this gets completed normally, the new one gets completed normally also
            When this gets completed exceptionally with exception X,
            the new one is also completed exceptionally with a CompletionException with X as cause.
         */

        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(() -> "reza");
        CompletableFuture<String> nameFutureCopy = nameFuture.copy();
        CompletableFuture<String> withSurname = nameFuture.thenApply(name -> "reza gholamzad");

        System.out.println(withSurname.join());

        // Change in original instance should affect it's copy instance.
        System.out.println(nameFutureCopy.join());
    }
}
