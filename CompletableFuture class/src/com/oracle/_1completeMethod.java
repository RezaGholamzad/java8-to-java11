package com.oracle;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class _1completeMethod {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //create a CompletableFuture
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        //get the result of this CompletableFuture
        String result = completableFuture.get();

        /*
            The get() method blocks until the Future is complete. So, the above call will
            block forever because the Future is never completed.
            use CompletableFuture.complete() method to manually complete a Future
         */

        completableFuture.complete("Future's Result");

    }

}
