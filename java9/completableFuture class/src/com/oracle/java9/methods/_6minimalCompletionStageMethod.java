package com.oracle.java9.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class _6minimalCompletionStageMethod {
    public static void main(String[] args) {
        /*
            This method returns a new CompletionStage that behaves exactly the same as
            the copy method described. However, this new instance throws
            an UnsupportedOperationException each time an attempt is made
            to retrieve or set a resolved value.

            You can retrieve a new CompletableFuture with all available methods using
            the toCompletableFuture method provided on the CompletionStage API.
         */
        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(() -> "reza");

        CompletionStage<String> nameFutureCopy = nameFuture.minimalCompletionStage();

        CompletableFuture<String> withSurname = nameFuture.thenApply(anme -> "reza gholamzad");

        System.out.println(withSurname.join());

        System.out.println(nameFutureCopy.toCompletableFuture().join());
    }
}
