package com.oracle.java9.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class _7newIncompleteFutureMethod {
    public static void main(String[] args) {
        /*
            The newIncompleteFuture, also known as the “virtual constructor”,
            is used to get a new completable future instance of the same type.

            This method is especially useful when subclassing CompletableFuture,
            mainly because it is used internally in almost all methods returning
            a new CompletionStage, allowing subclasses to control what subtype
            gets returned by such methods.

            Returns a new incomplete CompletableFuture of the type to be
            returned by a CompletionStage method. Subclasses should
            normally override this method to return an instance of the same
            class as this CompletableFuture. The default implementation
            returns an instance of class CompletableFuture.
         */

        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(() -> "reza");

        CompletionStage<String> completionStage = nameFuture.newIncompleteFuture();

        CompletableFuture<String> incompleteNameFuture = nameFuture.newIncompleteFuture();

        CompletableFuture<String> withSurname = nameFuture.thenApply(anme -> "reza gholamzad");

        System.out.println(withSurname.join());

        System.out.println(incompleteNameFuture.join());

    }
}
