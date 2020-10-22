package com.oracle.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class _8thenCombineMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*
            Combining two CompletableFutures together :

            2 ) Combine two independent futures using thenCombine() :

            While thenCompose() is used to combine two Futures where one future is dependent on the other,
            thenCombine() is used when you want two Futures to run independently and do something
            after both are complete.
         */

        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {

                 Double heightInMeter = heightInCm/100;
                 return weightInKg/(heightInMeter*heightInMeter);
        });

        System.out.println("Your BMI is - " + combinedFuture.get());
    }
}
