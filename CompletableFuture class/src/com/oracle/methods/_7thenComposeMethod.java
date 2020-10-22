package com.oracle.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class _7thenComposeMethod {

    static CompletableFuture<User> getUser(){
        return CompletableFuture.supplyAsync(() -> {
            return new User("reza", (double) 10);
        });
    }

    static CompletableFuture<Double> getId(User user){
        return CompletableFuture.supplyAsync(user::getId);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*
            Combining two CompletableFutures together :

            1 ) Combine two dependent futures using thenCompose() :

            In earlier examples, the Supplier function passed to thenApply() callback would return a
            simple value but in this case, it is returning a CompletableFuture.
            Therefore, the final result in this case is a nested CompletableFuture.
         */
        CompletableFuture<CompletableFuture<Double>> result = getUser()
                .thenApply(_7thenComposeMethod::getId);

        System.out.println(result.get().get());

        /*
            If you want the final result to be a top-level Future, use thenCompose() method.
            So, Rule of thumb here - If your callback function returns a CompletableFuture,
            and you want a flattened result from the CompletableFuture chain
            (which in most cases you would), then use thenCompose().
         */
        CompletableFuture<Double> future = getUser()
                .thenCompose(_7thenComposeMethod::getId);

        System.out.println(future.get());
    }
}

class User{
    private String name;
    private Double id;

    public User(String name, Double id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }
}
