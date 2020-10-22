package com.oracle.exceptions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class _2handleMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
            The API also provides a more generic method - handle() to recover from exceptions.
            It is called whether or not an exception occurs.
         */
        int age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if(age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> {
            /*
                If an exception occurs, then the res argument will be null,
                otherwise, the ex argument will be null.
             */
            if (ex != null){
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return res;
        });

        System.out.println("Maturity : " + maturityFuture.get());

    }
}
