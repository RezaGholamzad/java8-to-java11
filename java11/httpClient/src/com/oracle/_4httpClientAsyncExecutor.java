package com.oracle;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class _4httpClientAsyncExecutor {
    public static void main(String[] args) throws URISyntaxException, ExecutionException, InterruptedException {

        // Add a custom executor :

        Executor executor = Executors.newFixedThreadPool(5);

        /*
            Once created, an HttpClient instance is immutable, thus automatically thread-safe,
            and you can send multiple requests with it.
         */
        HttpClient httpClient = HttpClient.newBuilder()
                .executor(executor)
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        List<URI> targets = Arrays.asList(
                new URI("https://httpbin.org/get?name=mkyong1"),
                new URI("https://httpbin.org/get?name=mkyong2"),
                new URI("https://httpbin.org/get?name=mkyong3"));

        List<CompletableFuture<String>> completableFutureList = targets.stream()
                .map(url ->
                        httpClient.sendAsync(
                                HttpRequest.newBuilder(url)
                                        .setHeader("User-Agent", "Java 11 HttpClient Bot")
                                        .build(),
                                HttpResponse.BodyHandlers.ofString())
                                .thenApply(HttpResponse::body)
                )
                .collect(Collectors.toList());

        for (CompletableFuture<String> completableFuture : completableFutureList){
            System.out.println("thread : " + Thread.currentThread());
            System.out.println(completableFuture.get());
        }
    }
}
